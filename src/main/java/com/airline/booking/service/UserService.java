package com.airline.booking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.airline.booking.dto.UserDto;
import com.airline.booking.entity.User;
import com.airline.booking.exception.ResourceIdException;
import com.airline.booking.exception.UserAlreadyExistsException;
import com.airline.booking.repository.UserRepo;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	public UserRepo userRepo;

	@Autowired
	public UserDto userDto;
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	public MailService mailService;

	public UserDto signupUser(User user) {
		Optional<User> existUser = userRepo.findByEmail(user.getEmail());

		if (existUser.isPresent()) {
			throw new UserAlreadyExistsException("User already exist in database" + existUser.get().getEmail());
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole("ROLE_USER");
		User newUser = userRepo.save(user);
//		mailService.SendEmail(studentDb);
		return userDto.toUserDto(newUser);
	}

	public User getUserById(Long id) {
		return userRepo.findById(id)
				.orElseThrow(() -> new  ResourceIdException("user id not present: "));	
	}

	public Optional<User> getUser(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	   Optional<User> userDetail=getUser(username);
	   if (userDetail.isPresent()) {
		return userDetail.get();
	}
		 throw new UsernameNotFoundException("UserName not found: " + username);
	}
	
}
