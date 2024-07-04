package com.airline.booking.dto;

import org.springframework.stereotype.Component;

import com.airline.booking.entity.Booking;

@Component
public class BookingDto{
	
	Long id;
	String airlineName;
	String source;
	String destination;
	String date;
	Double ammount;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAirlineName() {
		return airlineName;
	}
	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Double getAmmount() {
		return ammount;
	}
	public void setAmmount(Double ammount) {
		this.ammount = ammount;
	}
	
	public  Booking toBookingDto(Booking booking) {
		Booking newBooking=new Booking();
		newBooking.setAirlineName(booking.getAirlineName());
		newBooking.setDestination(booking.getDestination());
		newBooking.setSource(booking.getSource());
		newBooking.setDate(booking.getDate());
		newBooking.setAmmount(booking.getAmmount());
		
		return newBooking;
		
	}
}
