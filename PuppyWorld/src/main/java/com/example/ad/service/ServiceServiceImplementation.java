package com.example.ad.service;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ad.domain.Services;

import com.example.ad.repo.ServiceRepository;




@Service
@Transactional
public class ServiceServiceImplementation implements ServiceService {
    
    @Autowired
    private ServiceRepository srepo;

 

    @Override
    public void saveService(Services service) {
        // TODO Auto-generated method stub
        srepo.save(service);
        
    }

 

    @Override
    public ArrayList<Services> findAllServices() {
        // TODO Auto-generated method stub
        return (ArrayList<Services>) srepo.findAll();
    }

 

    @Override
    public Services findServiceById(int Id) {
        // TODO Auto-generated method stub
        ArrayList<Services> sList = findAllServices();
        Services searchService = null;
        for (Iterator <Services>iterator = sList.iterator(); iterator.hasNext();) {
            Services service = iterator.next();
            if(service.getServiceId() == Id) {
                searchService = service;
            }
        }
        return searchService;
    }

 

    @Override
    public void deleteServiceById(int Id) {
        // TODO Auto-generated method stub
        Services searchService = findServiceById(Id);
        srepo.delete(searchService);
    }



	@Override
	public ArrayList<Services> searchServiceByKeyword(String keyword) {
		if(keyword!=null) {
			return srepo.searchService(keyword);
		}
		return (ArrayList<Services>) srepo.findAll();
	}




 

}