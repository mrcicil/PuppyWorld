package com.example.ad.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;


import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.ad.domain.Product;
import com.example.ad.domain.Provider;
import com.example.ad.domain.Reservation;
import com.example.ad.domain.Services;
import com.example.ad.domain.User;
import com.example.ad.repo.ServiceRepository;
import com.example.ad.service.EmailService;
import com.example.ad.service.ProviderService;
import com.example.ad.service.ProviderServiceImplementation;
import com.example.ad.service.ReservationService;
import com.example.ad.service.ReservationServiceImplementation;
import com.example.ad.service.ServiceService;
import com.example.ad.service.ServiceServiceImplementation;
import com.example.ad.service.UserService;
import com.example.ad.service.UserServiceImplementation;

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
	
	@Autowired
	private UserService uservice;
	
	@Autowired
	public void setUService(UserServiceImplementation uServiceImpl) {
		this.uservice = uServiceImpl;
	}
	
	@Autowired
	private ProviderService pvservice;
	
	@Autowired
	public void setPvService(ProviderServiceImplementation pvServiceImpl) {
		this.pvservice = pvServiceImpl;
	}
	
	@Autowired
	private EmailService eservice;
	

	@RequestMapping(value = "/serviceList/{id}")
	public String dateList(@PathVariable("id") Integer id, Model model) {
		ArrayList <Services> sList = sservice.findAllServicesByProviderId(id);
		System.out.println(sList);
		Provider provider = pvservice.findProviderById(id);
		model.addAttribute("serviceList", sList);
		model.addAttribute("provider", provider);
		return "serviceList";
	}

	//jiayuan
//@RequestMapping(value = "/serviceList")
//public String list(Model model) {
//	model.addAttribute("serviceList", sservice.findAllServices());
//	String keyword = "";
//	
//	model.addAttribute("keyword", keyword);
//	return "serviceList";
//}


	

	@RequestMapping("/serviceCreate/{id}")
	public String showNewServiceForm(@PathVariable("id") Integer id, Model model) {
		Services service = new Services();
		Provider provider = pvservice.findProviderById(id);
		service.setProvider(provider);
		model.addAttribute("service",service);
		return "serviceCreate";
	}
	
	@RequestMapping(value = "/reservationSave")
	public String saveReservation(@ModelAttribute("reservation")Reservation reservation, HttpServletRequest request) {
		Services service = sservice.findServiceById(reservation.getService().getServiceId());
		User user = uservice.findUserByUserName(request.getRemoteUser());
		reservation.setUser(user);
		rservice.saveReservation(reservation);
		return "reservationSuccess";
	}
	
	@RequestMapping(value = "/reservationCreate/{id}")
	public String createReservation(@PathVariable("id") Integer id, Model model) {
		Services service = sservice.findServiceById(id);
		model.addAttribute("service", service);
		Reservation reservation = new Reservation();
		reservation.setService(service);
		System.out.println("step 1" + reservation.getService().getLocalDate());
		model.addAttribute("reservation", reservation);
		return "reservationCreate";
	}

	
	@RequestMapping(value="/serviceSave",method=RequestMethod.POST)
	public String saveService(@ModelAttribute("service")Services service, Errors errors, BindingResult bindingResult){
		
		LocalDate date = LocalDate.now();
		if(service.getLocalDate().isBefore(date) || service.getLocalDate().isEqual(date)) {
			errors.rejectValue("localDate", "null", "Cannot be past or present");
		}
		ArrayList<Services> sList = sservice.findAllServices();
		for (Iterator <Services> iterator = sList.iterator(); iterator.hasNext();) {
			Services service1 = iterator.next();
			if(service1.getLocalDate().isEqual(service.getLocalDate()) & service1.getProvider().getProviderId() == service.getProvider().getProviderId()) {
				errors.rejectValue("localDate", "exist", "Timeslot exist");
				break;
			}
			
		}
		
		if (bindingResult.hasErrors()) {
			return "serviceCreate";
		}
		
		sservice.saveService(service);
		
		System.out.println("step 1" + service.getProvider().getProviderId());
		return "redirect:/serviceList/" + service.getProvider().getProviderId();
	}
	
	@RequestMapping(value = "/serviceDelete/{id}")
	public String deleteService(@PathVariable("id") Integer id) {
		Services service = sservice.findServiceById(id);
		sservice.deleteServiceById(id);
		
//		ArrayList<Reservation> rList = rservice.findAllReservations();
//		for (Iterator <Reservation> iterator = rList.iterator(); iterator.hasNext();) {
//			Reservation reservation = iterator.next();
//			if(reservation.getService().getServiceId() == service.getServiceId()) {
//				errors.rejectValue("localDate", "exist", "Timeslot exist");
//			}
//			
//		}
		
		return "redirect:/serviceList/" + service.getProvider().getProviderId();
	}
	
//	@RequestMapping(value = "/serviceEdit/{id}")
//	public String editProduct(@PathVariable("id") Integer id, Model model) {
//		
//		Services service = sservice.findServiceById(id);
//		String encodedString = Base64.getEncoder().encodeToString(service.getServiceImage());
//		model.addAttribute("image", encodedString);
//		model.addAttribute("service",service);
//		return "serviceEdit";
//	}
	
	

	//jiayuan
//	@RequestMapping(value = "/searchService")
//	public String searchService(Model model, @RequestParam("keyword") String keyword) {
//		
//		ArrayList<Services> searchService=sservice.searchServiceByKeyword(keyword);
//		
//		model.addAttribute("serviceList",searchService);
//		model.addAttribute("keyword",keyword);
//		return"serviceList";		

//	}
//	
////	@RequestMapping("/service")
////	public String viewHomePage(Model model) {
////		List<Services> listService=sservice.findAllServices();
////		model.addAttribute("listService",listService);
////		return "service";
////	}
//	
//	@RequestMapping("/serviceCreate")
//	public String showNewServiceForm(Model model) {
//		Services service=new Services();
//		model.addAttribute("service",service);
//		return "serviceCreate";
//	}
}
