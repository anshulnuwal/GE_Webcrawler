package com.ge.webcrawler2.main;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ge.webcrawler.model.PagesDTO;
import com.ge.webcrawler.model.ResponseDTO;
import com.ge.webcrawler.service.IWebcrawlerService;
import com.ge.webcrawler.service.impl.CrawlerService;

public class WebcrawlerApplicationWithMain {

	public static void main(String[] args) throws IOException {
		//read json file data to String
		byte[] jsonData = Files.readAllBytes(Paths.get("internet.json"));
		
		//create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();
		
		//convert json string to object
		PagesDTO pages = objectMapper.readValue(jsonData, PagesDTO.class);
		
		if(pages==null || pages.getPages().isEmpty()){
			System.out.println("Empty Pages List Sent");
		} else {
			IWebcrawlerService service = new CrawlerService();
			ResponseDTO res = service.process(pages);
			
			StringWriter responseString = new StringWriter();
			objectMapper.writeValue(responseString, res);
			System.out.println("Response JSON is\n"+responseString);
		}
	}

}
