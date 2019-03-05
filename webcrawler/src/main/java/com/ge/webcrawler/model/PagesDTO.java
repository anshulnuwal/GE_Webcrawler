package com.ge.webcrawler.model;

import java.util.List;

import javax.validation.constraints.NotEmpty;

public class PagesDTO {
	@NotEmpty
	private List<PageDTO> pages;

	public List<PageDTO> getPages() {
		return pages;
	}

	public void setPages(List<PageDTO> pages) {
		this.pages = pages;
	}

}
