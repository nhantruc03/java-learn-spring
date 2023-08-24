package com.example.demo.auth;

import lombok.Data;

@Data
public class ResgisterRequest {
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;

}
