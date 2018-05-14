package br.com.web.servexpress.reports;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.jfree.chart.JFreeChart;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.DefaultFontMapper;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

public class GraficoNumeroPedidosMes {

	public static ByteArrayInputStream create(JFreeChart chart, int width, int height) {
		PdfWriter writer = null;

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
			writer = PdfWriter.getInstance(document, out);
			document.open();
			
			addHeader(document);

			PdfContentByte contentByte = writer.getDirectContent();
			PdfTemplate template = contentByte.createTemplate(1000, 800);
			Graphics2D graphics2d = template.createGraphics(500, 400, new DefaultFontMapper());
			Rectangle2D rectangle2d = new Rectangle2D.Double(0, 0, width, height);
			chart.draw(graphics2d, rectangle2d);
			graphics2d.dispose();	
			contentByte.addTemplate(template, 50, 300);

		} catch (Exception e) {
			e.printStackTrace();
		}
		document.close();

		return new ByteArrayInputStream(out.toByteArray());
	}
	
	private static void addHeader(Document document) throws DocumentException {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm dd/MM/yyyy");

		String dateFormated = format.format(Calendar.getInstance().getTime());
		document.add(new Paragraph("Data de emissão: " + dateFormated));
		document.add(new Paragraph("Descrição: Gráfico em barras de número pedidos por mês"));
		document.add(new Paragraph(Chunk.NEWLINE));
	}
	
}
