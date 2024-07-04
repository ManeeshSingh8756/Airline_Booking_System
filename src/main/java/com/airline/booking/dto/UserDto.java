package com.airline.booking.dto;

import org.springframework.stereotype.Component;

import com.airline.booking.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class UserDto {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String gender;
	private String address;
	private String contactNumber;
	private String userName;
	private String role;
	
    public UserDto toUserDto(User user) {
    	UserDto userDto=new UserDto();
    	
    	userDto.setId(user.getId());
    	userDto.setFirstName(user.getFirstName());
    	userDto.setLastName(user.getLastName());
    	userDto.setEmail(user.getEmail());
    	userDto.setGender(user.getGender());
    	userDto.setContactNumber(user.getContactNumber());
    	userDto.setAddress(user.getAddress());
    	userDto.setUserName(user.getUsername());
    	userDto.setRole(user.getRole());
		return userDto;
    }
}
