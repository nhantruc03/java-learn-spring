package com.example.demo.resistration.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class RegistrationBody {
    
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;

}
