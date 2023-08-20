package com.example.demo.resistration.token;

import java.time.LocalDateTime;

import com.example.demo.appuser.AppUser;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken {
    
    @Id
    @SequenceGenerator(
        name = "confirmation_sequence",
        sequenceName = "confirmation_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "confirmation_sequence"
    )
    private Long id;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime expiredAt;
    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(
        nullable = false,
        name = "app_user_id"
    )
    private AppUser appuser;


    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiredAt,
            AppUser user) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.appuser = user;
    }


    

}
