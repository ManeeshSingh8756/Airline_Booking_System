package com.airline.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.security.config.Customizer;
import com.airline.booking.service.UserService;


@Configuration
@EnableWebSecurity
@CrossOrigin
public class SecurityConfiger {
	
//	 @Autowired
//	    private JwtAuthenticationEntryPoint point;
	    
	    @Autowired
	    private RequestFilter requestFilter;
	    
	    @Bean
	    public UserDetailsService userDetailsService() {
	        return new UserService();
	    }
	
	
	  @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
	        return httpSecurity
	                .cors(Customizer.withDefaults()) // Apply CORS
	                .csrf(csrf -> csrf.disable()) // Disable CSRF protection
	                .authorizeHttpRequests(auth -> auth
	                        .requestMatchers("/user/signup","/user/login")
	                        .permitAll()// Permit all requests to certain URLs
	                        .anyRequest().authenticated()) // Require authentication for all other requests
	                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Set session management to stateless
	                .authenticationProvider(authenticationProvider()) // Register the authentication provider
	                .addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class) // Add the JWT filter before processing the request
	                .build();
	    }
    
	 @Bean
	    PasswordEncoder passwordEncoder() {
	        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	    }
	 @Bean
	    public AuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
	        authenticationProvider.setUserDetailsService(userDetailsService());
	        authenticationProvider.setPasswordEncoder(passwordEncoder());
	        return authenticationProvider;
	    }
	 @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	        return config.getAuthenticationManager();
	    }

}
