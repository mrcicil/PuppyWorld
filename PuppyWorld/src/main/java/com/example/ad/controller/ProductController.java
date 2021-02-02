package com.example.ad.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ad.domain.Product;
import com.example.ad.service.ProductService;
import com.example.ad.service.ProductServiceImplementation;

@Controller
public class ProductController {

	@Autowired
	private ProductService pservice;
	
	@Autowired
	public void setPService(ProductServiceImplementation pServiceImpl) {
		this.pservice = pServiceImpl;
	}
	
	@RequestMapping(value = "/createProduct")
	public String add(Model model) 
	{
		Product product = new Product();
		model.addAttribute("product", product);
		return "new_product";
	}
	
	@RequestMapping(value = "/save")
	public String saveProduct(@ModelAttribute("product") @Valid Product product, 
			BindingResult bindingResult,  Model model) {
		pservice.saveProduct(product);
		return "forward:/product/list";
	}
	
	@RequestMapping(value="/list")
	public String list(Model model)
	{
		model.addAttribute("productList", pservice.findAllProducts()); 
		return "product";
	}
	
	@RequestMapping(value = "/edit/{id}")
	public String editForm(@PathVariable("id") int id, Model model) {
		model.addAttribute("product", pservice.findProductById(id));
		return "editProduct";
	}
	
//	@RequestMapping(value = "/delete/{id}")
//	public String deleteProduct(@PathVariable("id") Long id) {
//		//Product product = proservice.findById(number).get();
//		Product product = proservice.findProductById(id);
//		proservice.saveProduct(product);
//		return "forward:/product/list";
//	}
}
