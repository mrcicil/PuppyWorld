package com.example.ad.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

//import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.ad.domain.User;
import com.example.ad.domain.Reservation;
import com.example.ad.domain.Role;
import com.example.ad.domain.Services;
import com.example.ad.domain.Status;
import com.example.ad.service.EmailService;
import com.example.ad.service.ReservationService;
import com.example.ad.service.ReservationServiceImplementation;
import com.example.ad.service.ServiceService;
import com.example.ad.service.ServiceServiceImplementation;
import com.example.ad.service.UserService;
import com.example.ad.service.UserServiceImplementation;

@Controller
public class UserController{
	
	@Autowired
	private UserService uservice;
	
	@Autowired
	private EmailService eservice;
	
	@Autowired
	private ReservationService rservice;
	
	@Autowired
	public void setRService(ReservationServiceImplementation rServiceImpl) {
		this.rservice = rServiceImpl;
	}
	
	@Autowired
	public void setUService(UserServiceImplementation uServiceImpl) {
		this.uservice = uServiceImpl;
	}
	
	@Autowired
	private ServiceService sservice;
	
	@Autowired
	public void setSService(ServiceServiceImplementation sServiceImpl) {
		this.sservice = sServiceImpl;
	}
	
	
	/*
	 * @RequestMapping(value = "/authenticate", method=RequestMethod.POST) public
	 * String authenticate (Model model, @ModelAttribute("password") String
	 * password,@ModelAttribute("username") String username, HttpSession session) {
	 * User existingUser = uservice.findUserByUserName(username); if (existingUser
	 * != null) { // Use encoder.matches to compare raw password with encrypted
	 * password BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	 * 
	 * if (encoder.matches(password, existingUser.getPassword())){ // Successfully
	 * logged in session.setAttribute("usession", existingUser); return "index";
	 * 
	 * } else { // Wrong password model.addAttribute("message",
	 * "Incorrect password. Try again."); return "login"; } } else {
	 * model.addAttribute("message", "The username provided does not exist!");
	 * return "login"; } }
	 */
	 
	
	
		
		@GetMapping("login")
		public String login(Model model) 
		{ 
			  return "login";
		  
		}
		 
	 
	
	/*
	 * @RequestMapping(value = "/logout") public String logout(Model model,
	 * HttpServletRequest request) { session.removeAttribute("usession"); return
	 * "index"; }
	 */
		
	@RequestMapping(value="/")
	public String indexPage(HttpServletRequest request, Model model) {
		User user = uservice.findUserByUserName(request.getRemoteUser());
		updateReservationAndServices();
		model.addAttribute("user", user);
		return "index";
	}
	
	public void updateReservationAndServices() {
		ArrayList<Reservation> rList = rservice.findAllReservations();
		LocalDate date = LocalDate.now();
		for (Iterator <Reservation>iterator = rList.iterator(); iterator.hasNext();) {
			Reservation reservation = iterator.next();
			if(reservation.getService().getLocalDate().isBefore(date)) {
				reservation.setStatus(Status.INACTIVE);
				rservice.saveReservation(reservation);
			}
		}
		ArrayList<Services> sList = sservice.findAllServices();
		for (Iterator <Services> iterator = sList.iterator(); iterator.hasNext();) {
			Services services = iterator.next();
			if(services.getLocalDate().isBefore(date)) {
				services.setStatus(Status.INACTIVE);
				sservice.saveService(services);
			}
			
		}
		
	}
	
	
	@RequestMapping (value = "/register")
	public String registerUser (Model model) {
		User newUser = new User();
		model.addAttribute("user", newUser);
		return "register";
	}
	
	@RequestMapping (value = "/registerStaff")
	public String registerStaff (Model model) {
		User newUser = new User();
		model.addAttribute("user", newUser);
		return "registerStaff";
	}
	
	
	@RequestMapping(value = "/saveNewUser")
	public String saveNewUser (@ModelAttribute("user") User user, 
			BindingResult bindingResult, Model model, HttpServletRequest request, Errors errors) {
		if (bindingResult.hasErrors()) {
			return "register";
		}
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
		
		User createUser = new User();
		createUser.setUserType(Role.ROLE_USER);
		createUser.setName(user.getName());
		createUser.setUserName(user.getUserName());
		createUser.setPassword(encodedPassword);
		createUser.setEmailAddress(user.getEmailAddress());
		uservice.saveUser(createUser);
		
		return "login";
		
	}
	
	
	@RequestMapping(value = "/saveNewStaff")
	public String saveNewStaff (@ModelAttribute("user") User user, 
			BindingResult bindingResult, Model model, HttpServletRequest request, Errors errors) {
		if (bindingResult.hasErrors()) {
			return "registerStaff";
		}
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
		
		User createUser = new User();
		createUser.setUserType(Role.ROLE_ADMIN);
		createUser.setName(user.getName());
		createUser.setUserName(user.getUserName());
		createUser.setPassword(encodedPassword);
		createUser.setEmailAddress(user.getEmailAddress());
		uservice.saveUser(createUser);
		
		return "login";
		
	}
	
	
	@RequestMapping (value = "/profile")
	public String profile (Model model, HttpServletRequest request) {
		User currentUser = (User) uservice.findUserByUserName(request.getRemoteUser());
		int userId = currentUser.getUserId();
		ArrayList<Reservation> display = rservice.findAllReservationsByUserId(userId);
		
		Collections.sort(display, Reservation.ReserveComparator);

		model.addAttribute("reservationList", display);
		model.addAttribute("userProfile", currentUser);
		return "profile";
	}
	
	
	
	@RequestMapping (value = "/edit")
	public String editProfile (Model model, HttpServletRequest request) {
		
		User currentUser = (User) uservice.findUserByUserName(request.getRemoteUser());
		model.addAttribute("user", currentUser);
		return "editProfile";
	}
	
	@RequestMapping(value = "/saveProfile")
	public String saveProfile (@ModelAttribute("user") User user, 
			BindingResult bindingResult, Model model, HttpServletRequest request, Errors errors) {
		if (bindingResult.hasErrors()) {
			return "editProfile";
		}
		
		User editUser = uservice.findUserById(user.getUserId());
		
		if (editUser!=null) {
			
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        String encodedPassword = passwordEncoder.encode(user.getPassword());
	        
			editUser.setName(user.getName());
			editUser.setUserName(user.getUserName());
			editUser.setPassword(encodedPassword);
			editUser.setEmailAddress(user.getEmailAddress());
			uservice.saveUser(editUser);
			return "index";
		}
		
		return "editProfile";
		
	}
	
	
	@RequestMapping(value = "/forgetPassword", method=RequestMethod.GET)
	public ModelAndView forgetPassword(ModelAndView modelAndView, User user) {
		modelAndView.addObject("user",user);
		modelAndView.setViewName("forgetPassword");
		return modelAndView;
	}
	
	@RequestMapping(value = "/passwordRegenerate")
	public String passwordRegenerate(@ModelAttribute("user") User user, 
			BindingResult bindingResult, Model model, HttpServletRequest request, Errors errors){
		
		User dbuser = uservice.findUserByUserEmail(user.getEmailAddress());
		
		if (dbuser != null) {
			
			int length = 15;
//	        String symbol = "-/.^&*_!@%=+>)"; 
	        String cap_letter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
	        String small_letter = "abcdefghijklmnopqrstuvwxyz"; 
	        String numbers = "0123456789"; 


	        String finalString = cap_letter + small_letter + numbers; 

	        Random random = new Random(); 

	        String password = ""; 

	        for (int i = 0; i < length; i++) 
	        { 
	            password = password + finalString.charAt(random.nextInt(finalString.length())); 

	        } 
	        
	        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        dbuser.setPassword(passwordEncoder.encode(password));
	        uservice.saveUser(dbuser);
	        
	        System.out.println(password);
	        System.out.println(passwordEncoder.encode(password));
			
	        String mail = "The new password is being generated: " + password + ". Please use this new password to login to your account.";
			
	        try {
	        	eservice.sendNotification(mail, dbuser.getEmailAddress());
			} catch (MessagingException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        System.out.println("Done");

			return "login";
		}
		else {

			errors.rejectValue("emailAddress", "email mismatch", "Please input the correct email address");
			
			return "forgetPassword";
			
		}
		
	}

	
	

}
