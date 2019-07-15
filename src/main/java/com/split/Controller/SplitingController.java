package com.split.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;

import com.split.entities.Page;
import com.split.service.SplitingService;

@RestController
public class SplitingController {
	
	@Autowired
	SplitingService splitingService;

	
	//pages extraction 
	@GetMapping(value="/extraction")
	
	 public List<Page> extract_content() throws InvalidPasswordException, IOException {

	     List <Page>   pages = new ArrayList<Page>();
	     pages = splitingService.Extract();
	     
	     return pages;
	        
	    }
	//test
		@GetMapping(value="/test")
		
		 public List<Page> extract() throws InvalidPasswordException, IOException {

		     List <Page>   pages = new ArrayList<Page>();
		     pages = splitingService.Extract();
		     
		     return pages;
		        
		    }
	
}
