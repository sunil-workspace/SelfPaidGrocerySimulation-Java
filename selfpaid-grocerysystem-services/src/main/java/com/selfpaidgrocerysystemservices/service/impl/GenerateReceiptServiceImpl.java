package com.selfpaidgrocerysystemservices.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.selfpaidgrocerysystemservices.controller.SelfCheckoutController;
import com.selfpaidgrocerysystemservices.dto.Item;
import com.selfpaidgrocerysystemservices.dto.ItemSelected;
import com.selfpaidgrocerysystemservices.service.GenerateReceiptService;
import com.selfpaidgrocerysystemservices.service.constants.SelfpaidConstants;
import com.selfpaidgrocerysystemservices.service.pdf.PDFCreator;

@Service
public class GenerateReceiptServiceImpl implements GenerateReceiptService {

	private static Logger logger = LoggerFactory.getLogger(SelfCheckoutController.class);

	@Autowired
	ItemDetailsServiceImpl itemDetailsServiceImpl;

	@Autowired
	EmailServiceImpl emailServiceImpl;
	
	@Autowired
	PDFCreator pdfCreator;

	@Override
	public void generateReceipt() {

		//Document document = null;

		String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		fileName = "Receipt_"+fileName+".pdf";
		
		try {

			/*document = new Document();
			writer = PdfWriter.getInstance(document, new FileOutputStream("/Users/VijayPeddy/Downloads/SelfPaidGrocerySyatem_PDFs/Receipt_"+fileName+".pdf"));
			document.open();
			document.add(new Paragraph("Testing PDF "));*/

			java.util.List<ItemSelected> itemsSelected = itemDetailsServiceImpl.getItemsPurchased();
			logger.info("items: " + itemsSelected.size());

			//Path path = Paths.get(ClassLoader.getSystemResource("receipt_logo.png").toURI());

			//document = new Document();
			
			
			/*Image img = Image.getInstance(path.toAbsolutePath().toString());
			document.add(img);*/
			
			pdfCreator.createPdf(fileName, itemsSelected);
			//PdfWriter.getInstance(document, new FileOutputStream(SelfpaidConstants.receiptPath+fileName));

			sendEmail(fileName);

		} catch(ClassCastException | NullPointerException e) {
			logger.error("Exception occured in GenerateReceiptServiceImpl:generateReceipt method" + e.getMessage());
		} catch(Exception e) {
			e.printStackTrace();
		}/* finally {
			document.close();
		}*/

	}

	private void sendEmail(String fileName) {
		emailServiceImpl.sendReceiptAttachment("Receipt", "Please find the attachment", fileName);
	}

	public void onEndPage(PdfWriter writer, Document document) {
		PdfPTable table = new PdfPTable(2);
		try {
			table.setWidths(new int[]{200, 30});
			table.setLockedWidth(true);
			table.getDefaultCell().setBorder(Rectangle.SUBJECT);
			table.addCell("Receipt");
			table.addCell(String.format("Page %d ", writer.getPageNumber()));
			Rectangle page = document.getPageSize();
			table.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
			table.writeSelectedRows(0, -1, document.leftMargin(), page.getHeight() - document.topMargin()
					+ table.getTotalHeight()+5, writer.getDirectContent());          
		} catch(DocumentException de) {
			throw new ExceptionConverter(de);
		}
	}


}
