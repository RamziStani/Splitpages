package com.split.entities;

public class Page {

	private int page_number;
	private String content;

	public Page(int page_number, String content) {

		this.page_number = page_number;
		this.content = content;

	}

	public int getPage_number() {
		return page_number;
	}

	public void setPage_number(int page_number) {
		this.page_number = page_number;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
