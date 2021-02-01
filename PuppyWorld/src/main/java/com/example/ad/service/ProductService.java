package com.example.ad.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.ad.domain.Product;

@Service
public interface ProductService {
	
	public void saveProduct(Product product);
	public ArrayList<Product> findAllProducts();
	public Product findProductById(Integer Id);
	public void deleteProductById(Integer Id);

}
