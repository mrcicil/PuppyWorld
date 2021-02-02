package com.example.ad.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ad.domain.Services;


public interface ServicesRepository extends JpaRepository<Services,Integer> {
	
	 @Query(value = "SELECT * FROM Service s "
		 		+ "WHERE s.service_name LIKE %:keyword% "
		 		+ "OR s.service_duration LIKE %:keyword% "
		 		+ "OR CONCAT(s.servicedescription, '') LIKE %:keyword% "
		 		+ "OR CONCAT(s.serviceId, '') LIKE %:keyword% "	 		
		 		+ "OR s.service_charges LIKE %:keyword% "
		 		+ "OR CONCAT(s.servicesImage, '') LIKE %:keyword% "
		 		+ "OR s.service_duration LIKE %:keyword% ",
				 nativeQuery = true)
			public Page<Services> searchService(@Param("keyword") String keyword, Pageable pageable);
	 
	

}
