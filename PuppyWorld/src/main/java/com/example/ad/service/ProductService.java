package com.example.ad.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.example.ad.domain.Product;

@Service
public interface ProductService {
	
	public void saveProduct(Product product);
	public ArrayList<Product> findAllProducts();
	public Product findProductById(int Id);
	public void deleteProductById(int Id);

	void convertFileToString(MultipartFile multipartFile) throws IOException;
	void convertStringToImage(String encodedString, String outputFileName) throws IOException;
	
	



}
