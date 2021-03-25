package com.steventn.webscraper.project;

public class Item {
	
	private String name; 
	private String description; 
	private String url; 
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description; 
	}
	
	public void setName(String name) {
		this.name = name; 
	}
	
	public void setDescription(String description) {
		this.description = description; 
	}
	
	public void setURL(String url) {
		this.url = url;
	}
	
	public String getURL() {
		return this.url; 
	}
}
