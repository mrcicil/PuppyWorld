package com.example.ad.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Services {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int serviceId;
	
//	@Column(length=45,nullable=true)
//	private String logo;
	
//	private String serviceName, serviceDescription;
	
//	private double charges;
	
	//private double serviceDuration;
	
//	@Lob
//	private byte[] serviceImage;
	
//	private ReservationType reservationType;
	
	//private LimitDogs limitDogs;
	
	private ArrayList<String> timeSlots;
	
	@OneToOne
	private Provider provider;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate localDate;

	public Services(ArrayList<String> timeSlots, Provider provider, LocalDate localDate) {
		super();
		this.timeSlots = timeSlots;
		this.provider = provider;
		this.localDate = localDate;
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

	public ArrayList<String> getTimeSlots() {
		return timeSlots;
	}

	public void setTimeSlots(ArrayList<String> timeSlots) {
		this.timeSlots = timeSlots;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public LocalDate getLocalDate() {
		return localDate;
	}

	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}

	@Override
	public String toString() {
		return "Services [serviceId=" + serviceId + ", timeSlots=" + timeSlots + ", provider=" + provider
				+ ", localDate=" + localDate + "]";
	}
	

}
