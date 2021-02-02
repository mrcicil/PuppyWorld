package com.example.ad.controller;

import java.security.SecureRandom;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.ad.domain.User;
import com.example.ad.domain.UserType;
import com.example.ad.service.EmailService;
import com.example.ad.service.UserService;
import com.example.ad.service.UserServiceImplementation;

@Controller
public class UserController {
	
	@Autowired
	private UserService uservice;
	
	@Autowired
	private EmailService eservice;
	
	
	@Autowired
	public void setUService(UserServiceImplementation uServiceImpl) {
		this.uservice = uServiceImpl;
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
	public String indexPage() {
		return "index";
	}
	
	
	@RequestMapping (value = "/register")
	public String registerUser (Model model) {
		User newUser = new User();
		model.addAttribute("user", newUser);
		return "register";
	}
	
	
	@RequestMapping(value = "/save")
	public String saveNewUser (@ModelAttribute("user") User user, 
			BindingResult bindingResult, Model model, HttpServletRequest request, Errors errors) {
		if (bindingResult.hasErrors()) {
			return "register";
		}
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
		
		User createUser = new User();
		createUser.setUserType(UserType.CUSTOMER);
		createUser.setName(user.getName());
		createUser.setUserName(user.getUserName());
		createUser.setPassword(encodedPassword);
		createUser.setEmailAddress(user.getEmailAddress());
		uservice.saveUser(createUser);
		
		return "index";
		
	}
	
	@RequestMapping (value = "/profile")
	public String profile (Model model, HttpServletRequest request) {
		
		User currentUser = (User) uservice.findUserByUserName(request.getRemoteUser());
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
			
//			char[] possibleCharacters = (new String("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?")).toCharArray();
		
			
//			String randomStr = RandomStringUtils.random( 15, 0, possibleCharacters.length-1, false, false, possibleCharacters, new SecureRandom() );
//			System.out.println( randomStr );
			
			int length = 15;
//	        String symbol = "-/.^&*_!@%=+>)"; 
	        String cap_letter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
	        String small_letter = "abcdefghijklmnopqrstuvwxyz"; 
	        String numbers = "0123456789"; 


	        String finalString = cap_letter + small_letter + numbers; 

	        Random random = new Random(); 

	        char[] password = new char[length]; 

	        for (int i = 0; i < length; i++) 
	        { 
	            password[i] = 
	                    finalString.charAt(random.nextInt(finalString.length())); 

	        } 
	        
	        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        dbuser.setPassword(passwordEncoder.encode(password.toString()));
	        uservice.saveUser(dbuser);
	        
	        System.out.println(password);
	        System.out.println(passwordEncoder.encode(password.toString()));
			
	        String mail = "The new password is being generated: " + password.toString() + ". Please use this new password to login to your account.";
			eservice.sendNotification(mail, dbuser.getEmailAddress());

			return "login";
		}
		else {

			errors.rejectValue("emailAddress", "email mismatch", "Please input the correct email address");
			
			return "forgetPassword";
			
		}
		
	}
	

}
