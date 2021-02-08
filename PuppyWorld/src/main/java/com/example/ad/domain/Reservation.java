package com.example.ad.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int reservationId;
	
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	private LocalDate reserveDate;
//	private LocalTime reserveTime;
//	private LocalTime reserveEnd;
//	
//	private int noOfDogs;
//	
//	private String remarks;
	@OneToOne
	private Services service;
	
	private String timeSlot;
	
	@OneToOne
	private User user;

	public Reservation(Services service, String timeSlot, User user) {
		super();
		this.service = service;
		this.timeSlot = timeSlot;
		this.user = user;
	}

	public Reservation() {
		super();
	}

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public Services getService() {
		return service;
	}

	public void setService(Services service) {
		this.service = service;
	}

	public String getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	
	
	

	

}
