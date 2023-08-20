package com.example.demo.appuser.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.repo.UserRepo;
import com.example.demo.resistration.token.ConfirmationToken;
import com.example.demo.resistration.token.ConfirmationTokenService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;



@Service
@AllArgsConstructor
public class UserService implements UserDetailsService{


    private static final String USER_NOT_FOUND_MSG = "User with email %s not found";

    private static final String EMAIL_TAKEN_MSG = "Email already taken";

    private final UserRepo repo;

    private final ConfirmationTokenService confirmationTokenService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       return repo.findyByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }


    @Transactional
    public ConfirmationToken signUpUser(AppUser appUser){

        boolean exist = repo.findyByEmail(appUser.getEmail()).isPresent();

        if(exist){
            throw new IllegalStateException(EMAIL_TAKEN_MSG);
        }

        String encodePassword = bCryptPasswordEncoder.encode(appUser.getPassword());

        appUser.setPassword(encodePassword);

        repo.save(appUser);


        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15),  appUser);


        // send mail

        return confirmationTokenService.save(confirmationToken);
    }


    public int enableAppUser(String email) {
        return repo.enableAppUser(email);
    }
    
}
