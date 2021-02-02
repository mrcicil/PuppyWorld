package com.example.ad.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.example.ad.domain.Product;
import com.example.ad.repo.ProductRepository;
import java.io.ByteArrayInputStream;
import java.io.File;

@Service
@Transactional
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
	public Product findProductById(int Id) {
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
	public void deleteProductById(int Id) {
		// TODO Auto-generated method stub
		Product searchProduct = findProductById(Id);
		prepo.delete(searchProduct);
	}
	
	
	
	
	
	

}
