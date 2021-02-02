package com.example.ad;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.ad.domain.Product;
import com.example.ad.domain.ProductType;
import com.example.ad.domain.User;
import com.example.ad.domain.UserType;
import com.example.ad.repo.ProductRepository;
import com.example.ad.repo.UserRepository;

import java.io.ByteArrayOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.example.ad.domain.User;
import com.example.ad.domain.UserType;
import com.example.ad.repo.UserRepository;
import org.springframework.util.Base64Utils;


@SpringBootApplication
public class PuppyWorldApplication {
	
	@Autowired
	ProductRepository prepo;
	
	@Autowired
	UserRepository urepo; 
	


	
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
					data1 = Base64Utils.encode(data1);
			      
			      BufferedImage bImage2 = ImageIO.read(new File("src/main/resources/static/images/2.jpg"));
			      ByteArrayOutputStream bos2 = new ByteArrayOutputStream();
			      ImageIO.write(bImage2, "jpg", bos2 );
			      byte [] data2 = bos2.toByteArray();
			      data2 = Base64Utils.encode(data2);
			      
			      User u1 = new User(UserType.STAFF, "joe", "Joe", "password",
			  			"e0533420@u.nus.edu");
			      
			      User u2 = new User(UserType.STAFF, "ann", "Ann", "password",
			  			"chenyihan@gmail.com");
			      
			      urepo.save(u1);
			      
			      urepo.save(u2);
			      
			 /*     User u1 = urepo.findById(1).get();
			      User u2 = urepo.findById(2).get(); */
			
		
		Product p1 = new Product("Waggie Dog Food 1kg", (float) 34.00, 23, ProductType.FOOD,
				data1, u1);
		
		Product p2 = new Product("Jackie Dog Food 1kg", (float) 68.00, 12, ProductType.FOOD,
				data2, u2);
		
		prepo.save(p1);
		prepo.save(p2); 
		

		
			
			/*
			 * User user1 = new User(UserType.CUSTOMER, "keyin", "Tan Ke Yin", "keyin1994",
			 * "keyint94@gmail.com"); User user2 = new User(UserType.CUSTOMER, "xutian",
			 * "Ho Xu Tian", "xutian2021", "xutian2021@gmail.com"); urepo.save(user1);
			 * urepo.save(user2);
			 */
		};

	}

}
