package com.airline.booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.airline.booking.dto.BookingDto;
import com.airline.booking.repository.BookingRepository;

@Service
public class BookingService {
	
  @Autowired
  public  BookingDto bookingDto;
 
  @Autowired
  public BookingRepository bookingRepository;
//  
//  public Booking createBooking(UserDto userDto,User user) {
//	  
//	  Booking booking=bookingDto.toBookingDto(userDto.getBooking());
//	  booking.setUser(user);
//	  return  bookingRepository.save(booking);
//	 }
}
