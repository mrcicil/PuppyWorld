package com.example.ad.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ad.domain.Services;
import com.example.ad.service.ServiceServiceImplementation;
import com.example.ad.service.ServicesService;


@Controller
@RequestMapping("/services")
public class ServiceController {
	
	@Autowired
	private ServicesService sservice;
	
	@Autowired
	public void setSService(ServiceServiceImplementation sServiceImpl) {
		this.sservice = sServiceImpl;
	}
	
	@GetMapping("/search")
	public String listPage(Model model, @Param("keyword") String keyword) {
		return searchList(model, 1, keyword);
	}
	
	@GetMapping("/search/page/{pageNumber}")
	public String searchList(Model model, @PathVariable("pageNumber") int currentPage, @Param("keyword") String keyword) {
		

		Page<Services> page = sservice.searchService(currentPage, keyword);
		long totalElements = page.getTotalElements();
		int totalPages = page.getTotalPages();
		

		List<Services> invList = page.getContent();
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalElements", totalElements);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("invList", invList);
		model.addAttribute("keyword", keyword);

		return "searchservices";
	}
	

	

	

}
