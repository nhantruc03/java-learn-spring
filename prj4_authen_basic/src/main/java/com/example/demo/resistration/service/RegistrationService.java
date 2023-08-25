package com.example.demo.resistration.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.role.Role;
import com.example.demo.appuser.service.UserService;
import com.example.demo.resistration.model.RegistrationBody;
import com.example.demo.resistration.token.ConfirmationToken;
import com.example.demo.resistration.token.ConfirmationTokenService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final static String EMAIL_INVALID_MSG = "Email is invalid: %s";

    private final EmailValidator emailValidator;

    private final UserService service;

    private final ConfirmationTokenService confirmationTokenService;

    public ConfirmationToken registration(RegistrationBody body){

        boolean isValidEmail = emailValidator.test(body.getEmail());

        if(!isValidEmail){
            throw new IllegalStateException(String.format(EMAIL_INVALID_MSG, body.getEmail()));
        }

        return service.signUpUser(
            new AppUser(body.getFirstName(), body.getLastName(), body.getEmail(), body.getPassword(), Role.USER)
        );
    }

    public String confirmToken(String token){
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token);

        if(confirmationToken.getConfirmedAt() != null){
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiredAt();
        if(expiredAt.isBefore(LocalDateTime.now())){
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);

        service.enableAppUser(confirmationToken.getAppuser().getEmail());

        return "confirmed";

    }


    

}
