package com.example.demo.resistration.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.resistration.model.RegistrationBody;
import com.example.demo.resistration.service.RegistrationService;
import com.example.demo.resistration.token.ConfirmationToken;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("api/v1/registration")
@AllArgsConstructor
public class RegistrationController {
    
    private final RegistrationService service;
    

    @PostMapping("/register")
    public ResponseEntity<ConfirmationToken> registration(@RequestBody RegistrationBody body){
        return new ResponseEntity<>(service.registration(body),HttpStatus.OK);

    }

    
    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return service.confirmToken(token);
    }
    
}
