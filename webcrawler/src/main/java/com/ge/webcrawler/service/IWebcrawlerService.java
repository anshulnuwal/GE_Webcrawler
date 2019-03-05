package com.ge.webcrawler.service;

import com.ge.webcrawler.model.PagesDTO;
import com.ge.webcrawler.model.ResponseDTO;

public interface IWebcrawlerService {
	
	public ResponseDTO process(PagesDTO pages);

}
