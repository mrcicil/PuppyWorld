package com.example.ad.service;

import java.util.ArrayList;

import com.example.ad.domain.Product;

public interface ProductService {
	
	public void saveProduct(Product product);
	public ArrayList<Product> findAllProducts();
	public Product findProductById(int Id);
	public void deleteProductById(int Id);

}
