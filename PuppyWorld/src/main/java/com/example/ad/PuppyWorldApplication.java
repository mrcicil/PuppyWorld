package com.example.ad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.ad.domain.User;
import com.example.ad.domain.UserType;
import com.example.ad.repo.ServiceRepository;
import com.example.ad.repo.UserRepository;

@SpringBootApplication
public class PuppyWorldApplication {

	@Autowired
	public UserRepository urepo;



	public static void main(String[] args) {
		SpringApplication.run(PuppyWorldApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {

			


			//dummy12

			

			/*
			 * BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); String
			 * encodedPassword1 = passwordEncoder.encode("keyin1994"); String
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
