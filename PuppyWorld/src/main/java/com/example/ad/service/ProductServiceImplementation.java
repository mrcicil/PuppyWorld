package com.example.ad.service;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.ad.domain.Product;
import com.example.ad.repo.ProductRepository;

public class ProductServiceImplementation implements ProductService {
	
	@Autowired
	ProductRepository prepo;

	@Override
	public void saveProduct(Product product) {
		// TODO Auto-generated method stub
		prepo.save(product);
	}

	@Override
	public ArrayList<Product> findAllProducts() {
		// TODO Auto-generated method stub
		return (ArrayList<Product>) prepo.findAll();
	}

	@Override
	public Product findProductById(Long Id) {
		// TODO Auto-generated method stub
		ArrayList<Product> pList = findAllProducts();
		Product searchProduct = null;
		for (Iterator <Product>iterator = pList.iterator(); iterator.hasNext();) {
			Product product = iterator.next();
			if(product.getProductId() == Id) {
				searchProduct = product;
			}
		}
		return searchProduct;
	}

	@Override
	public void deleteProductById(Long Id) {
		// TODO Auto-generated method stub
		Product searchProduct = findProductById(Id);
		prepo.delete(searchProduct);
	}

}