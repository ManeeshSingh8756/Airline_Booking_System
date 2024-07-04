package com.airline.booking.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airline.booking.dto.UserDto;
import com.airline.booking.entity.User;
import com.airline.booking.model.ResponseModel;
import com.airline.booking.service.UserService;
import com.airline.booking.util.JwtUtil;
import com.airline.booking.util.Messages;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
public class UserAuth {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDto userDto;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private Messages messages;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@PostMapping("/login")
	public ResponseEntity<ResponseModel> userLogin(@RequestBody User user) {
	    String token=doAuthenticate(user.getEmail(), user.getPassword());
	    UserDetails userDetails	=userService.loadUserByUsername(user.getEmail());
	    User userDb=userService.getUser(userDetails.getUsername()).get();
	    
	    return new ResponseEntity<ResponseModel>(new ResponseModel.Builder()
	    		.status("success")
	    		.message(messages.getMessage("resource.fetch", null, Locale.getDefault()))
	    		.data("user",userDto.toUserDto(userDb))
         		.data("token", token)
	    		.build(),HttpStatus.OK);	
	}
	
	
	public String doAuthenticate(String email, String password) {
		Authentication authentication	=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		if(authentication.isAuthenticated()) {
			return jwtUtil.generateToken(email);
		}
		throw new UsernameNotFoundException("Invalid user request");
	}

}
