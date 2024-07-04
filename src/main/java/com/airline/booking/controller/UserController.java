package com.airline.booking.controller;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airline.booking.dto.UserDto;
import com.airline.booking.entity.User;
import com.airline.booking.model.ResponseModel;
import com.airline.booking.service.UserService;
import com.airline.booking.util.Messages;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
public class UserController {

	@Autowired
	public UserService userService;

	@Autowired
	private Messages messages;

	@PostMapping("/signup")
	public ResponseEntity<ResponseModel> registerUser(@RequestBody User user) {
		UserDto userDto = userService.signupUser(user);

		return new ResponseEntity<>(new ResponseModel.Builder().status("success")
				.message(messages.getMessage("resource.created", null, Locale.getDefault()))
//				.message(messages.getMessage("email.sent", null, Locale.getDefault()))
				.data("user", userDto)
				.build(), 
				HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseModel> getUser(@PathVariable Long id) {
		User user = userService.getUserById(id);
		return new ResponseEntity<ResponseModel>(new ResponseModel.Builder()
				.status("success")
				.message(messages.getMessage("resource.fetch", null, Locale.getDefault()))
				.data("user", user).build(),
				HttpStatus.OK);
	}

}
