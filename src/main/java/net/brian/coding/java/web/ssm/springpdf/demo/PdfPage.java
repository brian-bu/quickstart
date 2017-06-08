package net.brian.coding.java.web.ssm.springpdf.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfPage extends AbstractIText5PdfView {
	protected void buildPdfDocument(Map model, Document doc, PdfWriter writer, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		List words = (List) model.get("wordList");
		for (int i = 0; i < words.size(); i++) {
			doc.add(new Paragraph((String) words.get(i)));
		}
	}
}