package com.ge.webcrawler.controller;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ge.webcrawler.model.PagesDTO;
import com.ge.webcrawler.model.ResponseDTO;
import com.ge.webcrawler.service.IWebcrawlerService;

@RestController
public class WebCrawlerController {
	
	@Autowired
	@Qualifier("crawlerService")
	IWebcrawlerService crawlerService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseDTO crawl(@Valid @RequestBody PagesDTO dto){
		return crawlerService.process(dto);
		
	}
}
