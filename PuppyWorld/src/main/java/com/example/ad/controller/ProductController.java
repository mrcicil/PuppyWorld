package com.example.ad.controller;

import java.util.ArrayList;
import java.util.Iterator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ad.domain.Product;



@Controller
@RequestMapping("/product")
public class ProductController {

//	public ProductController() {
//		// TODO Auto-generated constructor stub
//	}
	
	@Autowired
	com.example.ad.service.ProductService proservice;
	
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
		
		proservice.saveProduct(product);
		return "forward:/product/list";
	}
	
	@RequestMapping(value="/list")
	public String list(Model model)
	{
		//model.addAttribute("productList", proservice.listAllProducts());
		model.addAttribute("productList", proservice.findAllProducts()); //I used the build in JPA repo
		
		
		return "productlist";
	}
	
	@RequestMapping(value = "/edit/{id}")
	public String editForm(@PathVariable("id") Integer id, Model model) {
		//model.addAttribute("product", proservice.findById(number).get());
		model.addAttribute("product", proservice.findProductById(id));
		return "editProduct";
	}
	
	@RequestMapping(value = "/view/{id}")
	public String view(@PathVariable("id") Integer id, Model model) {
		//model.addAttribute("product", proservice.findById(number).get());
		model.addAttribute("product", proservice.findProductById(id));
		return "product";
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String deleteProduct(@PathVariable("id") Integer id) {
		//Product product = proservice.findById(number).get();
		Product product = proservice.findProductById(id);

		proservice.saveProduct(product);
		return "forward:/product/list";
	}

	@RequestMapping(value = "/confirmEdit")
	public String saveInventory(@ModelAttribute("product") @Valid Product product, 
			BindingResult bindingResult,  Model model) {
		
		if (bindingResult.hasErrors()) 
		{
			
			return "editProduct";
		}
		
		proservice.saveProduct(product);
		return "forward:/product/list";
	}
	
	public String checkError(Product product) {
		String msg = null;
		ArrayList<Product> flist = new ArrayList<Product>();
		flist = (ArrayList<Product>) proservice.findAllProducts();
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
}


