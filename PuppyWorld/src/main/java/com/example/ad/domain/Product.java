package com.example.ad.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long productId;
	
	private String productName;
	
	private float productPrice;
	
	private int productQuantity;
	
	private ProductType productType;
	
	private byte[] productImage;
	
	@OneToOne
	private User user;

}
