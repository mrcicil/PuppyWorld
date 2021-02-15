package com.example.ad.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class BreedController {
	
	@RequestMapping(value = "/checkBreed")
	public String add() 
	{
		return "checkBreed";
	}
	
	@RequestMapping(value = "/checkBreedsave")
	public String save(@RequestParam("fileImage") MultipartFile multipartFile, Model model) throws IllegalStateException, IOException 
	{
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
		multipartFile.transferTo(convFile);
		byte[] fileContent = FileUtils.readFileToByteArray(convFile);
		String encodedString = Base64.getEncoder().encodeToString(fileContent);
		List<String> result = mlMethod(fileContent);
		model.addAttribute("image", encodedString);
		model.addAttribute("results", result);
		return "checkBreed";
	}
	
	public List<String> mlMethod(byte[] fileContent) {
		List<String> output = new ArrayList<String>();
		String adding = "";
        try{
        	String attachmentName = "image";
        	String attachmentFileName = "androidFlask.jpg";
        	String crlf = "\r\n";
        	String twoHyphens = "--";
        	String boundary =  "*****";
        	
        	HttpURLConnection httpUrlConnection = null;
        	URL url = new URL("https://tut-api-37p4k2zaiq-uc.a.run.app/predict/");
        	//URL url = new URL("https://puppysnap.azurewebsites.net/predict/");
        	httpUrlConnection = (HttpURLConnection) url.openConnection();
        	httpUrlConnection.setUseCaches(false);
        	httpUrlConnection.setDoOutput(true);

        	httpUrlConnection.setRequestMethod("POST");
        	httpUrlConnection.setRequestProperty("Connection", "Keep-Alive");
        	httpUrlConnection.setRequestProperty("Cache-Control", "no-cache");
        	httpUrlConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
        	
        	DataOutputStream request = new DataOutputStream(
        		    httpUrlConnection.getOutputStream());

        		request.writeBytes(twoHyphens + boundary + crlf);
        		request.writeBytes("Content-Disposition: form-data; name=\"" + attachmentName + "\";filename=\"" + attachmentFileName + "\"" + crlf);
        		request.writeBytes(crlf);
        		request.write(fileContent);
        		
        		request.writeBytes(crlf);
        		request.writeBytes(twoHyphens + boundary + twoHyphens + crlf);
        		
        		request.flush();
        		request.close();
        		
        	
        		InputStream responseStream = new 
        			    BufferedInputStream(httpUrlConnection.getInputStream());

        			BufferedReader responseStreamReader = 
        			    new BufferedReader(new InputStreamReader(responseStream));

        			String line = "";
        			StringBuilder stringBuilder = new StringBuilder();

        			while ((line = responseStreamReader.readLine()) != null) {
        			    stringBuilder.append(line).append("\n");
        			}
        			responseStreamReader.close();

        			String response = stringBuilder.toString();
        			System.out.println(response);
        			adding = response;
        			responseStream.close();
        			httpUrlConnection.disconnect();
        	
            
            } catch (MalformedURLException e) {
            	e.printStackTrace();
            }catch (IOException e){
            	e.printStackTrace();
            }finally
        		{
            
        }
        String str[] = adding.split("&");
        output = Arrays.asList(str);
        return output;
	}

}
