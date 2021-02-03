package com.example.ad.controller;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.ad.domain.Reservation;
import com.example.ad.domain.Services;
import com.example.ad.repo.ServiceRepository;
import com.example.ad.service.ReservationService;
import com.example.ad.service.ReservationServiceImplementation;
import com.example.ad.service.ServiceService;
import com.example.ad.service.ServiceServiceImplementation;

@Controller
public class ServiceController {
	@Autowired
	private ServiceService sservice;
	
	@Autowired
	public void setSService(ServiceServiceImplementation sServiceImpl) {
		this.sservice = sServiceImpl;
	}
	
	@Autowired
	private ReservationService rservice;
	
	@Autowired
	public void setRService(ReservationServiceImplementation rServiceImpl) {
		this.rservice = rServiceImpl;
	}
	
	@RequestMapping(value = "/serviceList")
	public String list(Model model) {
		model.addAttribute("serviceList", sservice.findAllServices());
		return "serviceList";
	}
	
//	@RequestMapping("/service")
//	public String viewHomePage(Model model) {
//		List<Services> listService=sservice.findAllServices();
//		model.addAttribute("listService",listService);
//		return "service";
//	}
	
	@RequestMapping("/createService")
	public String showNewServiceForm(Model model) {
		Services service=new Services();
		model.addAttribute("service",service);
		return "new_service";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveService(@ModelAttribute("service")Services service, @RequestParam("fileImage") MultipartFile multipartFile) throws IllegalStateException, IOException {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		System.out.println(fileName);
		File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
		multipartFile.transferTo(convFile);
		byte[] fileContent = FileUtils.readFileToByteArray(convFile);
		
		service.setServiceImage(fileContent);
		sservice.saveService(service);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/serviceDelete/{id}")
	public String deleteService(@PathVariable("id") Integer id) {
		sservice.deleteServiceById(id);
		return "redirect:/serviceList";
	}
	
	
	
	@RequestMapping(value = "/serviceReserve/{id}")
	public String reserveService(@PathVariable("id") Integer id, Model model) {
		Services service = new Services();
		service = sservice.findServiceById(id);
		Reservation reservation = new Reservation();
		reservation.setService(service);
		model.addAttribute("service", service);
		model.addAttribute("reservation", reservation);
		String encodedString = Base64.getEncoder().encodeToString(service.getServiceImage());
		model.addAttribute("image", encodedString);
		return "reservationCreate";
	}
	
	@RequestMapping(value = "/reservationSave")
	public String saveReservation(@ModelAttribute("reservation")Reservation reservation) {
		rservice.saveReservation(reservation);
		return "reservationSuccess";
	}
	
	
//	@GetMapping("/search")
//	public String listPage(Model model, @Param("keyword") String keyword) {
//		return searchList(model, 1, keyword);
//	}
//	
//	@GetMapping("/search/page/{pageNumber}")
//	public String searchList(Model model, @PathVariable("pageNumber") int currentPage, @Param("keyword") String keyword) {
//		
//
//		Page<Services> page = sservice.searchService(currentPage, keyword);
//		long totalElements = page.getTotalElements();
//		int totalPages = page.getTotalPages();
//		
//
//		List<Services> invList = page.getContent();
//		
//		model.addAttribute("currentPage", currentPage);
//		model.addAttribute("totalElements", totalElements);
//		model.addAttribute("totalPages", totalPages);
//		model.addAttribute("invList", invList);
//		model.addAttribute("keyword", keyword);
//
//		return "searchservices";
//	}

}
