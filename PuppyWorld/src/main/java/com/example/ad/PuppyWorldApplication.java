package com.example.ad;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.time.LocalDateTime;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.ad.domain.Post;
import com.example.ad.domain.PostComment;
import com.example.ad.domain.PostType;
import com.example.ad.domain.Product;
import com.example.ad.domain.ProductType;
import com.example.ad.domain.User;
import com.example.ad.domain.Role;
import com.example.ad.repo.PostCommentRepository;
import com.example.ad.repo.PostRepository;
import com.example.ad.repo.ProductRepository;
import com.example.ad.repo.ServiceRepository;
import com.example.ad.repo.UserRepository;

@SpringBootApplication
public class PuppyWorldApplication {

	


			@Autowired
			public UserRepository urepo;

			@Autowired
			public ProductRepository prepo;
			
			@Autowired
			public PostRepository porepo;
			
			@Autowired
			public PostCommentRepository pcrepo;


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
				      
				      User u1 = new User(Role.ROLE_ADMIN, "joe", "Joe", encodedPassword1,
				  			"e0533420@u.nus.edu");
				      
				      User u2 = new User(Role.ROLE_ADMIN, "ann", "Ann", encodedPassword1,
				  			"chenyihan@gmail.com");
				      
				      urepo.save(u1);
				      
				      urepo.save(u2);
				      
				      Post p1 = new Post(PostType.LOST, "Have you seen my dog?", "Dog lost around Serangoon Gardens. Its name is Max. It was lost in the afternoon. Did anyone see it?", data1, u1);
				
				      Post p2 = new Post(PostType.FOUND, "Dog roaming around NUS", "Saw a black shih tzu roaming around school today, HMU if you are its owner. The dog tag says that its name is Jackie. I fed it some food already. Hope to return it to the owner soon.", data2, u2);
				      
				      porepo.save(p1);
				      
				      porepo.save(p2);
				      
				      PostComment pc1 = new PostComment("I think I have seen it too", LocalDateTime.now(), u1, p1); 
				      PostComment pc2 = new PostComment("Looks cute!", LocalDateTime.now().minusMinutes(1), u1, p1); 
				      PostComment pc3 = new PostComment("Have you seen it too?", LocalDateTime.now().minusMinutes(2), u1, p1); 
				      PostComment pc4 = new PostComment("May have spotted it around the bus stop.", LocalDateTime.now().minusMinutes(3), u2, p1); 
				      PostComment pc5 = new PostComment("Is it found yet?", LocalDateTime.now().minusMinutes(4), u1, p1); 
				      PostComment pc6 = new PostComment("I hope it is returned to its owner soon", LocalDateTime.now().minusMinutes(5), u2, p2); 
				      PostComment pc7 = new PostComment("Yes. It has been returned.", LocalDateTime.now().minusMinutes(6), u1, p2); 
				      PostComment pc8 = new PostComment("Are you sure?", LocalDateTime.now().minusMinutes(7), u1, p2); 
				      PostComment pc9 = new PostComment("Yes, really sure it has been returned. Thanks everyone for the help.", LocalDateTime.now().minusMinutes(8), u2, p2); 
				      PostComment pc10 = new PostComment("That's great!", LocalDateTime.now().minusMinutes(9), u1, p2); 
				      PostComment pc11 = new PostComment("Saw it at the bus stop", LocalDateTime.now().minusMinutes(10), u1, p1); 
				      PostComment pc12 = new PostComment("Looks nice!", LocalDateTime.now().minusMinutes(11), u1, p1); 
				      PostComment pc13 = new PostComment("Maybe my friend saw it", LocalDateTime.now().minusMinutes(12), u1, p1); 
				      PostComment pc14 = new PostComment("May have spotted it around the canteen.", LocalDateTime.now().minusMinutes(13), u2, p1); 
				      PostComment pc15 = new PostComment("Found??", LocalDateTime.now().minusMinutes(14), u1, p1); 
				      PostComment pc16 = new PostComment("Go home soon", LocalDateTime.now().minusMinutes(15), u2, p2); 
				      PostComment pc17 = new PostComment("It came home yesterday", LocalDateTime.now().minusMinutes(16), u1, p2); 
				      PostComment pc18 = new PostComment("Yes!", LocalDateTime.now().minusMinutes(17), u1, p2); 
				      PostComment pc19 = new PostComment("Thanks everyone for the help.", LocalDateTime.now().minusMinutes(18), u2, p2); 
				      PostComment pc20 = new PostComment("That's good!", LocalDateTime.now().minusMinutes(19), u1, p2); 
				      PostComment pc21 = new PostComment("Looks like my dog", LocalDateTime.now().minusMinutes(20), u1, p1); 
				      PostComment pc22 = new PostComment("Looks nice", LocalDateTime.now().minusMinutes(21), u1, p1); 
				      PostComment pc23 = new PostComment("Stolen?", LocalDateTime.now().minusMinutes(22), u1, p1); 
				      PostComment pc24 = new PostComment("What is its name?", LocalDateTime.now().minusMinutes(23), u2, p1); 
				      PostComment pc25 = new PostComment("Is it found yet?", LocalDateTime.now().minusMinutes(24), u1, p1); 
				      PostComment pc26 = new PostComment("I hope it is returned to its owner soon", LocalDateTime.now().minusMinutes(25), u2, p2); 
				      PostComment pc27 = new PostComment("Yes. It has been returned.", LocalDateTime.now().minusMinutes(26), u1, p2); 
				      PostComment pc28 = new PostComment("Are you sure?", LocalDateTime.now().minusMinutes(27), u1, p2); 
				      PostComment pc29 = new PostComment("Yes, really sure it has been returned. Thanks everyone for the help.", LocalDateTime.now().minusMinutes(28), u2, p2); 
				      PostComment pc30 = new PostComment("That's great!", LocalDateTime.now().minusMinutes(29), u1, p2); 
				      
				      pcrepo.save(pc1); pcrepo.save(pc2); pcrepo.save(pc3); pcrepo.save(pc4); pcrepo.save(pc5);pcrepo.save(pc6);pcrepo.save(pc7);pcrepo.save(pc8);pcrepo.save(pc9);pcrepo.save(pc10);
				      pcrepo.save(pc11); pcrepo.save(pc12); pcrepo.save(pc13); pcrepo.save(pc14); pcrepo.save(pc15);pcrepo.save(pc16);pcrepo.save(pc17);pcrepo.save(pc18);pcrepo.save(pc19);pcrepo.save(pc20);
				      pcrepo.save(pc21); pcrepo.save(pc22); pcrepo.save(pc23); pcrepo.save(pc24); pcrepo.save(pc25);pcrepo.save(pc26);pcrepo.save(pc27);pcrepo.save(pc28);pcrepo.save(pc29);pcrepo.save(pc30);
					        
				      


			

		};
	}
}

