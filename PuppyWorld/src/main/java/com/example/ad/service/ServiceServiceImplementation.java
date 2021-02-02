package com.example.ad.service;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ad.domain.Services;
import com.example.ad.repo.ServicesRepository;




@Service
public class ServiceServiceImplementation implements ServicesService {
	
	@Autowired
	ServicesRepository srepo;

	@Transactional
	public Page<Services> searchService(int pageNumber, String keyword) {
		int pageElements = 5;
		Pageable pageable = PageRequest.of(pageNumber - 1, pageElements);
		if (keyword != null) {
			return srepo.searchService(keyword, pageable);
		}
		return srepo.findAll(pageable);
	}
	
	@Transactional
	public ArrayList<Services> findAllServices() {
		return (ArrayList<Services>) srepo.findAll();
	}

	
	@Override
	public Services findServiceById(int Id) {
		Services isearch = null;
		ArrayList<Services> iList = new ArrayList<Services>();
		iList = (ArrayList<Services>) srepo.findAll();
		for (Iterator<Services> iterator = iList.iterator(); iterator.hasNext();) {
			Services services = iterator.next();
			if (services.getServiceId()==Id){
				isearch = services;
				break;
			}
		}
		return isearch;
	}

	@Override
	@Transactional
	public Services getServices(int id) {
		return srepo.findById(id).get();
	}

	@Override
	public ArrayList<Services> findByServiceNameLike(String name) {
	   return null;
	}

	@Override
	public Services findByServiceDuration(String sduration) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Services> findByReservationTypeLike(String rtype) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Services findServiceByCharges(String charges) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveServices(Services services) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Services editService(Services services) {
		Services currentservices = srepo.findById((int) services.getServiceId()).get();
		currentservices = services;
		srepo.save(currentservices);
		
		return currentservices;
	}

	@Override
	@Transactional
	public void deleteService(Services services) {
		srepo.delete(services);
		
	}



	



}
