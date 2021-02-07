package com.example.ad.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Provider {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int providerId;
	
	private String providerName;

	public Provider() {
		super();
	}

	public Provider(int providerId, String providerName) {
		super();
		this.providerId = providerId;
		this.providerName = providerName;
	}

	public int getProviderId() {
		return providerId;
	}

	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

}
