package com.example.ad.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ad.domain.Services;



public interface ServiceRepository extends JpaRepository<Services, Integer> {
	
	@Query(value = "SELECT * from services s WHERE s.serviceId = :sId", nativeQuery = true)
	List<Services> searchServiceById (int sId);

	
}
