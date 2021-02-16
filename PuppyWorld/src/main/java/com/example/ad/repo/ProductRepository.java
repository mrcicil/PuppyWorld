package com.example.ad.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ad.domain.Product;
import com.example.ad.domain.ProductType;


public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	@Query(value = "SELECT * FROM Product p " 
			   +"WHERE p.product_name LIKE %:keyword% " 
			   +"OR p.product_price LIKE %:keyword% " 
			   +"OR p.product_quantity LIKE %:keyword% " 
			   +"OR CONCAT(p.product_type,'') LIKE %:keyword% ",
			   nativeQuery = true)
			public ArrayList<Product> searchProduct(@Param("keyword") String keyword);
	        public ArrayList<Product> findByProductType(@Param("keyword") ProductType productType);


}
