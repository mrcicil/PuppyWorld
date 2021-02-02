package com.example.ad.controller;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.ad.domain.Product;
import com.example.ad.service.ProductService;
import com.example.ad.service.ProductServiceImplementation;






@Controller
@RequestMapping("/product")
public class ProductController {

//	public ProductController() {
//		// TODO Auto-generated constructor stub
//	}
	
	@Autowired
	ProductService pservice;
	
	@Autowired
	public void setPService(ProductServiceImplementation pServiceImpl) {
		this.pservice = pServiceImpl;
	}
	
	@RequestMapping(value = "/add")
	public String add(Model model) 
	{
		model.addAttribute("product", new Product());
		return "product-form";
	}
	
	
	
	@RequestMapping(value = "/save")
	public String saveUser(@ModelAttribute("product") @Valid Product product, 
			BindingResult bindingResult,  Model model) {
		String msg = checkError(product);
		if (bindingResult.hasErrors()||msg!=null) {
			model.addAttribute("message",msg);
			return "product-form";
		}

//		if (proservice.checkProductNameExist(product)) 
//		{
//			return "product-form";
//		}
		
		pservice.saveProduct(product);
		return "forward:/product/list";
	}
	
	@RequestMapping(value="/list")
	public String list(Model model)
	{
		//model.addAttribute("productList", proservice.listAllProducts());
		model.addAttribute("productList", pservice.findAllProducts()); //I used the build in JPA repo
		
		
		return "productlist";
	}
	
	
	
	@RequestMapping(value = "/edit/{id}")
	public String editForm(@PathVariable("id") Integer id, Model model) {
		//model.addAttribute("product", proservice.findById(number).get());
		model.addAttribute("product", pservice.findProductById(id));
		return "editProduct";
	}
	
	@RequestMapping(value = "/view/{id}")
	public String view(@PathVariable("id") Integer id, Model model) {
		//model.addAttribute("product", proservice.findById(number).get());
		model.addAttribute("product", pservice.findProductById(id));
		return "product";
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String deleteProduct(@PathVariable("id") Integer id) {
		//Product product = proservice.findById(number).get();
		Product product = pservice.findProductById(id);

		pservice.saveProduct(product);
		return "forward:/product/list";
	}

	@RequestMapping(value = "/confirmEdit")
	public String saveInventory(@ModelAttribute("product") @Valid Product product, 
			BindingResult bindingResult,  Model model) {
		
		if (bindingResult.hasErrors()) 
		{
			
			return "editProduct";
		}
		
		pservice.saveProduct(product);
		return "forward:/product/list";
	}
	
	public String checkError(Product product) {
		String msg = null;
		ArrayList<Product> flist = new ArrayList<Product>();
		flist = (ArrayList<Product>) pservice.findAllProducts();
		//Product lastProduct = flist.get(flist.size()-1);
		for (Iterator <Product> iterator = flist.iterator(); iterator.hasNext();) {
			Product product2 = iterator.next();
			if(product.getProductId() == product2.getProductId()) {
				msg="Part ID Exists";
				break;
			}
			else if (product.getProductName() == product2.getProductName()) {
				msg="PartName Exists";
				break;
			
		}
		
	}
		return msg;
	}
	
	


	@GetMapping("/product/image/{id}")
	public void showProductImage(int id, HttpServletResponse response) throws IOException {
		response.setContentType("image/jpeg"); // Or whatever format you wanna use

		Product product = pservice.findProductById(id);

		InputStream is = new ByteArrayInputStream(product.getProductImage());
		IOUtils.copy(is, response.getOutputStream());
		
	}
	



	


	
	@RequestMapping("/createProduct")
	public String showNewProductForm(Model model) {
		Product product = new Product();
		model.addAttribute("product",product);
		return "new_product";
	}
	
	@RequestMapping(value="/saveProduct",method=RequestMethod.POST)
	public String saveService(@ModelAttribute("product")Product product, @RequestParam("fileImage") MultipartFile multipartFile) throws IllegalStateException, IOException {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		System.out.println(fileName);
		File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
		multipartFile.transferTo(convFile);
		byte[] fileContent = FileUtils.readFileToByteArray(convFile);
		
		product.setProductImage(fileContent);
		pservice.saveProduct(product);
		
		return "redirect:/";

	}
}

