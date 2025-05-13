package com.web.ecom_template.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.security.Timestamp;

@Entity
@Table(name = "verification_token")
@Getter
@Setter
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Lob
    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @Column(name = "created_timestamp", nullable = false)
    private Timestamp createdTimestamp;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private LocalUser user;
}
