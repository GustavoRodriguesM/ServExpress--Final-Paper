package br.com.web.servexpress.reports;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import br.com.web.servexpress.model.Cliente;

public class TodosClientesReport {

	private static Document document;
	private static PdfPCell hcell;
	private static PdfPTable table;

	public static ByteArrayInputStream create(List<Cliente> clientes) {

		document = new Document(PageSize.A4);
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			createTable();
			populateTable(clientes, out);

		} catch (DocumentException ex) {
			System.out.println(ex);
		}

		return new ByteArrayInputStream(out.toByteArray());
	}

	private static void populateTable(List<Cliente> clientes, ByteArrayOutputStream out) throws DocumentException {
		for (Cliente cliente : clientes) {

			PdfPCell cell;

			cell = new PdfPCell(new Phrase(cliente.getId().toString(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(
					new Phrase(cliente.getUsuario().getNome(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			cell.setPaddingLeft(5);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(cliente.getCpf(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setPaddingRight(5);
			table.addCell(cell);

			cell = new PdfPCell(
					new Phrase(cliente.getUsuario().getContato(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setPaddingRight(5);
			table.addCell(cell);

			String endereco = cliente.getEndereco().getRua() + ", " + cliente.getEndereco().getNumero() + " - "
					+ cliente.getEndereco().getBairroEntity().getNome();
			cell = new PdfPCell(new Phrase(endereco, FontFactory.getFont(FontFactory.HELVETICA, 10)));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setPaddingRight(5);
			table.addCell(cell);
		}

		PdfWriter.getInstance(document, out);
		document.open();
		document.addTitle("Teste");

		addHeader();

		document.add(table);

		document.close();
	}

	private static void createTable() throws DocumentException {
		table = new PdfPTable(5);
		table.setWidthPercentage(100);
		table.setWidths(new int[] { 1, 3, 3, 3, 3 });

		Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);

		hcell = new PdfPCell(new Phrase("#", headFont));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(hcell);

		hcell = new PdfPCell(new Phrase("Nome", headFont));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(hcell);

		hcell = new PdfPCell(new Phrase("CPF", headFont));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(hcell);

		hcell = new PdfPCell(new Phrase("Contato", headFont));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(hcell);

		hcell = new PdfPCell(new Phrase("Endereço", headFont));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(hcell);
	}

	private static void addHeader() throws DocumentException {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm dd/MM/yyyy");

		String dateFormated = format.format(Calendar.getInstance().getTime());
		document.add(new Paragraph("Data de emissão: " + dateFormated));
		document.add(new Paragraph("Descrição: relatório de todos os clientes cadastrados no sistema."));
		document.add(new Paragraph(Chunk.NEWLINE));
	}

}
