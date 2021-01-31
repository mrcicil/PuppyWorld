package com.example.ad.service;

import java.util.ArrayList;

import com.example.ad.domain.Service;

public interface ServiceService {
	
	public void saveService(Service service);
	public ArrayList<Service> findAllServices();
	public Service findServiceById(Long Id);
	public void deleteServiceById(Long Id);

}
