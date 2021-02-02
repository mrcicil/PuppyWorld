package com.example.ad.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ad.domain.User;
import com.example.ad.domain.UserType;
import com.example.ad.service.UserService;
import com.example.ad.service.UserServiceImplementation;

@Controller
public class UserController {
	
	@Autowired
	private UserService uservice;
	
	@Autowired
	public void setUService(UserServiceImplementation uServiceImpl) {
		this.uservice = uServiceImpl;
	}
	
	@RequestMapping(value = "/authenticate")
	public String authenticate (@ModelAttribute("user") User user, 
			BindingResult bindingResult, Model model, HttpSession session, Errors errors) 
	{
		
		if (uservice.findUserByUserName(user.getUserName()) == null)
		{
			errors.rejectValue("userName", "wrong username", "username not found in system");
		}
		else if (!uservice.authenticateUser(user))
		{
			errors.rejectValue("password", "wrong password", "username/ password is incorrect");
		}

		
		if (bindingResult.hasErrors()) {
			return "login";
		}
		
		if (uservice.authenticateUser(user))
		{
			User currentUser = uservice.findUserByUserName(user.getUserName());
			session.setAttribute("usession", currentUser);
			return "index";
		}


		return "forward:/user/login";
	}
	
	@RequestMapping(value = "/login")
	public String login(Model model) 
	{
		User user = new User();
		model.addAttribute("user", user);
		return "login";
	}
	
	@RequestMapping(value = "/logout")
	public String logout(Model model, HttpSession session) 
	{
		session.removeAttribute("usession");
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
			BindingResult bindingResult, Model model, HttpSession session, Errors errors) {
		if (bindingResult.hasErrors()) {
			return "register";
		}
		
		User createUser = new User();
		createUser.setUserType(UserType.CUSTOMER);
		createUser.setName(user.getName());
		createUser.setUserName(user.getUserName());
		createUser.setPassword(user.getPassword());
		createUser.setEmailAddress(user.getEmailAddress());
		uservice.saveUser(createUser);
		
		return "index";
		
	}
	
	@RequestMapping (value = "/profile")
	public String profile (Model model, HttpSession session) {
		
		User currentUser = (User) session.getAttribute("usession");
		model.addAttribute("userProfile", currentUser);
		return "profile";
	}
	
	@RequestMapping (value = "/edit")
	public String editProfile (Model model, HttpSession session) {
		
		User currentUser = (User) session.getAttribute("usession");
		model.addAttribute("user", currentUser);
		return "editProfile";
	}
	
	@RequestMapping(value = "/saveProfile")
	public String saveProfile (@ModelAttribute("user") User user, 
			BindingResult bindingResult, Model model, HttpSession session, Errors errors) {
		if (bindingResult.hasErrors()) {
			return "editProfile";
		}
		
		User editUser = uservice.findUserById(user.getUserId());
		
		if (editUser!=null) {
			editUser.setName(user.getName());
			editUser.setUserName(user.getUserName());
			editUser.setPassword(user.getPassword());
			editUser.setEmailAddress(user.getEmailAddress());
			uservice.saveUser(editUser);
			return "index";
		}
		
		return "editProfile";
		
	}
	
	

}
