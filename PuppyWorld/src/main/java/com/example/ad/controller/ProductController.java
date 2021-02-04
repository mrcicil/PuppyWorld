package com.example.ad.controller;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.ad.domain.Product;
import com.example.ad.domain.Services;
import com.example.ad.domain.User;
import com.example.ad.repo.ServiceRepository;
import com.example.ad.service.ProductService;
import com.example.ad.service.ProductServiceImplementation;
import com.example.ad.service.ServiceService;
import com.example.ad.service.ServiceServiceImplementation;
import com.example.ad.service.UserService;
import com.example.ad.service.UserServiceImplementation;

@Controller
public class ProductController {
	@Autowired
	private ProductService pservice;
	
	@Autowired
	public void setPService(ProductServiceImplementation pServiceImpl) {
		this.pservice = pServiceImpl;
	}
	
	@Autowired
	private UserService uservice;
	
	@Autowired
	public void setUService(UserServiceImplementation uServiceImpl) {
		this.uservice = uServiceImpl;
	}

	
//	@RequestMapping("/service")
//	public String viewHomePage(Model model) {
//		List<Services> listService=sservice.findAllServices();
//		model.addAttribute("listService",listService);
//		return "service";
//	}
	
	@RequestMapping("/productCreate")
	public String showNewProductForm(Model model, HttpServletRequest request) {
		Product product = new Product();
		model.addAttribute("product",product);
		return "productCreate";
	}
	
//	@RequestMapping("/product")
//	public String showAllProduct(Model model) {
//		model.addAttribute("productList", pservice.findAllProducts());
//		return "product";
//	}
	
	@RequestMapping(value="/productSave",method=RequestMethod.POST)
	public String saveService(@ModelAttribute("product")Product product, Errors errors, BindingResult bindingResult, @RequestParam("fileImage") MultipartFile multipartFile, HttpServletRequest request) throws IllegalStateException, IOException {
		
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
		
		ArrayList<Product> pList = pservice.findAllProducts();
		for (Iterator <Product>iterator = pList.iterator(); iterator.hasNext();) {
			Product product2 =  iterator.next();
			if(product2.getProductName().equalsIgnoreCase(product.getProductName())) {
				errors.rejectValue("productName", "exist", "Product Exist");
				break;
			}
			
		}
		
		if(product.getProductName().isEmpty()) {
			errors.rejectValue("productName", "null", "Must be filled");
		}
		if(product.getProductPrice() == 0) {
			errors.rejectValue("productPrice", "null", "Must be filled");
		}
		if(product.getProductQuantity() == 0) {
			errors.rejectValue("productQuantity", "null", "Must be filled");
		}
		
		if (bindingResult.hasErrors()) {
			return "productCreate";
		}
		
		User user = uservice.findUserByUserName(request.getRemoteUser());
		product.setUser(user);
		pservice.saveProduct(product);
		
		return "redirect:/productList";

	}
	
	@RequestMapping(value="/productList") // ???
	public String list(Model model, HttpServletRequest request)
	{
		
		User currentUser = uservice.findUserByUserName(request.getRemoteUser());
		model.addAttribute("user", currentUser);
		//model.addAttribute("productList", proservice.listAllProducts());
		model.addAttribute("productList", pservice.findAllProducts()); //I used the build in JPA repo
		
		int productId= 0;
		
		model.addAttribute("productId", productId);
		
		return "productList";
	}
	
	@GetMapping("/product/image/{id}")
	public void showProductImage(@PathVariable String id, HttpServletResponse response) throws IOException {
		response.setContentType("image/jpeg"); // Or whatever format you wanna use
		
		int newId = Integer.parseInt(id);
		Product product = pservice.findProductById(newId);

		InputStream is = new ByteArrayInputStream(product.getProductImage());
		IOUtils.copy(is, response.getOutputStream());
		
	}
	
	@RequestMapping(value = "/productDelete/{id}")
	public String deleteProduct(@PathVariable("id") Integer id) {
		pservice.deleteProductById(id);
		return "redirect:/productList";
	}
	
	@RequestMapping(value = "/productEdit/{id}")
	public String editProduct(@PathVariable("id") Integer id, Model model) {
		Product product = pservice.findProductById(id);
		String encodedString = Base64.getEncoder().encodeToString(product.getProductImage());
		model.addAttribute("image", encodedString);
		model.addAttribute("product",product);
		return "productEdit";
	}
}
