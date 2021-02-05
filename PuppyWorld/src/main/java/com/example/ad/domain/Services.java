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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Services {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int serviceId;
	
//	@Column(length=45,nullable=true)
//	private String logo;
	
	private String serviceName, serviceDescription;
	
	private double charges;
	
	//private double serviceDuration;
	
	@Lob
	private byte[] serviceImage;
	
	private ReservationType reservationType;
	
	//private LimitDogs limitDogs;
	
	private ArrayList<String> timeSlots;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate localDate;

	@Override
	public String toString() {
		return "Services [serviceId=" + serviceId + ", serviceName=" + serviceName + ", serviceDescription="
				+ serviceDescription + ", charges=" + charges + ", serviceImage=" + Arrays.toString(serviceImage)
				+ ", reservationType=" + reservationType + ", timeSlots=" + timeSlots + ", localDate=" + localDate
				+ "]";
	}

	public Services() {
		super();
	
	}

	public Services(String serviceName, String serviceDescription, double charges, byte[] serviceImage,
			ReservationType reservationType, ArrayList<String> timeSlots, LocalDate localDate) {
		super();
		this.serviceName = serviceName;
		this.serviceDescription = serviceDescription;
		this.charges = charges;
		this.serviceImage = serviceImage;
		this.reservationType = reservationType;
		this.timeSlots = timeSlots;
		this.localDate = localDate;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
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

	public ArrayList<String> getTimeSlots() {
		return timeSlots;
	}

	public void setTimeSlots(ArrayList<String> timeSlots) {
		this.timeSlots = timeSlots;
	}

	public LocalDate getLocalDate() {
		return localDate;
	}

	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}

	

	

	

}
