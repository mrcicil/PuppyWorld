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

import com.example.ad.domain.Post;
import com.example.ad.domain.PostType;
import com.example.ad.domain.Product;
import com.example.ad.domain.ProductType;
import com.example.ad.domain.Provider;
import com.example.ad.domain.ReservationType;
import com.example.ad.domain.User;
import com.example.ad.domain.Role;
import com.example.ad.repo.PostRepository;
import com.example.ad.repo.ProductRepository;
import com.example.ad.repo.ProviderRepository;
import com.example.ad.repo.ReservationRepository;
import com.example.ad.repo.ServiceRepository;
import com.example.ad.repo.UserRepository;

@SpringBootApplication
public class PuppyWorldApplication {

	@Autowired
	public UserRepository urepo;

	@Autowired
	public ProductRepository prepo;

	@Autowired
	public ServiceRepository srepo;

	@Autowired
	public ProviderRepository prrepo;

	@Autowired
	public ReservationRepository resrepo;

	@Autowired
	public PostRepository ptrepo;

	public static void main(String[] args) {
		SpringApplication.run(PuppyWorldApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {

//			----- Users ------
			
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword1 = passwordEncoder.encode("password");

			User u1 = new User(Role.ROLE_ADMIN, "joe", "Joe", encodedPassword1, "e0533420@u.nus.edu");

			User u2 = new User(Role.ROLE_ADMIN, "ann", "Ann", encodedPassword1, "chenyihan@gmail.com");

			urepo.save(u1);

			urepo.save(u2);
			
			User u3 = new User(Role.ROLE_USER, "jay", "Jason", encodedPassword1, "jay@email.com");
			urepo.save(u3);
			
			User u4 = new User(Role.ROLE_USER, "dick", "Richard", encodedPassword1, "dick@email.com");
			urepo.save(u4);
			
			User u5 = new User(Role.ROLE_USER, "cass", "Cassandra", encodedPassword1, "cass@email.com");
			urepo.save(u5);
			
			
//			----- Products ------
			
			BufferedImage bImage = ImageIO.read(new File("src/main/resources/static/images/product1.jpg"));
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(bImage, "jpg", bos);
			byte[] img1 = bos.toByteArray();
//			img1 = Base64Utils.encode(img1);

			Product p1 = new Product("PRONATURE - HOLISTIC DOG SENIOR WHITEFISH & RICE 13.6kg", (float) 149.00, 23,
					ProductType.FOOD, img1, u1);
			prepo.save(p1);

			BufferedImage bImage2 = ImageIO.read(new File("src/main/resources/static/images/product2.jpg"));
			ByteArrayOutputStream bos2 = new ByteArrayOutputStream();
			ImageIO.write(bImage2, "jpg", bos2);
			byte[] img2 = bos2.toByteArray();

			Product p2 = new Product("FISH 4 DOGS - FINEST SALMON COMPLETE (SMALL BITE) 1.5kg ", (float) 30.60, 52,
					ProductType.FOOD, img2, u2);
			prepo.save(p2);

			BufferedImage bImage3 = ImageIO.read(new File("src/main/resources/static/images/product3.jpg"));
			ByteArrayOutputStream bos3 = new ByteArrayOutputStream();
			ImageIO.write(bImage3, "jpg", bos3);
			byte[] img3 = bos3.toByteArray();

			Product p3 = new Product("PRONATURE - HOLISTIC DOG SENIOR WHITEFISH & RICE 12kg", (float) 115.00, 12,
					ProductType.FOOD, img3, u2);
			prepo.save(p3);

			BufferedImage bImage4 = ImageIO.read(new File("src/main/resources/static/images/product2.jpg"));
			ByteArrayOutputStream bos4 = new ByteArrayOutputStream();
			ImageIO.write(bImage4, "jpg", bos4);
			byte[] img4 = bos4.toByteArray();

			Product p4 = new Product("FISH 4 DOGS - FINEST SALMON COMPLETE 12kg", (float) 172.00, 6, ProductType.FOOD,
					img4, u1);
			prepo.save(p4);

			BufferedImage bImage5 = ImageIO.read(new File("src/main/resources/static/images/product5.jpg"));
			ByteArrayOutputStream bos5 = new ByteArrayOutputStream();
			ImageIO.write(bImage5, "jpg", bos5);
			byte[] img5 = bos5.toByteArray();

			Product p5 = new Product("BURP - LAMB WITH SALMON HYPOALLERGENIC FOR ADULT DOG 12kg ", (float) 100.00, 13,
					ProductType.FOOD, img5, u2);
			prepo.save(p5);

			BufferedImage bImage6 = ImageIO.read(new File("src/main/resources/static/images/product6.jpg"));
			ByteArrayOutputStream bos6 = new ByteArrayOutputStream();
			ImageIO.write(bImage6, "jpg", bos6);
			byte[] img6 = bos6.toByteArray();

			Product p6 = new Product("CANINE CAVIAR LIMITED INGREDIENT DIET - LAMB (ALKALINE) 10kg", (float) 159.50, 7,
					ProductType.FOOD, img6, u2);
			prepo.save(p6);

			BufferedImage bImage7 = ImageIO.read(new File("src/main/resources/static/images/product7.jpg"));
			ByteArrayOutputStream bos7 = new ByteArrayOutputStream();
			ImageIO.write(bImage7, "jpg", bos7);
			byte[] img7 = bos7.toByteArray();

			Product p7 = new Product("1ST CHOICE DOG SENIOR - LAMB & FISH, SENSITIVE SKIN & COAT 12kg", (float) 115.00,
					12, ProductType.FOOD, img7, u2);
			prepo.save(p7);

			BufferedImage bImage8 = ImageIO.read(new File("src/main/resources/static/images/product7.jpg"));
			ByteArrayOutputStream bos8 = new ByteArrayOutputStream();
			ImageIO.write(bImage8, "jpg", bos8);
			byte[] img8 = bos8.toByteArray();

			Product p8 = new Product("1ST CHOICE DOG SENIOR - LAMB & FISH, SENSITIVE SKIN & COAT 2.72kg", (float) 36.20,
					12, ProductType.FOOD, img8, u2);
			prepo.save(p8);

			BufferedImage bImage9 = ImageIO.read(new File("src/main/resources/static/images/product3.jpg"));
			ByteArrayOutputStream bos9 = new ByteArrayOutputStream();
			ImageIO.write(bImage9, "jpg", bos9);
			byte[] img9 = bos9.toByteArray();

			Product p9 = new Product("PRONATURE HOLISTIC DOG ADULT SALMON & RICE 13.6kg ", (float) 149.00, 32,
					ProductType.FOOD, img9, u2);
			prepo.save(p9);

			BufferedImage bImage10 = ImageIO.read(new File("src/main/resources/static/images/1.jpg"));
			ByteArrayOutputStream bos10 = new ByteArrayOutputStream();
			ImageIO.write(bImage10, "jpg", bos10);
			byte[] img10 = bos10.toByteArray();

			Product p10 = new Product("HILL'S SCIENCE DIET CANINE ADULT SENSITIVE SKIN & STOMACH 30lbs", (float) 149.25,
					22, ProductType.FOOD, img10, u2);
			prepo.save(p10);

			BufferedImage bImage11 = ImageIO.read(new File("src/main/resources/static/images/product11.jpg"));
			ByteArrayOutputStream bos11 = new ByteArrayOutputStream();
			ImageIO.write(bImage11, "jpg", bos11);
			byte[] img11 = bos11.toByteArray();

			Product p11 = new Product("HILL'S SCIENCE DIET CANINE ADULT 15kg", (float) 135.00, 12, ProductType.FOOD,
					img11, u2);
			prepo.save(p11);

			BufferedImage bImage12 = ImageIO.read(new File("src/main/resources/static/images/product12.jpg"));
			ByteArrayOutputStream bos12 = new ByteArrayOutputStream();
			ImageIO.write(bImage12, "jpg", bos12);
			byte[] img12 = bos12.toByteArray();

			Product p12 = new Product("VETS ALL NATURAL COMPLETE LIFE KANGAROO & VEGETABLE FOR DOG 5kg", (float) 89.00,
					45, ProductType.FOOD, img12, u2);
			prepo.save(p12);

			BufferedImage bImage13 = ImageIO.read(new File("src/main/resources/static/images/product5.jpg"));
			ByteArrayOutputStream bos13 = new ByteArrayOutputStream();
			ImageIO.write(bImage13, "jpg", bos13);
			byte[] img13 = bos13.toByteArray();

			Product p13 = new Product("BURP LAMB WITH SALMON HYPOALLERGENIC FOR ADULT DOG 1.35kg", (float) 16.20, 13,
					ProductType.FOOD, img13, u2);
			prepo.save(p13);

			BufferedImage bImage14 = ImageIO.read(new File("src/main/resources/static/images/product14.jpg"));
			ByteArrayOutputStream bos14 = new ByteArrayOutputStream();
			ImageIO.write(bImage14, "jpg", bos14);
			byte[] img14 = bos14.toByteArray();

			Product p14 = new Product("WELLNESS CORE OCEAN 22lbs", (float) 152.45, 28, ProductType.FOOD, img14, u1);
			prepo.save(p14);

			BufferedImage bImage15 = ImageIO.read(new File("src/main/resources/static/images/product2.jpg"));
			ByteArrayOutputStream bos15 = new ByteArrayOutputStream();
			ImageIO.write(bImage15, "jpg", bos15);
			byte[] img15 = bos15.toByteArray();

			Product p15 = new Product("FISH 4 DOGS FINEST SALMON COMPLETE (SMALL BITE) 12kg", (float) 172.00, 37,
					ProductType.FOOD, img15, u1);
			prepo.save(p15);

			BufferedImage bImage16 = ImageIO.read(new File("src/main/resources/static/images/product16.jpg"));
			ByteArrayOutputStream bos16 = new ByteArrayOutputStream();
			ImageIO.write(bImage16, "jpg", bos16);
			byte[] img16 = bos16.toByteArray();

			Product p16 = new Product("WELLNESS COMPLETE HEALTH WHITEFISH & SWEET POTATO 30lbs", (float) 139.60, 26,
					ProductType.FOOD, img16, u1);
			prepo.save(p16);

			BufferedImage bImage17 = ImageIO.read(new File("src/main/resources/static/images/product17.jpg"));
			ByteArrayOutputStream bos17 = new ByteArrayOutputStream();
			ImageIO.write(bImage17, "jpg", bos17);
			byte[] img17 = bos17.toByteArray();

			Product p17 = new Product("EUKANUBA ADULT LAMB & RICE (LARGE BREED) 15kg", (float) 111.30, 3,
					ProductType.FOOD, img17, u1);
			prepo.save(p17);

			BufferedImage bImage18 = ImageIO.read(new File("src/main/resources/static/images/product18.jpg"));
			ByteArrayOutputStream bos18 = new ByteArrayOutputStream();
			ImageIO.write(bImage18, "jpg", bos18);
			byte[] img18 = bos18.toByteArray();

			Product p18 = new Product("ADDICTION ZEN VEGETARIAN 20lbs", (float) 68.00, 3, ProductType.FOOD, img18, u1);
			prepo.save(p18);

			BufferedImage bImage19 = ImageIO.read(new File("src/main/resources/static/images/product19.jpg"));
			ByteArrayOutputStream bos19 = new ByteArrayOutputStream();
			ImageIO.write(bImage19, "jpg", bos19);
			byte[] img19 = bos19.toByteArray();

			Product p19 = new Product("ROYAL CANIN PUPPY (MEDIUM BREED) 10kg", (float) 96.30, 16, ProductType.FOOD,
					img19, u1);
			prepo.save(p19);

			BufferedImage bImage20 = ImageIO.read(new File("src/main/resources/static/images/product20.jpg"));
			ByteArrayOutputStream bos20 = new ByteArrayOutputStream();
			ImageIO.write(bImage20, "jpg", bos20);
			byte[] img20 = bos20.toByteArray();

			Product p20 = new Product("DOG IT DURABLE BOWL w SS INSERT - WHITE MEDIUM", (float) 26.60, 16,
					ProductType.ACCESSORIES, img20, u1);
			prepo.save(p20);

			BufferedImage bImage21 = ImageIO.read(new File("src/main/resources/static/images/product21.jpg"));
			ByteArrayOutputStream bos21 = new ByteArrayOutputStream();
			ImageIO.write(bImage21, "jpg", bos21);
			byte[] img21 = bos21.toByteArray();

			Product p21 = new Product("TRUSTIE ELEVATED SLANTED BOWL (OCEAN GREY) (300ml) (17.5x4.5cm)", (float) 11.00,
					22, ProductType.ACCESSORIES, img21, u1);
			prepo.save(p21);

			BufferedImage bImage22 = ImageIO.read(new File("src/main/resources/static/images/product22.jpg"));
			ByteArrayOutputStream bos22 = new ByteArrayOutputStream();
			ImageIO.write(bImage22, "jpg", bos22);
			byte[] img22 = bos22.toByteArray();

			Product p22 = new Product("FLEXI RETRACTABLE LEASH - VARIO CORD SMALL 5m - BLUE", (float) 14.80, 37,
					ProductType.ACCESSORIES, img22, u1);
			prepo.save(p22);

			BufferedImage bImage23 = ImageIO.read(new File("src/main/resources/static/images/product23.jpg"));
			ByteArrayOutputStream bos23 = new ByteArrayOutputStream();
			ImageIO.write(bImage23, "jpg", bos23);
			byte[] img23 = bos23.toByteArray();

			Product p23 = new Product("FOUR PAWS DOG TRAINING LEAD 10ft", (float) 22.45, 1, ProductType.ACCESSORIES,
					img23, u1);
			prepo.save(p23);

			BufferedImage bImage24 = ImageIO.read(new File("src/main/resources/static/images/product24.jpg"));
			ByteArrayOutputStream bos24 = new ByteArrayOutputStream();
			ImageIO.write(bImage24, "jpg", bos24);
			byte[] img24 = bos24.toByteArray();

			Product p24 = new Product("NOVAGUARD WOUND GUARD PROTECTION FOR SMALL / MEDIUM DOGS (10-25kg)",
					(float) 20.20, 2, ProductType.CARE, img24, u1);
			prepo.save(p24);

			BufferedImage bImage25 = ImageIO.read(new File("src/main/resources/static/images/product25.jpg"));
			ByteArrayOutputStream bos25 = new ByteArrayOutputStream();
			ImageIO.write(bImage25, "jpg", bos25);
			byte[] img25 = bos25.toByteArray();

			Product p25 = new Product("FRONTLINE FLEA & TICK PLUS 6s FOR MEDIUM DOG 10-20kg", (float) 72.85, 13,
					ProductType.CARE, img25, u2);
			prepo.save(p25);

			BufferedImage bImage26 = ImageIO.read(new File("src/main/resources/static/images/product26.jpg"));
			ByteArrayOutputStream bos26 = new ByteArrayOutputStream();
			ImageIO.write(bImage26, "jpg", bos26);
			byte[] img26 = bos26.toByteArray();

			Product p26 = new Product("NEXGARD FLEAS & TICKS FLAV CHEW (M) DOG (4-10kg) 6 Tabs", (float) 70.95, 14,
					ProductType.CARE, img26, u2);
			prepo.save(p26);

			BufferedImage bImage27 = ImageIO.read(new File("src/main/resources/static/images/product27.jpg"));
			ByteArrayOutputStream bos27 = new ByteArrayOutputStream();
			ImageIO.write(bImage27, "jpg", bos27);
			byte[] img27 = bos27.toByteArray();

			Product p27 = new Product("YOURS DROOLLY FLEA & TICK SHAMPOO & CONDITIONER 500ml", (float) 23.50, 9,
					ProductType.CARE, img27, u2);
			prepo.save(p27);

			BufferedImage bImage28 = ImageIO.read(new File("src/main/resources/static/images/product28.jpg"));
			ByteArrayOutputStream bos28 = new ByteArrayOutputStream();
			ImageIO.write(bImage28, "jpg", bos28);
			byte[] img28 = bos28.toByteArray();

			Product p28 = new Product("ESPREE FLEA & TICK DOG SPRAY 355ml", (float) 25.00, 8, ProductType.CARE, img28,
					u2);
			prepo.save(p28);

			BufferedImage bImage29 = ImageIO.read(new File("src/main/resources/static/images/product29.jpg"));
			ByteArrayOutputStream bos29 = new ByteArrayOutputStream();
			ImageIO.write(bImage29, "jpg", bos29);
			byte[] img29 = bos29.toByteArray();

			Product p29 = new Product("NYLABONE POWER CHEW TEXTURE BEEF JERKY - WOLF", (float) 16.30, 45,
					ProductType.TOY, img29, u2);
			prepo.save(p29);

			BufferedImage bImage30 = ImageIO.read(new File("src/main/resources/static/images/product30.jpg"));
			ByteArrayOutputStream bos30 = new ByteArrayOutputStream();
			ImageIO.write(bImage30, "jpg", bos30);
			byte[] img30 = bos30.toByteArray();

			Product p30 = new Product("KONG AIR KONG SQUEAKER DUMBBELL - MEDIUM", (float) 28.85, 37, ProductType.TOY,
					img30, u2);
			prepo.save(p30);

			BufferedImage bImage31 = ImageIO.read(new File("src/main/resources/static/images/product31.jpg"));
			ByteArrayOutputStream bos31 = new ByteArrayOutputStream();
			ImageIO.write(bImage31, "jpg", bos31);
			byte[] img31 = bos31.toByteArray();

			Product p31 = new Product("NYLABONE DURA CHEW WISHBONE - REGULAR", (float) 15.80, 40, ProductType.TOY,
					img31, u2);
			prepo.save(p31);

			BufferedImage bImage32 = ImageIO.read(new File("src/main/resources/static/images/product32.jpg"));
			ByteArrayOutputStream bos32 = new ByteArrayOutputStream();
			ImageIO.write(bImage32, "jpg", bos32);
			byte[] img32 = bos32.toByteArray();

			Product p32 = new Product("KONG WUBBA FRIEND LARGE", (float) 23.50, 8, ProductType.TOY, img32, u2);
			prepo.save(p32);

			BufferedImage bImage33 = ImageIO.read(new File("src/main/resources/static/images/product33.jpg"));
			ByteArrayOutputStream bos33 = new ByteArrayOutputStream();
			ImageIO.write(bImage33, "jpg", bos33);
			byte[] img33 = bos33.toByteArray();

			Product p33 = new Product("NYLABONE FLEXIBLE DENTAL CHEW - WOLF", (float) 19.50, 1, ProductType.TOY, img33,
					u2);
			prepo.save(p33);

			BufferedImage bImage34 = ImageIO.read(new File("src/main/resources/static/images/product34.png"));
			ByteArrayOutputStream bos34 = new ByteArrayOutputStream();
			ImageIO.write(bImage34, "png", bos34);
			byte[] img34 = bos34.toByteArray();

			Product p34 = new Product("Sable Shetland Sheepdog - puppy, 2 months; Parents are award-winning show dogs.",
					(float) 1200.00, 1, ProductType.DOG, img34, u2);
			prepo.save(p34);

			BufferedImage bImage35 = ImageIO.read(new File("src/main/resources/static/images/product35.jpg"));
			ByteArrayOutputStream bos35 = new ByteArrayOutputStream();
			ImageIO.write(bImage35, "jpg", bos35);
			byte[] img35 = bos35.toByteArray();

			Product p35 = new Product("Appenzeller Sennenhund - puppy, 3 months. Bred in the Swiss alps.",
					(float) 2800.00, 1, ProductType.DOG, img35, u2);
			prepo.save(p35);

			BufferedImage bImage36 = ImageIO.read(new File("src/main/resources/static/images/product36.jpg"));
			ByteArrayOutputStream bos36 = new ByteArrayOutputStream();
			ImageIO.write(bImage36, "jpg", bos36);
			byte[] img36 = bos36.toByteArray();

			Product p36 = new Product("Italian Greyhound - puppies, 2 months. Parents are champion race-dogs.",
					(float) 140.20, 1, ProductType.DOG, img36, u2);
			prepo.save(p36);

//			----- Service providers ------

			BufferedImage servpImage = ImageIO.read(new File("src/main/resources/static/images/servp.jpg"));
			ByteArrayOutputStream servpbos = new ByteArrayOutputStream();
			ImageIO.write(servpImage, "jpg", servpbos);
			byte[] servp = servpbos.toByteArray();

			Provider sp = new Provider("Dog Care Consultation",
					"First time dog owner? Thinking of getting a particular breed? We have extensive experiential knowledge to help you with your concerns, whether it is on the health, nutritional and grooming needs, trainability or adaptability. We also advise on barking tendencies, apartment friendliness, child friendliness and hypoallergenic concerns on popular breeds. Come down to our store for hands-on advice today!",
					(float) 0.00, ReservationType.NO, servp);
			prrepo.save(sp);

			BufferedImage servpImage2 = ImageIO.read(new File("src/main/resources/static/images/servp2.jpg"));
			ByteArrayOutputStream servpbos2 = new ByteArrayOutputStream();
			ImageIO.write(servpImage2, "jpg", servpbos2);
			byte[] servp2 = servpbos2.toByteArray();
//			servp1 = Base64Utils.encode(servp1);

			Provider sp2 = new Provider("Dog Grooming - Basic Package",
					"Grooming is essential in maintaining the health, wellbeing and happiness of your dog! This services includes nail clipping, ear cleaning, shaving of anal area, between the legs, belly and paw pads. Bathing, conditioning, drying and brushing. Trimming fur off the nose bridge and around paw (if applicable). Prices start at $40++ for small dogs (~1kg), with additional $5 for every kg.",
					(float) 40.00, ReservationType.YES, servp2);
			prrepo.save(sp2);

			BufferedImage servpImage3 = ImageIO.read(new File("src/main/resources/static/images/servp3.jpg"));
			ByteArrayOutputStream servpbos3 = new ByteArrayOutputStream();
			ImageIO.write(servpImage3, "jpg", servpbos3);
			byte[] servp3 = servpbos3.toByteArray();

			Provider sp3 = new Provider("Dog Grooming - Full Package",
					"Full grooming comprises of everything in the basic package, with additional hair styling/cutting. Prices start at $50++ for small dogs (~1kg), with additional $5 for every kg.",
					(float) 50.00, ReservationType.YES, servp3);
			prrepo.save(sp3);

			BufferedImage servpImage4 = ImageIO.read(new File("src/main/resources/static/images/servp4.jpg"));
			ByteArrayOutputStream servpbos4 = new ByteArrayOutputStream();
			ImageIO.write(servpImage4, "jpg", servpbos4);
			byte[] servp4 = servpbos4.toByteArray();

			Provider sp4 = new Provider("Pet Spa",
					"Pamper your furkids like yourself at our award-winning facility, accredited by the Singapore Kennel Club. Our team of professional groomers tailor each service based on the needs of your pet. A day at the spa includes bathing, grooming, styling and colouring, and a spa service. Choose between skin clinic chlorhexidine treatment, Japanese Herb Treatment, nano-microbubble spa or CO2 treatment. Prices for small dogs start from $80++",
					(float) 80.00, ReservationType.YES, servp4);
			prrepo.save(sp4);

			BufferedImage servpImage5 = ImageIO.read(new File("src/main/resources/static/images/servp5.jpg"));
			ByteArrayOutputStream servpbos5 = new ByteArrayOutputStream();
			ImageIO.write(servpImage5, "jpg", servpbos5);
			byte[] servp5 = servpbos5.toByteArray();

			Provider sp5 = new Provider("De-Ticking",
					"Have a bad tick infestation? Leave it to the professionals to to de-tick. Prices start at $30++. Additional charges may apply depending on the severity of infestation. ",
					(float) 30.00, ReservationType.YES, servp5);
			prrepo.save(sp5);

			BufferedImage servpImage6 = ImageIO.read(new File("src/main/resources/static/images/servp6.jpg"));
			ByteArrayOutputStream servpbos6 = new ByteArrayOutputStream();
			ImageIO.write(servpImage6, "jpg", servpbos6);
			byte[] servp6 = servpbos6.toByteArray();

			Provider sp6 = new Provider("Dental Care",
					"Nervous about brushing your dog's teeth? Or perhaps your dog has a case of bad breath. We provide basic tooth brushing and cleaning services to put you at ease.",
					(float) 20.00, ReservationType.YES, servp6);
			prrepo.save(sp6);

			BufferedImage servpImage7 = ImageIO.read(new File("src/main/resources/static/images/servp7.jpg"));
			ByteArrayOutputStream servpbos7 = new ByteArrayOutputStream();
			ImageIO.write(servpImage7, "jpg", servpbos7);
			byte[] servp7 = servpbos7.toByteArray();

			Provider sp7 = new Provider("Pet Day Care",
					"We provide pet day-boarding services to keep you furry companions company while you're away. Includes 2 meals, 4 walks a day and a complimentary webcam viewing for you to check up on them. Complimentary bath and blowdry for stays of more than 3 consecutive days. Check-in: 8am. Check-out: 6pm.",
					(float) 80.00, ReservationType.YES, servp7);
			prrepo.save(sp7);

			BufferedImage servpImage8 = ImageIO.read(new File("src/main/resources/static/images/servp8.jpg"));
			ByteArrayOutputStream servpbos8 = new ByteArrayOutputStream();
			ImageIO.write(servpImage8, "jpg", servpbos8);
			byte[] servp8 = servpbos8.toByteArray();

			Provider sp8 = new Provider("Pet Hotel",
					"Going away for a vacation and can't bring your loved one(s) along? Fret not! We offer overnight services. Full board services start from $100 per day (12pm-12pm). Additional charges may apply depending on choice of suite, meal types and dog size.",
					(float) 100.00, ReservationType.YES, servp8);
			prrepo.save(sp8);

			BufferedImage servpImage9 = ImageIO.read(new File("src/main/resources/static/images/servp9.jpg"));
			ByteArrayOutputStream servpbos9 = new ByteArrayOutputStream();
			ImageIO.write(servpImage9, "jpg", servpbos9);
			byte[] servp9 = servpbos9.toByteArray();

			Provider sp9 = new Provider("Basic Dog Training",
					"Spend quality time with you dog and get to know it better through our obedience course. We'll enable you to better understand and build a stronger connection between the two of you. Improving trust and breeding a loving relationship will give both of you greater satisfaction. The training will be conducted in groups over 10 weeks.",
					(float) 800.00, ReservationType.YES, servp9);
			prrepo.save(sp9);

			BufferedImage servpImage10 = ImageIO.read(new File("src/main/resources/static/images/servp10.jpg"));
			ByteArrayOutputStream servpbos10 = new ByteArrayOutputStream();
			ImageIO.write(servpImage10, "jpg", servpbos10);
			byte[] servp10 = servpbos10.toByteArray();

			Provider sp10 = new Provider("Potty Training",
					"Toilet train your dog or puppy through our course. It is a small price to pay compared to having clean up that soiled carpet or mat many times over. The course is conducted in groups over 10 weeks.",
					(float) 480.00, ReservationType.YES, servp10);
			prrepo.save(sp10);
			
			
//			----- Posts ------
			
			BufferedImage ptImage = ImageIO.read(new File("src/main/resources/static/images/ptimg.jpg"));
			ByteArrayOutputStream ptbos = new ByteArrayOutputStream();
			ImageIO.write(ptImage, "jpg", ptbos);
			byte[] ptimg = ptbos.toByteArray();

			Post pt = new Post(PostType.LOST, "Lost poodle :(", "Last seen on 14 July at 7pm along Sycamore Canyon rd. $5000 reward!!!", ptimg, u3);
			ptrepo.save(pt);
			
			BufferedImage ptImage2 = ImageIO.read(new File("src/main/resources/static/images/ptimg2.jpg"));
			ByteArrayOutputStream ptbos2 = new ByteArrayOutputStream();
			ImageIO.write(ptImage2, "jpg", ptbos2);
			byte[] ptimg2 = ptbos2.toByteArray();

			Post pt2 = new Post(PostType.LOST, "Lost Sheltie", "Prince was last seen on 23 Dec at Platinum Dogs club. Offering $2000 reward.", ptimg2, u4);
			ptrepo.save(pt2);
			
			BufferedImage ptImage3 = ImageIO.read(new File("src/main/resources/static/images/ptimg3.jpg"));
			ByteArrayOutputStream ptbos3 = new ByteArrayOutputStream();
			ImageIO.write(ptImage3, "jpg", ptbos3);
			byte[] ptimg3 = ptbos3.toByteArray();

			Post pt3 = new Post(PostType.FOUND, "FOUND: Terrier mix", "Found this gray-white-gold terrier mix outside on the porch. Please call the number if it's yours. And provide some proof of ownership.", ptimg3, u5);
			ptrepo.save(pt3);
		};
	}
}
