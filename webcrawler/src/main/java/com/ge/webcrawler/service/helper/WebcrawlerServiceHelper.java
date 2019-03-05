package com.ge.webcrawler.service.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ge.webcrawler.model.PageDTO;
import com.ge.webcrawler.model.PagesDTO;

@Component
public class WebcrawlerServiceHelper {

	public Map<String, List<String>> getConnectingPagesMap(PagesDTO pages){
		Map<String, List<String>> cm = new HashMap<>();
		for (PageDTO page : pages.getPages()) {
			cm.put(page.getAddress(), page.getLinks());
		}
		return cm;
	}
}
