package com.example.ad.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Event {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long eventId;
	
	private String eventName, eventDetails;
	
	private LocalDate startDate, endDate;
	private LocalTime startTime, endTime;
	
	private int capacity;
	
	//clarify with YK
	@OneToOne
	private User creator;
	
	@OneToMany 
	private List<User> participants;

}
