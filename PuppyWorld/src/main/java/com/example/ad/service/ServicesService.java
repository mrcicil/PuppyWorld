package com.example.ad.service;

import java.util.ArrayList;

import org.springframework.data.domain.Page;

import com.example.ad.domain.Services;


public interface ServicesService {
	
	public void saveServices(Services services);
	public ArrayList<Services> findAllServices();
	public Services findServiceById(int Id);
	public Services getServices(int id);
	public Services editService(Services services);
	public void deleteService(Services services);
	
	public Page<Services> searchService(int pageNumber, String keyword);
	public ArrayList<Services> findByServiceNameLike(String name);
	public Services findByServiceDuration(String sduration);
	public ArrayList<Services> findByReservationTypeLike(String rtype);

	public Services findServiceByCharges(String charges);


}
