package com.cnpm.muahexanh.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "local")
@Data
public class Local {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String localName;
    private String localAddress;

    @OneToMany(mappedBy = "local", cascade = CascadeType.ALL)
    private List<CommunityLeader> communityLeaders;

    @OneToMany(mappedBy = "local",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Event> events;


}
