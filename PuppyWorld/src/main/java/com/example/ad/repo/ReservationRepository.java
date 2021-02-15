package com.example.ad.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ad.domain.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

}
