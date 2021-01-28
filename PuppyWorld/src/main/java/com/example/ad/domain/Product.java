package com.example.ad.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long productId;
	
	private String productName;
	
	private float productPrice;
	
	private int productQuantity;
	
	private ProductCategory productCategory;
	
	private byte[] productImage;
	
	private User user;

}
