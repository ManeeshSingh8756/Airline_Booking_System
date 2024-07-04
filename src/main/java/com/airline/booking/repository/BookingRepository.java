package com.airline.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.airline.booking.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long>{

}
