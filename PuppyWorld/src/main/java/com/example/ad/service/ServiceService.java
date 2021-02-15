package com.example.ad.service;

import java.util.ArrayList;

import com.example.ad.domain.Services;

public interface ServiceService {
	
	public void saveService(Services service);
	public ArrayList<Services> findAllServices();
	public Services findServiceById(int Id);
	public void deleteServiceById(int Id);
	public ArrayList<Services> findAllServicesByProviderId(int Id);
	
	public ArrayList<Services> searchServiceByKeyword(String keyword);
	public ArrayList<Services> findAllActiveServices(int id);
	

}
