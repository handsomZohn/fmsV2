package com.casic.fms.service.impl;

import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Map;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.GrayColor;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

public class PDFServiceImp {

	
	/**
	 * 测试PDF文件
	 * @param str
	 */
	public static void main(String[] str){
		try{
			PdfReader reader = new PdfReader("/Volumes/BAK/test.pdf");
	
		    Map info = reader.getInfo();
	
		    for (Iterator i = info.keySet().iterator(); i.hasNext();) {
		      String key = (String) i.next();
		      String value = (String) info.get(key);
		      System.out.println(key + ": " + value);
		    }
		    reader.close();
		    
		    Document document = new Document(PageSize.A4);
		    reader = new PdfReader("/Volumes/BAK/test.pdf");
		    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(
		        "/Volumes/BAK/testpages.jpg"));
		    document.open();
		    Paragraph  pgh = new Paragraph("This is page 1:");
		    document.add(pgh);
		    PdfImportedPage page = writer.getImportedPage(reader, 1);
		   
		    Image image = Image.getInstance(page);
		    image.scalePercent(15f);
		    image.setBorder(Rectangle.BOX);
		    image.setBorderWidth(3f);
		    image.setBorderColor(new GrayColor(0.5f));
		    
		    document.add(image);
		    
		    
		    document.add(new Paragraph("This is page 2:"));
		    page = writer.getImportedPage(reader, 2);
		    image = Image.getInstance(page);
		    image.scalePercent(15f);
		    image.setBorder(Rectangle.BOX);
		    image.setBorderWidth(3f);
		    image.setBorderColor(new GrayColor(0.5f));
		    document.add(image);
		    document.add(new Paragraph("This is page 3:"));
		    
		    page = writer.getImportedPage(reader, 4);
		    image = Image.getInstance(page);
		    image.scalePercent(15f);
		    image.setBorder(Rectangle.BOX);
		    image.setBorderWidth(3f);
		    image.setBorderColor(new GrayColor(0.5f));
		    document.add(image);
		    
//		    Rectangle rect = new Rectangle(0, 0, (int) page.getWidth(), (int) page.getHeight());  
//		    BufferedImage tag =new BufferedImage((int)rect.getWidth(),(int)rect.getHeight(),BufferedImage.TYPE_INT_BGR);  
            //tag.getGraphics().drawImage(img, x, y, width, height, observer);
            	//image, 0, 0, (int)rect.getWidth(), (int)rect.getHeight(),  null); 
//            FileOutputStream out = new FileOutputStream("/Volumes/BAK/pdfImg.jpg");
//            out.write(image.getRawData());
//            out.close();  		    
//		    System.out.println("Tampered? " + reader.isTampered());
		    document.close();		 
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
}
