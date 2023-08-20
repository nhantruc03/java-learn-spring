package com.example.demo.resistration.token;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    
    private final ConfirmationTokenRepo repo;

    public ConfirmationToken save(ConfirmationToken token){
        return repo.save(token);
    }

    public ConfirmationToken getToken(String token){
        return repo.findByToken(token).orElseThrow(() -> new IllegalStateException("Token not found"));
    }

    public int setConfirmedAt(String token) {
        return repo.updateConfirmedAt(
                token, LocalDateTime.now());

    }

}
