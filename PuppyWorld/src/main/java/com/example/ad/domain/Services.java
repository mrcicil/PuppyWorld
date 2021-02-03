package com.example.ad.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Services {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int serviceId;
	
	@Column(length=45,nullable=true)
	private String logo;
	
	private String serviceName, serviceDescription;
	
	private double charges;
	
	private double serviceDuration;
	
	@Lob
	private byte[] serviceImage;
	
	private ReservationType reservationType;
	
	private LimitDogs limitDogs;

	public Services(String logo, String serviceName, String serviceDescription, double charges, double serviceDuration,
			byte[] serviceImage, ReservationType reservationType, LimitDogs limitDogs) {
		super();
		this.logo = logo;
		this.serviceName = serviceName;
		this.serviceDescription = serviceDescription;
		this.charges = charges;
		this.serviceDuration = serviceDuration;
		this.serviceImage = serviceImage;
		this.reservationType = reservationType;
		this.limitDogs = limitDogs;
	}

	public Services() {
		super();
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceDescription() {
		return serviceDescription;
	}

	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}

	public double getCharges() {
		return charges;
	}

	public void setCharges(double charges) {
		this.charges = charges;
	}

	public double getServiceDuration() {
		return serviceDuration;
	}

	public void setServiceDuration(double serviceDuration) {
		this.serviceDuration = serviceDuration;
	}

	public byte[] getServiceImage() {
		return serviceImage;
	}

	public void setServiceImage(byte[] serviceImage) {
		this.serviceImage = serviceImage;
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
