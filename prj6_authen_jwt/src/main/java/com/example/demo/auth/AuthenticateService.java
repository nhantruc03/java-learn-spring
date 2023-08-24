package com.example.demo.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.config.JwtSerivce;
import com.example.demo.entity.Role;
import com.example.demo.entity.UserEntity;
import com.example.demo.repo.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticateService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtSerivce jwtSerivce;
	private final AuthenticationManager authenticationManager;
	
	public AuthenticateResponse register(ResgisterRequest body) {
		// TODO Auto-generated method stub
		
		UserEntity user =  UserEntity.builder()
		.firstName(body.getFirstName())
		.lastName(body.getLastName())
		.email(body.getEmail())
		.password(passwordEncoder.encode(body.getPassword()))
		.role(Role.USER)
		.build();
		
		userRepository.save(user);
		
		String jwtToken = jwtSerivce.generateToken(user);
		
		return AuthenticateResponse.builder().token(jwtToken).build();
	}

	public AuthenticateResponse authenticate(AuthenticateRequest body) {
		authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword())
				);
		
		UserEntity user = userRepository.findByEmail(body.getEmail()).orElseThrow();
		
		String jwtToken = jwtSerivce.generateToken(user);
		
		return AuthenticateResponse.builder().token(jwtToken).build();
	}

}
