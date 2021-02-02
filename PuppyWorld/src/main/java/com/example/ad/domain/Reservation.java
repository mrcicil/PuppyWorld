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
	private int reservationId;
	
	private LocalDate reserveDate;
	private LocalTime reserveTime;
	
	private int noOfDogs;
	
	private String remarks;

	public Reservation(LocalDate reserveDate, LocalTime reserveTime, int noOfDogs, String remarks) {
		super();
		this.reserveDate = reserveDate;
		this.reserveTime = reserveTime;
		this.noOfDogs = noOfDogs;
		this.remarks = remarks;
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

}
