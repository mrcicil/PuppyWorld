package com.example.ad.service;

import java.util.ArrayList;

import com.example.ad.domain.Reservation;

public interface ReservationService {
	
	public void saveReservation(Reservation reservation);
	public ArrayList<Reservation> findAllReservations();
	public Reservation findReservationById(Long Id);
	public void deleteReservationById(Long Id);

}
