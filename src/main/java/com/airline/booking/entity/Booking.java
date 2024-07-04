package com.airline.booking.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String airlineName;
	String source;
	String destination;
	String date;
	Double ammount;
	
//	@ManyToOne()
//	@JoinColumn(name = "user_id")
//	@JsonBackReference
//	private User user;
	
	
	
}
