package com.split.Service;

import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.springframework.stereotype.Service;

import com.split.entities.Greeting;

import java.io.File; 
import java.io.IOException;
import java.util.ArrayList;
import java.util.List; 
@Service
public class SplitingService {

	
	public List <Greeting> countpages() throws InvalidPasswordException, IOException
	{
		String pdfFileInText="";
		String result  =" ";
		List <Greeting> Greetings = new ArrayList<Greeting>(); 
		//Loading an existing PDF document
	      File file = new File("C:/Users/rakkari/Desktop/Airbus.pdf");
	     
	      PDDocument document = PDDocument.load(file); 

	      //Instantiating Splitter class
	      Splitter splitter = new Splitter();

	      //splitting the pages of a PDF document
	      List<PDDocument> Pages = splitter.split(document);

		for (int i =0; i<Pages.size();i++) {
			 try (PDDocument doc= Pages.get(i)) {

		            doc.getClass();

		            if (!document.isEncrypted()) {
					
		                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
		                stripper.setSortByPosition(true);

		                PDFTextStripper tStripper = new PDFTextStripper();

		                 pdfFileInText = tStripper.getText(doc);}
		            String lines[] = pdfFileInText.split("\\r?\\n");
		            for (String line : lines) {
	                    result = result + " "+ line;
	                }
		            Greeting g = new Greeting(i+1, result);
		            Greetings.add(g);
		}
		
	}
		return Greetings;
}}