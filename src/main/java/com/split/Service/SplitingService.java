package com.split.service;

import org.apache.pdfbox.cos.COSInputStream;
import org.apache.pdfbox.multipdf.Splitter;
import org.xml.sax.ContentHandler;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.split.entities.Page;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class SplitingService {

	public List<Page> Extract_pages() throws InvalidPasswordException, IOException {

		String pdfFileInText = "";
		String result = " ";
		List<Page> Extracted_pages = new ArrayList<Page>();

		// Loading an existing PDF document
		File file = new File("C:/Users/rakkari/Desktop/Total.pdf");

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

					pdfFileInText = tStripper.getText(doc);
				}
				// trim "\\r?\\n" from all the page
				String lines[] = pdfFileInText.split("\\r?\\n");
				for (String line : lines) {
					result = result + " " + line;
				}
				// creating the page i+1 is the number of the page & result is the text
				Page page = new Page(i + 1, result);
				Extracted_pages.add(page);
			}

		}
		return Extracted_pages;
	}
	public List<Page> Extract() throws InvalidPasswordException, IOException {
		String pdfFileInText = "";
		String result = " ";
		List<Page> Extracted_pages = new ArrayList<Page>();

		// Loading an existing PDF document
		File file = new File("C:/Users/rakkari/Desktop/Total.pdf");

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

					pdfFileInText = tStripper.getText(doc).replace("\r\n", "");
				}
				// trim "\\r?\\n" from all the page
				
				// creating the page i+1 is the number of the page & result is the text
				Page page = new Page(i + 1, pdfFileInText);
				Extracted_pages.add(page);
			}

		}
		return Extracted_pages;}
}