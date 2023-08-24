package com.example.demo.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {
	
	private final AuthenticateService authenticateService;

	@PostMapping("/register")
	public ResponseEntity<AuthenticateResponse> resgister(@RequestBody ResgisterRequest body ){
		
		return new ResponseEntity<>( authenticateService.register(body),HttpStatus.OK);
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticateResponse> authenticate(@RequestBody AuthenticateRequest body ){
		
		
		return new ResponseEntity<>( authenticateService.authenticate(body),HttpStatus.OK);
	}
	

	
}
