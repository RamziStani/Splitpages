package com.split.service;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.springframework.stereotype.Service;
import com.split.entities.Page;

import java.awt.geom.Rectangle2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SplitingService {

	public List<Page> Extract_pages() throws InvalidPasswordException, IOException {
		String pdfFileInText = "";
		String result ="";
		
		List<Page> Extracted_pages = new ArrayList<Page>();
		//createing txt file
				FileWriter wite = new FileWriter("C:/Users/rakkari/Desktop/Total.txt");
				BufferedWriter bw = new BufferedWriter(wite);
		// Loading an existing PDF document
		File file = new File("C:/Users/rakkari/Desktop/tot.pdf");

		PDDocument document = PDDocument.load(file);
		//Remove Bookmarks
document.getDocumentCatalog().setDocumentOutline(null);
		// Instantiating Splitter class
		Splitter splitter = new Splitter();

		// splitting the pages of a PDF document
		List<PDDocument> Pages = splitter.split(document);

		for (int i = 0; i < Pages.size(); i++) {
			try (PDDocument doc = Pages.get(i)) {

				doc.getClass();

				if (!document.isEncrypted()) {
//Ignore headers and footers
					//Rectangle2D region = new Rectangle2D.Double(0,100,550,700);
					Rectangle2D region = new Rectangle2D.Double(0,50,550,700);
					String regionName = "region";
					PDFTextStripperByArea stripper;
					stripper = new PDFTextStripperByArea();
					stripper.addRegion(regionName, region);
					PDPage page = doc.getPage(0);
					stripper.extractRegions(page);
					pdfFileInText= stripper.getTextForRegion(regionName).replace("\r\n", " ").trim();
					
					
				}
				

				// creating the page i+1 is the number of the page & pdfFileInText is the text
				Page page = new Page(i + 1, pdfFileInText);
				Extracted_pages.add(page);
			}

		}
		
		return Extracted_pages;
		
	}

	public List<Page> Extract() throws InvalidPasswordException, IOException {
		String pdfFileInText = "";
		String result ="";
		
		List<Page> Extracted_pages = new ArrayList<Page>();
		//createing txt file
				FileWriter wite = new FileWriter("C:/Users/rakkari/Desktop/Total.txt");
				BufferedWriter bw = new BufferedWriter(wite);
		// Loading an existing PDF document
		File file = new File("C:/Users/rakkari/Desktop/tot.pdf");

		PDDocument document = PDDocument.load(file);

		// Instantiating Splitter class
		Splitter splitter = new Splitter();

		// splitting the pages of a PDF document
		List<PDDocument> Pages = splitter.split(document);

		for (int i = 0; i < Pages.size(); i++) {
			try (PDDocument doc = Pages.get(i)) {

				doc.getClass();

				if (!document.isEncrypted()) {

					PDFTextStripperByArea stripper = new PDFTextStripperByArea();
					stripper.setSortByPosition(true);

					PDFTextStripper tStripper = new PDFTextStripper();

					pdfFileInText = tStripper.getText(doc).replace("\r\n", " ");
					
				}
				

				// creating the page i+1 is the number of the page & pdfFileInText is the text
				Page page = new Page(i + 1, pdfFileInText);
				Extracted_pages.add(page);
			}

		}
		
		return Extracted_pages;
	}
	
	
	public List<Page> Extraction() throws InvalidPasswordException, IOException {
		String pdfFileInText = "";
		String result ="";
		
		List<Page> Extracted_pages = new ArrayList<Page>();
		File file = new File("C:/Users/rakkari/Desktop/tot.pdf"); 
	    PDDocument document = PDDocument.load(file);
	    PDFTextStripper pdfStripper = new PDFTextStripper();
	    
	    FileWriter wite = new FileWriter("C:/Users/rakkari/Desktop/Total.txt");
		BufferedWriter bw = new BufferedWriter(wite);
	    //load all lines into a string
	    String pages = pdfStripper.getText(document);

	    //split by detecting newline
	    String[] lines = pages.split("\\r\\n\\r\\n");

	    int count=1;   //Just to indicate line number
	    for(String temp:lines)
	    {
	        
	       
	        bw.write(temp.trim());
	        bw.newLine();
	    }
	    bw.close();
			return Extracted_pages;
	}
}