package com.steventn.webscraper.project;

import java.util.List;

import org.eclipse.jetty.util.IO;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Scrapper {
	
	private static final String searchUrl = "https://www.allrecipes.com/search/results/?search=chicken"; 
	private static String pagePath = "//*[@class ='card__detailsContainer']";
	
	public static void main(String[] args) {
		WebClient client = new WebClient();
		client.getOptions().setJavaScriptEnabled(false);
		client.getOptions().setCssEnabled(false);
		client.getOptions().setUseInsecureSSL(true);
		
		try {
			HtmlPage page = client.getPage(searchUrl); 
			List<HtmlElement> items = (List<HtmlElement>)page.getByXPath(pagePath);		
			
			if(items.isEmpty()) {
				  System.out.println("No items found !");
			} else {
				for(HtmlElement item : items){
				  HtmlAnchor itemAnchor = ((HtmlAnchor) item.getFirstByXPath(".//a[@class='card__titleLink manual-link-behavior']"));
				  HtmlElement itemDescriptionAnchor = ((HtmlElement) item.getFirstByXPath(".//div[contains(@class,'card__summary')]"));
				  String itemName = itemAnchor.asText();
				  String itemDescription = itemDescriptionAnchor.asText();
				  String itemUrl =  itemAnchor.getHrefAttribute();
							
	//			  System.out.println( String.format("Name : %s Url : %s Description : %s", itemName, itemUrl, itemDescription));
				  
				  Item itemObj = new Item();
				  itemObj.setName(itemName);
				  itemObj.setDescription(itemDescription);
				  itemObj.setURL(itemUrl);
				  
				  ObjectMapper mapper = new ObjectMapper();
				  String jsonString = mapper.writeValueAsString(itemObj) ;
				  System.out.println(jsonString);
				  
				  
				  }
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}
}
