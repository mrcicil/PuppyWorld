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
		
		return "serviceList";
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
		
		if(service.getServiceName().isEmpty()) {
			errors.rejectValue("serviceName", "null", "Must be filled");
		}
		if(service.getCharges() == 0) {
			errors.rejectValue("charges", "null", "Must be filled");
		}
		if(service.getServiceDuration() == 0) {
			errors.rejectValue("serviceDuration", "null", "Must be filled");
		}
		
		if (bindingResult.hasErrors()) {
			return "serviceCreate";
		}
		
		sservice.saveService(service);
		
		
		return "redirect:/serviceList";
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
	public String saveReservation(@ModelAttribute("reservation")Reservation reservation, HttpServletRequest request) {
		User user = uservice.findUserByUserName(request.getRemoteUser());
		Services service = sservice.findServiceById(reservation.getService().getServiceId());
		System.out.println("this is the service" + service);
		String mail = "Thank you " + user.getName() + " for booking with us. Your reservation for " + service.getServiceName() + " has been success. Looking forward to see you";
		
		try {
			eservice.sendNotification(mail, user.getEmailAddress());
		} catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		double hours = service.getServiceDuration();
		System.out.println(hours);
		LocalTime endTime = reservation.getReserveTime().plusHours((long) hours);
		reservation.setReserveEnd(endTime);
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
