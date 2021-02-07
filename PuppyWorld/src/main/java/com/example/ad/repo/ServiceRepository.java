package com.example.ad.repo;


import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ad.domain.Services;



public interface ServiceRepository extends JpaRepository<Services, Integer> {
	
	

	@Query(value = "SELECT * FROM Services s " 
	   +"WHERE s.service_name LIKE %:keyword% " 
	   +"OR s.service_description LIKE %:keyword% " 
	   +"OR s.service_duration LIKE %:keyword% " 
	   +"OR CONCAT(s.charges, '') LIKE %:keyword% " 
	   +"OR s.reservation_type LIKE %:keyword% " 
	   + "OR s.limit_dogs LIKE %:keyword% ",
	   nativeQuery = true)
	public ArrayList<Services> searchService(@Param("keyword") String keyword);

	
	 // @Query (value ="SELECT * FROM services s WHERE s.serviceName=:sName", nativeQuery = true )
	 // public ArrayList<Services> searchService(String keyword);
	 

	/*
	 * @Query (value ="SELECT * FROM services s WHERE s.serviceID=:sId", nativeQuery
	 * = true ) ArrayList<Services> serarchServiceById(int sId);
	 */
	

}
