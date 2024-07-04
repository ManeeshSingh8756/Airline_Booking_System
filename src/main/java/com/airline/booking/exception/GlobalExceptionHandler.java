package com.airline.booking.exception;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.airline.booking.model.ResponseModel;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@Autowired
	private MessageSource messages;

	@ExceptionHandler(ResourceIdException.class)
	public ResponseEntity<ResponseModel> handleNotFoundException(ResourceIdException ex) {
		return new ResponseEntity<>(new ResponseModel.Builder().status("BAD_REQUEST")
			   .message(messages.getMessage("user.id.not.present",null, Locale.getDefault()))
			   .build(),
				HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<ResponseModel> idNotFoundException(UserAlreadyExistsException ex) {
		return new ResponseEntity<>(new ResponseModel.Builder().status("Bad Request")
			   .message(messages.getMessage("resource.not.created", null, Locale.getDefault())).build(),
				HttpStatus.BAD_REQUEST);
	}

}
