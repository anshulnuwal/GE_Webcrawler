package com.ge.webcrawler.service.impl;

import java.util.List;
import java.util.Map;

import com.ge.webcrawler.model.ResponseDTO;

public class NextPageService implements Runnable {

	private String currentPageAddress;
	volatile Map<String, List<String>> connectingPageMap;
	volatile ResponseDTO response;
	
	
	public NextPageService (String currentPageAddress, Map<String, List<String>> connectingPageMap, ResponseDTO response){
		this.currentPageAddress = currentPageAddress;
		this.connectingPageMap = connectingPageMap;
		this.response = response;
	}
	
	@Override
	public void run() {
		System.out.println("In thread for page : " + this.currentPageAddress);
		try {
			List<String> connectedPages = connectingPageMap.get(this.currentPageAddress);
			if(connectedPages.isEmpty()){
				return;
			}
			
			for (String connectedPageAddress : connectedPages) {
				if(!connectingPageMap.containsKey(connectedPageAddress)){
					// Update in Error Part
					updateErrorPage(connectedPageAddress);
				} //Check if already visited. 
				  else if (this.response.getSuccess().contains(connectedPageAddress)){
					  // Already visited, need to skip
					  updateSkippedPage(connectedPageAddress);
				} else {				
					// Page not visited. Update in Success and start checking for further connected Pages
					updateSuccessPage(connectedPageAddress);

					Runnable r = new NextPageService(connectedPageAddress, connectingPageMap, response);
					Thread t = new Thread(r);
					t.start();
					t.join();
				} 
			}
		} catch (InterruptedException e) {
			System.out.println("Error : " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private synchronized void updateErrorPage(String connectedPageAddress){
		if(!this.response.getError().contains(connectedPageAddress)){
			this.response.getError().add(connectedPageAddress);
		}
	}
	
	private synchronized void updateSkippedPage(String connectedPageAddress){
		if(!this.response.getSkipped().contains(connectedPageAddress)){
			this.response.getSkipped().add(connectedPageAddress);
		}
	}
	
	private synchronized void updateSuccessPage(String connectedPageAddress){
		if(!this.response.getSuccess().contains(connectedPageAddress)){
			this.response.getSuccess().add(connectedPageAddress);
		}
	}

}
