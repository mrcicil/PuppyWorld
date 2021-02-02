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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.ad.domain.Services;
import com.example.ad.repo.ServiceRepository;
import com.example.ad.service.ServiceService;
import com.example.ad.service.ServiceServiceImplementation;

@Controller
public class ServiceController {
	@Autowired
	private ServiceService sservice;
	
	@Autowired
	private ServiceServiceImplementation sServiceImpl;
	
	@Autowired
	public void setSService(ServiceServiceImplementation sServiceImpl) {
		this.sservice = sServiceImpl;
	}
	
	@RequestMapping("/service")
	public String viewHomePage(Model model) {
		List<Services> listService=sservice.findAllServices();
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
		sservice.saveService(service);
		
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{serviceId}")
	public ModelAndView showEditServiceForm(@PathVariable(name="serviceId") int id){
		ModelAndView mav=new ModelAndView("edit_service");	
		Services service=sservice.findServiceById(id);
		mav.addObject("service", service);
		return mav;
	}
	
	@RequestMapping("/delete/{serviceId}")
	public String deleteServiceForm(@PathVariable(name="serviceId") int id){
		sservice.deleteServiceById(id);
		return "redirect:/service";
	}
}
