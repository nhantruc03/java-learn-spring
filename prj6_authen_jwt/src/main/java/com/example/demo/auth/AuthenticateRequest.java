package com.example.demo.auth;

import lombok.Data;

@Data
public class AuthenticateRequest {
	
	private String email;
	private String password;

}
