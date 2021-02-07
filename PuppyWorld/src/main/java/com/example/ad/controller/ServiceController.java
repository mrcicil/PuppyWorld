package com.example.ad.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.ad.domain.Product;
import com.example.ad.domain.Reservation;
import com.example.ad.domain.Services;
import com.example.ad.domain.User;
import com.example.ad.repo.ServiceRepository;
import com.example.ad.service.EmailService;
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
	private EmailService eservice;
	
	@RequestMapping(value = "/serviceList")
	public String list(Model model, HttpServletRequest request) {
		model.addAttribute("serviceList", sservice.findAllServices());
		Reservation reservation = new Reservation();
		model.addAttribute("reservation", reservation);
		return "serviceList";
	}
	
	@RequestMapping(value = "/reservationSave")
	public String saveReservation(@ModelAttribute("reservation")Reservation reservation) {
	//	User user = uservice.findUserByUserName(request.getRemoteUser());
		Services service = sservice.findServiceById(reservation.getService().getServiceId());
	//	Services service = reservation.getService();
		System.out.println("step 1 " + service);
	//	reservation.setService(service);
		rservice.saveReservation(reservation);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/reservationCreate/{id}")
	public String createReservation(@PathVariable("id") Integer id, Model model) {
		System.out.println(id);
		Services service = sservice.findServiceById(id);
		System.out.println("service" + service.toString());
		model.addAttribute("service", service);
		Reservation reservation = new Reservation();
		reservation.setService(service);
		model.addAttribute("reservation", reservation);
		return "reservationCreate";
	}
//	@RequestMapping("/service")
//	public String viewHomePage(Model model) {
//		List<Services> listService=sservice.findAllServices();
//		model.addAttribute("listService",listService);
//		return "service";
//	}
	
	@RequestMapping("/serviceCreate")
	public String showNewServiceForm(Model model) {
		Services service=new Services();
		model.addAttribute("service",service);
		return "serviceCreate";
	}
	
	@RequestMapping(value="/serviceSave",method=RequestMethod.POST)
	public String saveService(@ModelAttribute("service")Services service, Errors errors, BindingResult bindingResult, @RequestParam("fileImage") MultipartFile multipartFile) throws IllegalStateException, IOException {
		try {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			System.out.println(fileName);
			File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
			multipartFile.transferTo(convFile);
			byte[] fileContent = FileUtils.readFileToByteArray(convFile);
			
			service.setServiceImage(fileContent);
		}
		catch(IllegalStateException e) {
			System.out.println(e.toString());
		}
		catch(IOException e) {
			System.out.println(e.toString());
		}
		
		ArrayList<Services> sList = sservice.findAllServices();
		for (Iterator <Services>iterator = sList.iterator(); iterator.hasNext();) {
			Services service2 =  iterator.next();
			if(service2.getServiceName().equalsIgnoreCase(service.getServiceName())) {
				errors.rejectValue("serviceName", "exist", "Service Exist");
				break;
			}
			
		}
		
//		if(service.getServiceName().isEmpty()) {
//			errors.rejectValue("serviceName", "null", "Must be filled");
//		}
//		if(service.getCharges() == 0) {
//			errors.rejectValue("charges", "null", "Must be filled");
//		}
//		if(service.getServiceDuration() == 0) {
//			errors.rejectValue("serviceDuration", "null", "Must be filled");
//		}
//		
//		if(service.getServiceDuration() > 3) {
//			errors.rejectValue("serviceDuration", "null", "Maximum hours is 3");
//		}
		
		if (bindingResult.hasErrors()) {
			return "serviceCreate";
		}
		
		sservice.saveService(service);
		
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/serviceDelete/{id}")
	public String deleteService(@PathVariable("id") Integer id) {
		sservice.deleteServiceById(id);
		
		return "redirect:/serviceList";
	}
	
	@RequestMapping(value = "/serviceEdit/{id}")
	public String editProduct(@PathVariable("id") Integer id, Model model) {
		
		Services service = sservice.findServiceById(id);
		String encodedString = Base64.getEncoder().encodeToString(service.getServiceImage());
		model.addAttribute("image", encodedString);
		model.addAttribute("service",service);
		return "serviceEdit";
	}
}
