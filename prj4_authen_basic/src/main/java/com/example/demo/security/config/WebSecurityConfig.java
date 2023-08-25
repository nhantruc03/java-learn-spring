package com.example.demo.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.appuser.service.UserService;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig{
    

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        http
            .csrf(csrf-> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api/v*/registration/**")
                .permitAll()
            )
            .formLogin(formLogin -> formLogin
                // .loginPage("/login")
                .permitAll()
            )
            .authenticationProvider(daoAuthenticationProvider())
            .rememberMe(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(bCryptPasswordEncoder);
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }
    

    // @Bean
	// public UserDetailsService userDetailsService() {
	// 	UserDetails user =
	// 		 User.withDefaultPasswordEncoder()
	// 			.username("user")
	// 			.password("password")
	// 			.roles("USER")
	// 			.build();

	// 	return new InMemoryUserDetailsManager(user);
	// }

    // @Bean
    // public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
    //     UserDetails user = User.withUsername("user")
    //         .password(passwordEncoder.encode("password"))
    //         .roles("USER")
    //         .build();

    //     UserDetails admin = User.withUsername("admin")
    //         .password(passwordEncoder.encode("admin"))
    //         .roles("USER", "ADMIN")
    //         .build();

    //     return new InMemoryUserDetailsManager(user, admin);
    // }

    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //     http.authorizeRequests()
    //         .anyRequest()
    //         .authenticated()
    //         .and()
    //         .httpBasic();
    //     return http.build();
    // }

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    //     return encoder;
    // }

}
