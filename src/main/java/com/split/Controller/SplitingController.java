package com.split.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import com.split.Service.SplitingService;
import com.split.entities.Greeting;

@RestController
public class SplitingController {
	
	@Autowired
	SplitingService splitingService;

	@GetMapping(value="/hello")
    public List<Greeting> sayHello() throws InvalidPasswordException, IOException {

     List <Greeting>   g = new ArrayList<Greeting>();
     g= splitingService.countpages();
     
     return g;
        
    }
	
	
}
