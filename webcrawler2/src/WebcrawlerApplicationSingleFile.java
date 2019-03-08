

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WebcrawlerApplicationSingleFile {

	public static void main(String[] args) {
		try {
			//read json file data to String
			byte[] jsonData = Files.readAllBytes(Paths.get("internet.json"));
			
			//create ObjectMapper instance
			ObjectMapper objectMapper = new ObjectMapper();
			
			//convert json string to object
			Pages2DTO pages = objectMapper.readValue(jsonData, Pages2DTO.class);
			
			if(pages==null || pages.getPages().isEmpty()){
				System.out.println("Empty Pages List Sent");
			} else {
				Response2DTO res = process(pages);
				StringWriter responseString = new StringWriter();
				objectMapper.writeValue(responseString, res);
				System.out.println("Response JSON is\n"+responseString);
			}	
			
		} catch (JsonParseException e) {
			System.out.println("JsonParseException : " + e.getLocalizedMessage());
			e.printStackTrace();
		} catch (JsonMappingException e) {
			System.out.println("JsonMappingException : " + e.getLocalizedMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException : " + e.getLocalizedMessage());
			e.printStackTrace();
		}

	}
	
	private static Response2DTO process(Pages2DTO pages) {
		Response2DTO response = new Response2DTO();
		response.setSuccess(new ArrayList<String>());
		response.setSkipped(new ArrayList<String>());
		response.setError(new ArrayList<String>());
		
		try {
			Map<String, List<String>> connectingPages = getConnectingPagesMap(pages);
			String startPage = pages.getPages().get(0).getAddress();
			
			response.getSuccess().add(startPage);
			
			Runnable r = new NextPageService2(startPage, connectingPages, response);
			Thread t = new Thread(r);
			t.start();
			t.join();
			
			System.out.println(response.toString());
		} catch (InterruptedException e) {
			System.out.println("Error : " + e.getMessage());
			e.printStackTrace();
		}
		return response;
	}
	
	private static Map<String, List<String>> getConnectingPagesMap(Pages2DTO pages){
		Map<String, List<String>> cm = new HashMap<>();
		for (Page2DTO page : pages.getPages()) {
			cm.put(page.getAddress(), page.getLinks());
		}
		return cm;
	}
	

}

