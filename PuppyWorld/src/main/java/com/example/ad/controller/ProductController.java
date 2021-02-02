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

import com.example.ad.domain.Product;
import com.example.ad.domain.Services;
import com.example.ad.repo.ServiceRepository;
import com.example.ad.service.ProductService;
import com.example.ad.service.ProductServiceImplementation;
import com.example.ad.service.ServiceService;
import com.example.ad.service.ServiceServiceImplementation;

@Controller
public class ProductController {
	@Autowired
	private ProductService pservice;
	
	@Autowired
	public void setPService(ProductServiceImplementation pServiceImpl) {
		this.pservice = pServiceImpl;
	}
	
//	@RequestMapping("/service")
//	public String viewHomePage(Model model) {
//		List<Services> listService=sservice.findAllServices();
//		model.addAttribute("listService",listService);
//		return "service";
//	}
	
	@RequestMapping("/createProduct")
	public String showNewProductForm(Model model) {
		Product product = new Product();
		model.addAttribute("product",product);
		return "new_product";
	}
	
//	@RequestMapping(value="/save",method=RequestMethod.POST)
//	public String saveService(@ModelAttribute("service")Services service, @RequestParam("fileImage") MultipartFile multipartFile) throws IllegalStateException, IOException {
//		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//		System.out.println(fileName);
//		File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
//		multipartFile.transferTo(convFile);
//		byte[] fileContent = FileUtils.readFileToByteArray(convFile);
//		
//		service.setServiceImage(fileContent);
//		sservice.saveService(service);
//		
//		return "redirect:/";

//	}
}
