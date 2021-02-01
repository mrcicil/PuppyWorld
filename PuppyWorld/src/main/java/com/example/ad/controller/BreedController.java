package com.example.ad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/checkBreed")
public class BreedController {
	
	@RequestMapping(value = "/home")
	public String add() 
	{
		
		return "blog";
	}

}
