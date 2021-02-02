package com.example.ad.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	public String saveService(@ModelAttribute("service")Services service, @RequestParam("fileImage") MultipartFile multipartFile) throws IllegalStateException, IOException {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		System.out.println(fileName);
		File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
		multipartFile.transferTo(convFile);
		byte[] fileContent = FileUtils.readFileToByteArray(convFile);
		
		service.setServiceImage(fileContent);
		Iservice.saveService(service);
		
		return "redirect:/";
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
