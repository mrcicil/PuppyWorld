package com.example.ad.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Services {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long servicesId;
	
	private String servicesName, servicesDescription, servicesDuration, charges;
	
	private byte[] servicesImage;
	
	private ReservationType reservationType;
	
	private LimitDogs limitDogs;

	public Services(String servicesName, String servicesDescription, String servicesDuration, String charges,
			byte[] servicesImage, ReservationType reservationType, LimitDogs limitDogs) {
		super();
		this.servicesName = servicesName;
		this.servicesDescription = servicesDescription;
		this.servicesDuration = servicesDuration;
		this.charges = charges;
		this.servicesImage = servicesImage;
		this.reservationType = reservationType;
		this.limitDogs = limitDogs;
	}

	public long getServicesId() {
		return servicesId;
	}

	public void setServicesId(long servicesId) {
		this.servicesId = servicesId;
	}

	public String getServicesName() {
		return servicesName;
	}

	public void setServicesName(String servicesName) {
		this.servicesName = servicesName;
	}

	public String getServicesDescription() {
		return servicesDescription;
	}

	public void setServicesDescription(String servicesDescription) {
		this.servicesDescription = servicesDescription;
	}

	public String getServicesDuration() {
		return servicesDuration;
	}

	public void setServicesDuration(String servicesDuration) {
		this.servicesDuration = servicesDuration;
	}

	public String getCharges() {
		return charges;
	}

	public void setCharges(String charges) {
		this.charges = charges;
	}

	public byte[] getServicesImage() {
		return servicesImage;
	}

	public void setServicesImage(byte[] serviceImage) {
		this.servicesImage = serviceImage;
	}

	public ReservationType getReservationType() {
		return reservationType;
	}

	public void setReservationType(ReservationType reservationType) {
		this.reservationType = reservationType;
	}

	public LimitDogs getLimitDogs() {
		return limitDogs;
	}

	public void setLimitDogs(LimitDogs limitDogs) {
		this.limitDogs = limitDogs;
	}

}
