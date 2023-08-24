package com.example.demo.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticateResponse {
	
	private String token;

}
