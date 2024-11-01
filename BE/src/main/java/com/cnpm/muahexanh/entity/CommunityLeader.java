package com.cnpm.muahexanh.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "community_leader")
public class CommunityLeader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    private String phone;

    @ManyToOne
    @JoinColumn(name = "local_id")
    private Local local;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

}
