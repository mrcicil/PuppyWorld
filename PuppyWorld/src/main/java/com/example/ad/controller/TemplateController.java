package com.example.ad.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.ad.service.EventService;
import com.example.ad.service.EventServiceImplementation;
import com.example.ad.service.PostCommentService;
import com.example.ad.service.PostCommentServiceImplementation;
import com.example.ad.service.PostService;
import com.example.ad.service.PostServiceImplementation;
import com.example.ad.service.ProductService;
import com.example.ad.service.ProductServiceImplementation;
import com.example.ad.service.ReservationService;
import com.example.ad.service.ReservationServiceImplementation;
import com.example.ad.service.ServiceServiceImplementation;
import com.example.ad.service.UserService;
import com.example.ad.service.UserServiceImplementation;

public class TemplateController {
	
	@Autowired
	private EventService eservice;
	
	@Autowired
	public void setEService(EventServiceImplementation eServiceImpl) {
		this.eservice = eServiceImpl;
	}
	
	@Autowired
	private PostCommentService pcservice;
	
	@Autowired
	public void setPCService(PostCommentServiceImplementation pcServiceImpl) {
		this.pcservice = pcServiceImpl;
	}
	
	@Autowired
	private PostService ptservice;
	
	@Autowired
	public void setPTService(PostServiceImplementation ptServiceImpl) {
		this.ptservice = ptServiceImpl;
	}
	
	@Autowired
	private ProductService pservice;
	
	@Autowired
	public void setPService(ProductServiceImplementation pServiceImpl) {
		this.pservice = pServiceImpl;
	}
	
	@Autowired
	private ReservationService rservice;
	
	@Autowired
	public void setRService(ReservationServiceImplementation rServiceImpl) {
		this.rservice = rServiceImpl;
	}
	
	@Autowired
	public void setSService(ServiceServiceImplementation sServiceImpl) {
	}
	
	@Autowired
	private UserService uservice;
	
	@Autowired
	public void setUService(UserServiceImplementation uServiceImpl) {
		this.uservice = uServiceImpl;
	}

}
