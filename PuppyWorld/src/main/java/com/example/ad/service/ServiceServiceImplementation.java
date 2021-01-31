package com.example.ad.service;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.ad.domain.Service;
import com.example.ad.repo.ServiceRepository;

public class ServiceServiceImplementation implements ServiceService {
	
	@Autowired
	ServiceRepository srepo;

	@Override
	public void saveService(Service service) {
		// TODO Auto-generated method stub
		srepo.save(service);
		
	}

	@Override
	public ArrayList<Service> findAllServices() {
		// TODO Auto-generated method stub
		return (ArrayList<Service>) srepo.findAll();
	}

	@Override
	public Service findServiceById(Long Id) {
		// TODO Auto-generated method stub
		ArrayList<Service> sList = findAllServices();
		Service searchService = null;
		for (Iterator <Service>iterator = sList.iterator(); iterator.hasNext();) {
			Service service = iterator.next();
			if(service.getServiceId() == Id) {
				searchService = service;
			}
		}
		return searchService;
	}

	@Override
	public void deleteServiceById(Long Id) {
		// TODO Auto-generated method stub
		Service searchService = findServiceById(Id);
		srepo.delete(searchService);
	}

}
