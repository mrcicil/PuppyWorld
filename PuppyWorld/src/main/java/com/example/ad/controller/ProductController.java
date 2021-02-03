package com.example.ad.controller;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	
//	@RequestMapping("/product")
//	public String showAllProduct(Model model) {
//		model.addAttribute("productList", pservice.findAllProducts());
//		return "product";
//	}
	
	@RequestMapping(value="/saveProduct",method=RequestMethod.POST)
	public String saveService(@ModelAttribute("product")Product product, @RequestParam("fileImage") MultipartFile multipartFile) throws IllegalStateException, IOException {
		
		try {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			System.out.println(fileName);
			File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
			multipartFile.transferTo(convFile);
			byte[] fileContent = FileUtils.readFileToByteArray(convFile);
			
			product.setProductImage(fileContent);
		}
		catch(IllegalStateException e) {
			System.out.println(e.toString());
		}
		catch(IOException e) {
			System.out.println(e.toString());
		}
		pservice.saveProduct(product);
		
		return "redirect:/productList";

	}
	
	@RequestMapping(value="/productList")
	public String list(Model model)
	{
		//model.addAttribute("productList", proservice.listAllProducts());
		model.addAttribute("productList", pservice.findAllProducts()); //I used the build in JPA repo
		return "productlist";
	}
	
	@GetMapping("/product/image/{id}")
	public void showProductImage(@PathVariable String id, HttpServletResponse response) throws IOException {
		response.setContentType("image/jpeg"); // Or whatever format you wanna use
		
		int newId = Integer.parseInt(id);
		Product product = pservice.findProductById(newId);

		InputStream is = new ByteArrayInputStream(product.getProductImage());
		IOUtils.copy(is, response.getOutputStream());
		
	}
	
	@RequestMapping(value = "/product/delete/{id}")
	public String deleteProduct(@PathVariable("id") Integer id) {
		
		pservice.deleteProductById(id);
		return "redirect:/productList";
	}
	
	@RequestMapping(value = "/productEdit/{id}")
	public String editProduct(@PathVariable("id") Integer id, Model model, HttpSession session) {
		Product product = pservice.findProductById(id);
		String encodedString = Base64.getEncoder().encodeToString(product.getProductImage());
		session.setAttribute("product", "something");
		model.addAttribute("image", encodedString);
		model.addAttribute("product",product);
		return "edit_product";
	}
}
