package com.ge.webcrawler.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ge.webcrawler.model.PagesDTO;
import com.ge.webcrawler.model.ResponseDTO;
import com.ge.webcrawler.service.IWebcrawlerService;
import com.ge.webcrawler.service.helper.WebcrawlerServiceHelper;

@Service("crawlerService")
public class CrawlerService implements IWebcrawlerService {

	@Autowired
	WebcrawlerServiceHelper helper;
	
	Map<String, List<String>> connectingPages;
	
	@Override
	public ResponseDTO process(PagesDTO pages) {
		ResponseDTO response = new ResponseDTO();
		response.setSuccess(new ArrayList<String>());
		response.setSkipped(new ArrayList<String>());
		response.setError(new ArrayList<String>());
		try {
			connectingPages = helper.getConnectingPagesMap(pages);
			String startPage = pages.getPages().get(0).getAddress();
			
			response.getSuccess().add(startPage);
			
			Runnable r = new NextPageService(startPage, connectingPages, response);
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

}
