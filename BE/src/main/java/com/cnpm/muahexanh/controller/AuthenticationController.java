package com.cnpm.muahexanh.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import com.cnpm.muahexanh.exception.*;
import com.cnpm.muahexanh.model.entity.User;
import com.cnpm.muahexanh.model.request.AuthenticationRequest;
import com.cnpm.muahexanh.model.request.RegisterRequest;
import com.cnpm.muahexanh.model.response.*;
import com.cnpm.muahexanh.repository.UserRepository;
import com.cnpm.muahexanh.repository.VerificationTokenRepository;
import com.cnpm.muahexanh.service.AuthenticationService;
import com.cnpm.muahexanh.service.VerificationTokenService;
import com.cnpm.muahexanh.service.impl.CookieServiceImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth")
public class AuthenticationController {
    private final AuthenticationService service;
    // private final EmailService emailService;
    private final UserRepository userRepository;
    private final VerificationTokenService verificationTokenService;
    // private final CourseMapper courseMapper;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> register(@Valid @RequestBody RegisterRequest request,
            BindingResult bindingResult) {

        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        Map<String, String> errors = new HashMap<>();
        if (bindingResult.hasErrors()) {
            errors = bindingResult.getAllErrors().stream()
                    .collect(Collectors.toMap(
                            error -> ((FieldError) error).getField(),
                            error -> error.getDefaultMessage()));
            apiResponse.error(errors);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
        try {
            // Check if the email or username already exists without sending verification
            // emails
            if (userRepository.findByEmail(request.getEmail()).isPresent()) {
                errors.put("message", "Email has already been used");
                apiResponse.error(errors);
                return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
            }
            if (userRepository.findByName(request.getName()).isPresent()) {
                errors.put("message", "Name has already been used");
                apiResponse.error(errors);
                return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
            }

            // Register the user without email verification
            UserResponse userResponse = service.register(request);
            if (userResponse == null) {
                apiResponse.error(ResponseCode.getError(23));
                return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            // Directly return success response
            apiResponse.ok(userResponse);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);

        } catch (IllegalArgumentException i) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Invalid user role");
            apiResponse.error(error);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            apiResponse.error(ResponseCode.getError(23));
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ApiResponse<LoginResponse>> authenticate(
            @Valid @RequestBody AuthenticationRequest request,
            BindingResult bindingResult,
            HttpServletResponse response) {
        ApiResponse<LoginResponse> apiResponse = new ApiResponse<>();
        Map<String, String> errors = new HashMap<>();
        if (bindingResult.hasErrors()) {
            throw new ValidationException(
                    bindingResult.getAllErrors().stream()
                            .collect(Collectors.toMap(
                                    error -> ((FieldError) error).getField(),
                                    error -> error.getDefaultMessage())));
        }

        try {
            LoginResponse loginResponse = service.authenticate(request, response);
            apiResponse.ok(loginResponse);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (IncorrectPasswordException e) {
            apiResponse.error(ResponseCode.getError(9));
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        service.refreshToken(request, response);
    }

    @GetMapping("/is-expired-verification")
    public ResponseEntity<Object> isExpiredVerification(@RequestParam String token) {
        try {
            User user = verificationTokenService.validateVerificationToken(token)
                    .orElseThrow(() -> new UserNotFoundException("User not found"));
            user.setActivated(true);
            userRepository.save(user);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Token is not expired and now is revoked");
            // return new RedirectView("/api/v1/auth/successful");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
            // return new RedirectView("/api/v1/auth/unsuccessful");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
            // return new RedirectView("/api/v1/auth/unsuccessful");

        }
    }

    // @GetMapping("/send-verification-email/{email}")
    // public ResponseEntity<ApiResponse<Boolean>>
    // sendVerificationEmail(@PathVariable String email) {
    // ApiResponse<Boolean> apiResponse = new ApiResponse<>();

    // User user = userRepository.findByEmail(email).orElse(null);
    // if (user == null) {
    // apiResponse.error(ResponseCode.getError(8));
    // return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    // }
    // if (user.isActivated()) {
    // apiResponse.error(ResponseCode.getError(11));
    // return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    // }
    // verificationTokenService.revokePreviousTokens(user.getUserId());
    // if (emailService.sendEmailToStudent(email))
    // apiResponse.ok(true);
    // else
    // apiResponse.error(ResponseCode.getError(23));
    // return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    // }

    @GetMapping("/successful")
    public ResponseEntity<String> successfulAuthentication() {
        return ResponseEntity.status(HttpStatus.OK).body("Successful authentication");
    }

    @GetMapping("/unsuccessful")
    public ResponseEntity<String> unsuccessfulAuthentication() {
        return ResponseEntity.status(HttpStatus.OK).body("Unsuccessful authentication");
    }

}
