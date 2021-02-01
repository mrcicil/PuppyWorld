package com.example.ad.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.ad.domain.MLPath;
import com.example.ad.repo.MLPathRepository;

@Controller
@RequestMapping("/checkBreed")
public class BreedController {
	
	@Autowired
	MLPathRepository mlrepo;
	
	@RequestMapping(value = "/home")
	public String add() 
	{
		mlrepo.deleteAll();
		return "ml-form";
	}
	
	@RequestMapping(value = "/save")
	public String save(@RequestParam("image") MultipartFile multipartFile, Model model) throws IllegalStateException, IOException 
	{
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
		multipartFile.transferTo(convFile);
		String pathWay = convFile.toString();
		MLPath mlPath = new MLPath();
		mlPath.setPath(pathWay);
		mlrepo.save(mlPath);
		String result = "Its a....";
		result += mlMethod1();
		
		byte[] fileContent = FileUtils.readFileToByteArray(convFile);
		String encodedString = Base64.getEncoder().encodeToString(fileContent);
		
		model.addAttribute("image", encodedString);
		model.addAttribute("result", result);
		return "ml-form";
	}
	
	public String mlMethod1() {
		String output = "";
		try { 
			  URL url = new URL("http://127.0.0.1:5000/predict/"); 
			  HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			  
			  conn.setRequestMethod("GET");
		      conn.connect();
		      BufferedReader rd  = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		      
		      String line = null;
	          while ((line = rd.readLine()) != null)
	          {
	              output +=line;
	              
	          }
	          rd.close();
	          conn.disconnect();
		  }
		  catch (Exception e) {
	            e.printStackTrace();
	      } return output;
	}

}
