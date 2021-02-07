package com.example.ad.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.ad.domain.Provider;
import com.example.ad.repo.ProviderRepository;

public class ProviderServiceImplementation implements ProviderService {
	
	@Autowired
	ProviderRepository pvrepo;

	@Override
	public void saveProvider(Provider provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Provider> findAllProviders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Provider findProviderById(int Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProviderById(int Id) {
		// TODO Auto-generated method stub
		
	}

}
