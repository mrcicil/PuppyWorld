package com.example.ad.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Service {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long serviceId;
	
	private String serviceName, serviceDescription, serviceDuration, charges;
	
	private byte[] serviceImage;
	
	private ReservationType reservationType;
	
	private LimitDogs limitDogs;

}
