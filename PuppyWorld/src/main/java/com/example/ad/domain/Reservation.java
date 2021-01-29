package com.example.ad.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long reservationId;
	
	private LocalDate reserveDate;
	private LocalTime reserveTime;
	
	private int noOfDogs;
	
	private String remarks;
}
