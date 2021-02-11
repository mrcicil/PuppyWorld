package com.example.ad.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Provider {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int providerId;
	
	private String providerName; 
	
	@Column(columnDefinition = "TEXT")
	private String providerDescription;
	
	private float price;
	
	private ReservationType reservationType;
	
	@Lob
	private byte[] providerImage;

	public Provider(String providerName, String providerDescription, float price, ReservationType reservationType,
			byte[] providerImage) {
		super();
		this.providerName = providerName;
		this.providerDescription = providerDescription;
		this.price = price;
		this.reservationType = reservationType;
		this.providerImage = providerImage;
	}

	public Provider() {
		super();
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

	public String getProviderDescription() {
		return providerDescription;
	}

	public void setProviderDescription(String providerDescription) {
		this.providerDescription = providerDescription;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public ReservationType getReservationType() {
		return reservationType;
	}

	public void setReservationType(ReservationType reservationType) {
		this.reservationType = reservationType;
	}

	public byte[] getProviderImage() {
		return providerImage;
	}

	public void setProviderImage(byte[] providerImage) {
		this.providerImage = providerImage;
	}
	
	


}
