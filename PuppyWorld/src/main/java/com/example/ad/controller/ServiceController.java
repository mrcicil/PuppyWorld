package com.example.ad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.example.ad.domain.Services;
import com.example.ad.repo.ServiceRepository;
import com.example.ad.service.ServiceServiceImplementation;

@Controller
public class ServiceController {
	@Autowired
	private ServiceServiceImplementation Iservice;
	
	@Autowired
	private ServiceRepository repo;
	
	@RequestMapping("/service")
	public String viewHomePage(Model model) {
		List<Services> listService=Iservice.findAllServices();
		model.addAttribute("listService",listService);
		return "service";
	}
	
	@RequestMapping("/new")
	public String showNewServiceForm(Model model) {
		Services service=new Services();
		model.addAttribute("service",service);
		return "new_service";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveService(@ModelAttribute("service")Services service) {
		Iservice.saveService(service);
		
		return "redirect:/";
	}
}
