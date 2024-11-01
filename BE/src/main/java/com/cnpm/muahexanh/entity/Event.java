package com.cnpm.muahexanh.entity;


import com.cnpm.muahexanh.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventName;
    private String description;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Status status;

    private String location;
    private int numberOfPeople;

    @ManyToOne
    @JoinColumn(name = "local_id")
    private Local local;

    @OneToMany(mappedBy = "event")
    private List<CommunityLeader> communityLeader;

    @OneToOne
    @JoinColumn(name = "accept_id")
    private Appcept appcept;

}
