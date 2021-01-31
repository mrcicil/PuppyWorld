package com.example.ad.service;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.ad.domain.Reservation;
import com.example.ad.repo.ReservationRepository;

public class ReservationServiceImplementation implements ReservationService {
	
	@Autowired
	ReservationRepository rrepo;

	@Override
	public void saveReservation(Reservation reservation) {
		// TODO Auto-generated method stub
		rrepo.save(reservation);
	}

	@Override
	public ArrayList<Reservation> findAllReservations() {
		// TODO Auto-generated method stub
		return (ArrayList<Reservation>) rrepo.findAll();
	}

	@Override
	public Reservation findReservationById(Long Id) {
		// TODO Auto-generated method stub
		ArrayList<Reservation> rList = findAllReservations();
		Reservation searchReservation = null;
		for (Iterator <Reservation> iterator = rList.iterator(); iterator.hasNext();) {
			Reservation reservation = iterator.next();
			if(reservation.getReservationId() == Id) {
				searchReservation = reservation;
			}
		}
		return searchReservation;
	}

	@Override
	public void deleteReservationById(Long Id) {
		// TODO Auto-generated method stub
		Reservation searchReservation = findReservationById(Id);
		rrepo.delete(searchReservation);
	}

}
