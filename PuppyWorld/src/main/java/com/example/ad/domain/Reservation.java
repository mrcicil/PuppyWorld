package com.example.ad.domain;

import java.time.LocalDate;
import java.time.LocalTime;

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
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate reserveDate;
	private LocalTime reserveTime;
	private LocalTime reserveEnd;
	
	private int noOfDogs;
	
	private String remarks;
	
	@OneToOne
	private Services service;

	public Reservation(LocalDate reserveDate, LocalTime reserveTime, LocalTime reserveEnd, int noOfDogs, String remarks,
			Services service) {
		super();
		this.reserveDate = reserveDate;
		this.reserveTime = reserveTime;
		this.reserveEnd = reserveEnd;
		this.noOfDogs = noOfDogs;
		this.remarks = remarks;
		this.service = service;
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

	public LocalDate getReserveDate() {
		return reserveDate;
	}

	public void setReserveDate(LocalDate reserveDate) {
		this.reserveDate = reserveDate;
	}

	public LocalTime getReserveTime() {
		return reserveTime;
	}

	public void setReserveTime(LocalTime reserveTime) {
		this.reserveTime = reserveTime;
	}

	public LocalTime getReserveEnd() {
		return reserveEnd;
	}

	public void setReserveEnd(LocalTime reserveEnd) {
		this.reserveEnd = reserveEnd;
	}

	public int getNoOfDogs() {
		return noOfDogs;
	}

	public void setNoOfDogs(int noOfDogs) {
		this.noOfDogs = noOfDogs;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Services getService() {
		return service;
	}

	public void setService(Services service) {
		this.service = service;
	}

	

}
