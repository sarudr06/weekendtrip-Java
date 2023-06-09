package com.feuji.weekendtrip.serviceImplementation;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.feuji.weekendtrip.model.Passenger;
import com.feuji.weekendtrip.model.Traveller;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PdfGeneratorForAdmin {

	List<Passenger> passengersList;
	List<Traveller> travellersList;

	public List<Passenger> getPassengersList() {
		return passengersList;
	}

	public void setPassengersList(List<Passenger> passengersList) {
		this.passengersList = passengersList;
	}

	public List<Traveller> getTravellersList() {
		return travellersList;
	}

	public void setTravellersList(List<Traveller> travellersList) {
		this.travellersList = travellersList;
	}

	public void generate(HttpServletResponse response) throws DocumentException, IOException {

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "inline; filename=Mypdf.pdf");
		Document document = new Document(PageSize.A4);

		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		Rectangle rect = new Rectangle(577, 825, 18, 15); // you can resize rectangle
		rect.enableBorderSide(1);
		rect.enableBorderSide(2);
		rect.enableBorderSide(4);
		rect.enableBorderSide(8);
		rect.setBorderColor(BaseColor.BLACK);
		rect.setBorderWidth(1);
		document.add(rect);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);

		Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTiltle.setSize(30);

		Paragraph paragraph = new Paragraph("Traveller's Details", fontTiltle);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(paragraph);

		Image img = Image.getInstance(
				ClassLoader.getSystemResource("weekendtrips-low-resolution-logo-color-on-transparent-background.png"));

		img.scaleAbsolute(146, 70);

		Phrase phrase = new Phrase();
		phrase.add(new Chunk(img, 390, 0));
		document.add(new Paragraph(phrase));
		document.add(Chunk.NEWLINE);
		for (Traveller tList : travellersList) {
			document.add(new Paragraph("Traveller Id : " + tList.getTravellerId()));
			document.add(new Paragraph("Traveller Email : " + tList.getTravellerEmail()));
			
			List<Passenger> passengersList =tList.getPassenger();
			
			
			long menCount = passengersList.stream().filter((e) -> e.getPassengerGender().equalsIgnoreCase("male")).count();
			long womenCount = passengersList.stream().filter((e) -> e.getPassengerGender().equalsIgnoreCase("female")).count();
	        long childCount=passengersList.stream().filter((e)->e.getPassengerAge()<5).count();
			
	        
	        document.add(new Paragraph("List of Passengers -" + tList.getPassenger().size()+"                                                                    "+"Male -"+menCount+"   "+"Female-"+womenCount+"    Child-"+childCount));
	
			PdfPTable table = new PdfPTable(3);
			table.setWidthPercentage(100f);
			table.setWidths(new int[] { 3, 3, 3});
			table.setSpacingBefore(3);

			PdfPCell cell = new PdfPCell();
			cell.setBackgroundColor(CMYKColor.WHITE);
			cell.setPadding(3);
			Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
			font.setColor(CMYKColor.BLACK.darker());

			cell.setPhrase(new Phrase("Name", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Age", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Gender", font));
			table.addCell(cell);
			for (Passenger passenger : passengersList) {
				if (passenger.getTraveller().getTravellerId().equals(tList.getTravellerId())) {
					table.addCell(passenger.getPassengerName());
					table.addCell(String.valueOf(passenger.getPassengerAge()));
					table.addCell(passenger.getPassengerGender());
				}
			}
			document.add(table);
			document.add(new Paragraph(
					".............................................................................................................................."));

		}
		document.add(Chunk.NEWLINE);
		document.setPageCount(2);
		document.addAuthor("MAHESH");
		document.setRole(new PdfName("MY PDF"));

		document.close();
	}
}