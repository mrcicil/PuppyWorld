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
	private long serviceId;
	
	@Column(length=45,nullable=true)
	private String logo;
	
	private String serviceName, serviceDescription, serviceDuration, charges;
	@Lob
	private byte[] serviceImage;
	
	private ReservationType reservationType;
	
	private LimitDogs limitDogs;

	public Services() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

	public Services(String serviceName, String serviceDescription, String serviceDuration, String charges,
			byte[] serviceImage, ReservationType reservationType, LimitDogs limitDogs) {
		super();
		this.serviceName = serviceName;
		this.serviceDescription = serviceDescription;
		this.serviceDuration = serviceDuration;
		this.charges = charges;
		this.serviceImage = serviceImage;
		this.reservationType = reservationType;
		this.limitDogs = limitDogs;
	}

	public long getServiceId() {
		return serviceId;
	}

	public void setServiceId(long serviceId) {
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

	public String getServiceDuration() {
		return serviceDuration;
	}

	public void setServiceDuration(String serviceDuration) {
		this.serviceDuration = serviceDuration;
	}

	public String getCharges() {
		return charges;
	}

	public void setCharges(String charges) {
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

	public LimitDogs getLimitDogs() {
		return limitDogs;
	}

	public void setLimitDogs(LimitDogs limitDogs) {
		this.limitDogs = limitDogs;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

}
