package com.example.ad;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.ad.domain.Product;
import com.example.ad.domain.ProductType;
import com.example.ad.domain.User;
import com.example.ad.domain.UserType;
import com.example.ad.repo.ProductRepository;
import com.example.ad.repo.ServiceRepository;
import com.example.ad.repo.UserRepository;

@SpringBootApplication
public class PuppyWorldApplication {

	@Autowired
	public UserRepository urepo;

	@Autowired
	public ProductRepository prepo;


	public static void main(String[] args) {
		SpringApplication.run(PuppyWorldApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {

			
			
			BufferedImage bImage = ImageIO.read(new File("src/main/resources/static/images/1.jpg"));
		      ByteArrayOutputStream bos = new ByteArrayOutputStream();
		      ImageIO.write(bImage, "jpg", bos );
		      byte [] data1 = bos.toByteArray();
				//data1 = Base64Utils.encode(data1);
		      
		      BufferedImage bImage2 = ImageIO.read(new File("src/main/resources/static/images/2.jpg"));
		      ByteArrayOutputStream bos2 = new ByteArrayOutputStream();
		      ImageIO.write(bImage2, "jpg", bos2 );
		      byte [] data2 = bos2.toByteArray();
		      //data2 = Base64Utils.encode(data2); 
		      
			
		      BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		      String encodedPassword1 = passwordEncoder.encode("password");
		      
		      User u1 = new User(UserType.STAFF, "joe", "Joe", encodedPassword1,
		  			"e0533420@u.nus.edu");
		      
		      User u2 = new User(UserType.STAFF, "ann", "Ann", encodedPassword1,
		  			"chenyihan@gmail.com");
		      
		      urepo.save(u1);
		      
		      urepo.save(u2);
		      
		    
		
	
		      Product p1 = new Product("Waggie Dog Food 1kg", (float) 34.00, 23, ProductType.FOOD,
			data1, u1);
	
		      Product p2 = new Product("Jackie Dog Food 1kg", (float) 68.00, 12, ProductType.FOOD,
			data2, u2);
		      
		      Product p3 = new Product("Jackie Dog Food 1kg", (float) 68.00, 12, ProductType.FOOD,
		  			data2, u2);
		      
		      Product p4 = new Product("Jackie Dog Food 1kg", (float) 68.00, 12, ProductType.FOOD,
		  			data2, u2);
		      
		      Product p5 = new Product("Jackie Dog Food 1kg", (float) 68.00, 12, ProductType.FOOD,
		  			data2, u2);
		      
		      Product p6 = new Product("Jackie Dog Food 1kg", (float) 68.00, 12, ProductType.FOOD,
		  			data2, u2);
		      
		      Product p7 = new Product("Jackie Dog Food 1kg", (float) 68.00, 12, ProductType.FOOD,
		  			data2, u2);
		      
		      Product p8 = new Product("Jackie Dog Food 1kg", (float) 68.00, 12, ProductType.FOOD,
		  			data2, u2);
		      
		      Product p9 = new Product("Jackie Dog Food 1kg", (float) 68.00, 12, ProductType.FOOD,
		  			data2, u2);
		      
		      Product p10 = new Product("Jackie Dog Food 1kg", (float) 68.00, 12, ProductType.FOOD,
		  			data2, u2);
		      
		      Product p11 = new Product("Jackie Dog Food 1kg", (float) 68.00, 12, ProductType.FOOD,
		  			data2, u2);
		      
		      Product p12 = new Product("Jackie Dog Food 1kg", (float) 68.00, 12, ProductType.FOOD,
		  			data2, u2);
		      
		      Product p13 = new Product("Jackie Dog Food 1kg", (float) 68.00, 12, ProductType.FOOD,
		  			data2, u2);
		      
		      Product p14 = new Product("Jackie Dog Food 1kg", (float) 68.00, 12, ProductType.FOOD,
		  			data2, u2);
		      
		      Product p15 = new Product("Jackie Dog Food 1kg", (float) 68.00, 12, ProductType.FOOD,
		  			data2, u2);
		      
		      Product p16 = new Product("Jackie Dog Food 1kg", (float) 68.00, 12, ProductType.FOOD,
		  			data2, u2);
		      
		      Product p17 = new Product("Jackie Dog Food 1kg", (float) 68.00, 12, ProductType.FOOD,
		  			data2, u2);
		      
		      Product p18 = new Product("Jackie Dog Food 1kg", (float) 68.00, 12, ProductType.FOOD,
		  			data2, u2);
		      
		      Product p19 = new Product("Jackie Dog Food 1kg", (float) 68.00, 12, ProductType.FOOD,
		  			data2, u2);
		      
		      Product p20 = new Product("Jackie Dog Food 1kg", (float) 68.00, 12, ProductType.FOOD,
			  			data2, u2);
	
		      prepo.save(p1);
		      prepo.save(p2); 
		      prepo.save(p3);
		      prepo.save(p4);
		      prepo.save(p5);
		      prepo.save(p6);
		      prepo.save(p7);
		      prepo.save(p8);
		      prepo.save(p9);
		      prepo.save(p10);
		      prepo.save(p11);
		      prepo.save(p12);
		      prepo.save(p13);
		      prepo.save(p14);
		      prepo.save(p15);
		      prepo.save(p16);
		      prepo.save(p17);
		      prepo.save(p18);
		      prepo.save(p19);
		      prepo.save(p20);
		      
		      



			


			//dummy11

			

			/*
			 * 
			 * ; String
			 * encodedPassword2 = passwordEncoder.encode("xutian2021");
			 * 
			 * User user1 = new User(UserType.CUSTOMER, "keyin", "Tan Ke Yin",
			 * encodedPassword1, "keyintan94@gmail.com");
			 * 
			 * User user2 = new User(UserType.CUSTOMER, "xutian", "Ho Xu Tian",
			 * encodedPassword2, "xutian2021@gmail.com");
			 * 
			 * urepo.save(user1); urepo.save(user2);
			 */

		};
	}
}

