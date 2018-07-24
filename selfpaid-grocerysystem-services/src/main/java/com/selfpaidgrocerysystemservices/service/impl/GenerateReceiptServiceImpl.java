package com.selfpaidgrocerysystemservices.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.selfpaidgrocerysystemservices.controller.SelfCheckoutController;
import com.selfpaidgrocerysystemservices.service.GenerateReceiptService;

@Service
public class GenerateReceiptServiceImpl implements GenerateReceiptService {
	
	private static Logger logger = LoggerFactory.getLogger(SelfCheckoutController.class);

	@Override
	public void generateReceipt() {

		Document document = null;
		PdfWriter writer = null;
		try {
			document = new Document();
			writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\SUNIL\\Downloads\\Test.pdf"));
			document.open();
			document.add(new Paragraph("Testing PDF "));
			
		} catch(ClassCastException | NullPointerException | DocumentException | FileNotFoundException e) {
			logger.error("Exception occured in GenerateReceiptServiceImpl:generateReceipt method" + e.getMessage());
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			document.close();
			writer.close();
		}
		
	}

	
}
