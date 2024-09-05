package com.startup.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.startup.model.saatracker;
import com.startup.model.sscard;


public class DownloadPDFServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	private static Font normal = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rs = null;
		if (request.getSession().getAttribute("tablename").equals("saatracker")) {
			// Set content type and header for PDF file
			get_saatracker_pdf(request, response, rs);
			rs = request.getRequestDispatcher("view.jsp");
			rs.forward(request, response);
		} else if (request.getSession().getAttribute("tablename").equals("sscard")) {
			// Set content type and header for PDF file
			get_sscard_pdf(request, response, rs);
			rs = request.getRequestDispatcher("view.jsp");
			rs.forward(request, response);
		} else {
			rs = request.getRequestDispatcher("view.jsp");
			rs.forward(request, response);
		}
	}


	private void get_sscard_pdf(HttpServletRequest request, HttpServletResponse response, RequestDispatcher rs)
			throws ServletException, IOException {
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=sscard.pdf");
		try {
			// Create new PDF document
			Document document = new Document(PageSize.A4);
			// Get output stream for PDF file
			OutputStream out = response.getOutputStream();
			// Create writer instance for PDF document
			PdfWriter.getInstance(document, out);
			// Open the document
			document.open();

			// Load the image from file
			Image logo = Image.getInstance(request.getSession().getServletContext().getRealPath("/passion-it-ptv-ltd-logo.png"));
			logo.setPaddingTop(5f);
			// Set the position of the image
			logo.setAbsolutePosition(10, 780);

			// Add the image to the PDF document
			document.add(logo);

			// Add a blank line with a black background
			Paragraph line = new Paragraph();
			line.setSpacingAfter(10f);
			line.setPaddingTop(15f);
			line.setPaddingTop(5f);
//		    line.setPadding(5f);
			// line.setBackgroundColor(BaseColor.BLACK);
			document.add(line);

			// Add the project name to the PDF document
			Paragraph projectName = new Paragraph("Audit Score Card");
			projectName.setAlignment(Element.ALIGN_CENTER);
			projectName.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 25));
			document.add(projectName);
			// Add a title to the document
			Paragraph title = new Paragraph("Table - Startup Score Card");
			title.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(title);

			// Add a table to the document
			PdfPTable table = new PdfPTable(10);
			table.setWidthPercentage(100);
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10f);

			// Add table headers
			PdfPCell cell = new PdfPCell(new Paragraph("ID"));
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph("Startup Name"));
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph("Updated Date"));
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph("Dimension"));
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph("Perspective"));
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph("Process Area"));
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph("Weight"));
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph("Max Score"));
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph("Actual Score"));
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph("Remark"));
			table.addCell(cell);

			// Fetch data from database or any other data source
			@SuppressWarnings("unchecked")
			List<sscard> sscard_data = (List<sscard>) request.getSession().getAttribute("sscard_data");
			// Loop through the data and add it to the PDF document
			for (sscard sscard : sscard_data) {
				cell = new PdfPCell(new Paragraph(sscard.getId().toString()));
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph(sscard.getStartupname()));
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph(sscard.getDate_process().toString()));
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph(sscard.getDimension()));
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph(sscard.getPerspective()));
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph(sscard.getProcessarea()));
				table.addCell(cell);
//				String weight = sscard.getWeight();
				cell = new PdfPCell(new Paragraph(Double.toString(sscard.getWeight())));
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph(Double.toString(sscard.getMaxscore())));
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph(Double.toString(sscard.getActualscore())));
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph(sscard.getRemark()));
				table.addCell(cell);
			}
			document.add(table);

			// Close the document
			document.close();

		} catch (DocumentException e) {
			e.printStackTrace();

		}

	}

	private void get_saatracker_pdf(HttpServletRequest request, HttpServletResponse response, RequestDispatcher rs)
			throws IOException, ServletException {
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=saatracker.pdf");
		try {
			// Create new PDF document
			Document document = new Document(PageSize.A4);
			// Get output stream for PDF file
			OutputStream out = response.getOutputStream();
			// Create writer instance for PDF document
			PdfWriter.getInstance(document, out);
			// Open the document
			document.open();

			// Load the image from file
			Image logo = Image.getInstance(request.getSession().getServletContext().getRealPath("/passion-it-ptv-ltd-logo.png"));
			logo.setPaddingTop(5f);
			// Set the position of the image
			logo.setAbsolutePosition(10, 780);

			// Add the image to the PDF document
			document.add(logo);

			// Add a blank line with a black background
			Paragraph line = new Paragraph();
			line.setSpacingAfter(10f);
			line.setPaddingTop(15f);
			line.setPaddingTop(5f);
//		    line.setPadding(5f);
			// line.setBackgroundColor(BaseColor.BLACK);
			document.add(line);

			// Add the project name to the PDF document
			Paragraph projectName = new Paragraph("Audit Score Card");
			projectName.setAlignment(Element.ALIGN_CENTER);
			projectName.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 25));
			document.add(projectName);

			// Add a title to the document
			Paragraph title = new Paragraph("Table - Startup Assessment Automation Tracker");
			title.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(title);

			// Add a table to the document
			PdfPTable table = new PdfPTable(6);
			table.setWidthPercentage(100);
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10f);

			// Add table headers
			PdfPCell cell = new PdfPCell(new Paragraph("ID"));
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph("Startup Name"));
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph("Date of Process"));
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph("Link URL"));
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph("Process Area"));
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph("Status"));
			table.addCell(cell);

			// Fetch data from database or any other data source
			List<saatracker> saatracker_data = (List<saatracker>) request.getSession().getAttribute("saatracker_data");
			// Loop through the data and add it to the PDF document
			for (saatracker saatracker : saatracker_data) {
				cell = new PdfPCell(new Paragraph(saatracker.getId().toString()));
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph(saatracker.getStartupname()));
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph(saatracker.getDateofprocess().toString()));
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph(saatracker.getLinkURL()));
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph(saatracker.getProcessarea()));
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph(saatracker.getStatus()));
				table.addCell(cell);
			}
			document.add(table);

			// Close the document
			document.close();

		} catch (DocumentException e) {
			e.printStackTrace();

		}

	}

}
