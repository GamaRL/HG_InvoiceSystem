package mx.unam.fi.tsic.billapi.utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

import com.lowagie.text.*;
import mx.unam.fi.tsic.billapi.services.EmployeeService;
import org.springframework.stereotype.Component;

import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import mx.unam.fi.tsic.billapi.services.ICustomerPurchaseService;

@Component
@RequiredArgsConstructor
public class PDFGenerator {

	private final ICustomerPurchaseService customerPurchaseService;
	private final EmployeeService employeeService;

	public void generateForPurchase(HttpServletResponse response, long purchaseId) throws DocumentException, IOException {
		var taxReceipt = customerPurchaseService.getTaxReceiptById(purchaseId);
		Document document = new Document(PageSize.LETTER);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();

		Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA, 20);
		Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

		// Creating paragraph
		Paragraph paragraph = new Paragraph("Factura de la compra #" + taxReceipt.getId(), fontTitle);

		// Aligning the paragraph in document
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);

		// Adding the created paragraph in document
		document.add(paragraph);


		Paragraph issuerRfc = new Paragraph("RFC Emisor: " + taxReceipt.getIssuerRfc(), dataFont);
		Paragraph issuerName = new Paragraph("Nombre Emisor: FarmaFácil SA. de CV.", dataFont);
		Paragraph receiverRfc = new Paragraph("RFC Receptor: " + taxReceipt.getReceiverRfc(), dataFont);


		document.add(issuerRfc);
		document.add(issuerName);
		document.add(receiverRfc);

		Paragraph invoice = new Paragraph("Folio: " + taxReceipt.getInvoce(), dataFont);
		Paragraph date = new Paragraph("Fecha: " + taxReceipt.getCreatedAt().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")), dataFont);
		Paragraph type = new Paragraph("Tipo de Comprobante: " + taxReceipt.getType().getCode() + "(" + taxReceipt.getType().getDescription() + ")", dataFont);

		document.add(invoice);
		document.add(date);
		document.add(type);

		// Creating a table of 3 columns
		PdfPTable table = new PdfPTable(7);

		// Setting width of table, its columns and spacing
		table.setWidthPercentage(100f);
		table.setWidths(new int[]{
				3, 3, 3, 5, 3, 3, 3
		});
		table.setSpacingBefore(5);

		// Create Table Cells for table header
		PdfPCell cell = new PdfPCell();

		// Setting the background color and padding
		cell.setBackgroundColor(CMYKColor.BLUE);
		cell.setPadding(5);

		// Creating font
		// Setting font style and size
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setColor(CMYKColor.WHITE);

		// Adding headings in the created table cell/ header
		// Adding Cell to table
		cell.setPhrase(new Phrase("Clave", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Cantidad", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Clave Unidad", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Descripción", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Valor Unitario", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Descuento", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Importe", font));
		table.addCell(cell);

		// Iterating over the list of students
		taxReceipt.getPurchase().getPurchaseProducts().forEach(product -> {
			table.addCell(product.getProduct().getType().getCode() + " / " + product.getProduct().getType().getDescription());
			table.addCell("" + product.getQuantity());
			table.addCell(product.getProduct().getUnit().getCode() + " / " + product.getProduct().getUnit().getName());
			table.addCell(product.getProduct().getDescription());
			table.addCell(product.getPrice().toString());
			table.addCell(BigDecimal.ZERO.toString());
			table.addCell(product.getPrice().multiply(new BigDecimal(product.getQuantity())).toString());
		});

		// Adding the created table to document
		document.add(table);


		Paragraph currency = new Paragraph("Moneda: " + taxReceipt.getCurrency().getDescription() + "(" + taxReceipt.getCurrency().getCode() + ")");
		Paragraph subtotal = new Paragraph(String.format("Descuento: %2.2f %%", taxReceipt.getSubtotal()));
		Paragraph discount = new Paragraph(String.format("Descuento: %2.2f %%", taxReceipt.getDiscount()));
		Paragraph taxes = new Paragraph(String.format("Impuestos: %2.2f %%", taxReceipt.getTaxes().floatValue()));
		Paragraph total = new Paragraph("Total: " + taxReceipt.getTotal());

		subtotal.setAlignment(ElementTags.ALIGN_RIGHT);
		discount.setAlignment(ElementTags.ALIGN_RIGHT);
		taxes.setAlignment(ElementTags.ALIGN_RIGHT);
		total.setAlignment(ElementTags.ALIGN_RIGHT);

		document.add(currency);
		document.add(subtotal);
		document.add(discount);
		document.add(taxes);
		document.add(total);

		// Closing the document
		document.close();
	}

	public void generateForPayment(HttpServletResponse response, long paymentId) throws DocumentException, IOException {
		var receipt = employeeService.getPaymentReceiptById(paymentId);
		Document document = new Document(PageSize.LETTER);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();

		Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA, 20);
		Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

		// Creating paragraph
		Paragraph paragraph = new Paragraph("Recibo de pago #" + receipt.getId(), fontTitle);

		// Aligning the paragraph in document
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);

		// Adding the created paragraph in document
		document.add(paragraph);


		Paragraph issuerRfc = new Paragraph("RFC Emisor: " + receipt.getIssuerRfc(), dataFont);
		Paragraph issuerName = new Paragraph("Nombre Emisor: FarmaFácil SA. de CV.", dataFont);
		Paragraph receiverRfc = new Paragraph("RFC Receptor: " + receipt.getReceiverRfc(), dataFont);


		document.add(issuerRfc);
		document.add(issuerName);
		document.add(receiverRfc);

		Paragraph invoice = new Paragraph("Folio: " + receipt.getInvoce(), dataFont);
		Paragraph date = new Paragraph("Date: " + receipt.getCreatedAt().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")), dataFont);
		Paragraph type = new Paragraph("Tipo de Comprobante: " + receipt.getType().getCode() + "(" + receipt.getType().getDescription() + ")", dataFont);

		document.add(invoice);
		document.add(date);
		document.add(type);

		// Creating a table of 3 columns
		PdfPTable table = new PdfPTable(4);

		// Setting width of table, its columns and spacing
		table.setWidthPercentage(100f);
		table.setWidths(new int[]{
				3, 5, 3, 3
		});
		table.setSpacingBefore(5);

		// Create Table Cells for table header
		PdfPCell cell = new PdfPCell();

		// Setting the background color and padding
		cell.setBackgroundColor(CMYKColor.MAGENTA);
		cell.setPadding(5);

		// Creating font
		// Setting font style and size
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setColor(CMYKColor.WHITE);

		// Adding headings in the created table cell/ header
		// Adding Cell to table
		cell.setPhrase(new Phrase("Clave", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Concepto", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Percepción", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Deducción", font));
		table.addCell(cell);

		// Iterating over the list of students
		table.addCell("1");
		table.addCell("Salario nominal");
		table.addCell(receipt.getEmployeePayment().getAmount().toString());
		table.addCell("");

		table.addCell("51");
		table.addCell("ISR");
		table.addCell("");
		table.addCell(receipt.getTaxes().multiply(receipt.getEmployeePayment().getAmount()).toString());


		// Adding the created table to document
		document.add(table);


		Paragraph currency = new Paragraph("Moneda: " + receipt.getCurrency().getDescription() + "(" + receipt.getCurrency().getCode() + ")");
		Paragraph subtotal = new Paragraph("Subtotal: " + receipt.getSubtotal());
		Paragraph taxes = new Paragraph(String.format("Impuestos: %2.2f %%", receipt.getTaxes().floatValue() * 100));
		Paragraph total = new Paragraph("Total: " + receipt.getTotal());

		subtotal.setAlignment(ElementTags.ALIGN_RIGHT);
		taxes.setAlignment(ElementTags.ALIGN_RIGHT);
		total.setAlignment(ElementTags.ALIGN_RIGHT);

		document.add(currency);
		document.add(subtotal);
		document.add(taxes);
		document.add(total);

		// Closing the document
		document.close();
	}

	/*public void generateForPayment(HttpServletResponse response, long paymentId) throws DocumentException, IOException {
		var receipt = employeeService.getPaymentReceiptById(paymentId);

		// Create document
		Document document = new Document(PageSize.LETTER);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();

		// Set fonts
		Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
		Font fontSubtitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
		Font fontContent = FontFactory.getFont(FontFactory.HELVETICA, 12);

		// Title paragraph
		Paragraph title = new Paragraph("Comprobante Fiscal Digital por Internet (CFDI)", fontTitle);
		title.setAlignment(Element.ALIGN_CENTER);
		document.add(title);

		// Add space
		document.add(Chunk.NEWLINE);

		// Company details section
		PdfPTable companyTable = new PdfPTable(2);
		companyTable.setWidthPercentage(100);
		companyTable.setSpacingBefore(10f);
		companyTable.setSpacingAfter(10f);

		PdfPCell companyCell = new PdfPCell(new Paragraph("Empresa XYZ S.A. de C.V.", fontContent));
		companyCell.setBorder(Rectangle.NO_BORDER);
		companyTable.addCell(companyCell);

		PdfPCell dateCell = new PdfPCell(new Paragraph("Fecha de Emisión: " + receipt.getCreatedAt(), fontContent));
		dateCell.setBorder(Rectangle.NO_BORDER);
		dateCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		companyTable.addCell(dateCell);

		document.add(companyTable);

		// Payment details section
		Paragraph paymentDetailsTitle = new Paragraph("Detalles del Pago", fontSubtitle);
		paymentDetailsTitle.setSpacingBefore(10f);
		paymentDetailsTitle.setSpacingAfter(10f);
		document.add(paymentDetailsTitle);

		PdfPTable paymentDetailsTable = new PdfPTable(2);
		paymentDetailsTable.setWidthPercentage(100);

		paymentDetailsTable.addCell(new PdfPCell(new Paragraph("ID del Pago:", fontContent)));
		paymentDetailsTable.addCell(new PdfPCell(new Paragraph(String.valueOf(receipt.getId()), fontContent)));

		//paymentDetailsTable.addCell(new PdfPCell(new Paragraph("Empleado:", fontContent)));
		//paymentDetailsTable.addCell(new PdfPCell(new Paragraph(receipt.getEmployeeName(), fontContent)));

		//paymentDetailsTable.addCell(new PdfPCell(new Paragraph("Fecha de Pago:", fontContent)));
		//paymentDetailsTable.addCell(new PdfPCell(new Paragraph(receipt.getPaymentDate(), fontContent)));

		paymentDetailsTable.addCell(new PdfPCell(new Paragraph("Monto:", fontContent)));
		paymentDetailsTable.addCell(new PdfPCell(new Paragraph("$" + receipt.getEmployeePayment().getAmount(), fontContent)));

		paymentDetailsTable.addCell(new PdfPCell(new Paragraph("Concepto:", fontContent)));
		paymentDetailsTable.addCell(new PdfPCell(new Paragraph("Nómina", fontContent)));

		document.add(paymentDetailsTable);

		// Additional details (example: taxes, etc.)
		Paragraph additionalDetailsTitle = new Paragraph("Detalles Adicionales", fontSubtitle);
		additionalDetailsTitle.setSpacingBefore(10f);
		additionalDetailsTitle.setSpacingAfter(10f);
		document.add(additionalDetailsTitle);

		PdfPTable additionalDetailsTable = new PdfPTable(2);
		additionalDetailsTable.setWidthPercentage(100);

		// Add additional details as needed for the CFDI
		// Example rows:
		additionalDetailsTable.addCell(new PdfPCell(new Paragraph("Impuesto:", fontContent)));
		additionalDetailsTable.addCell(new PdfPCell(new Paragraph("$" + receipt.getTaxes(), fontContent)));

		additionalDetailsTable.addCell(new PdfPCell(new Paragraph("Total:", fontContent)));
		additionalDetailsTable.addCell(new PdfPCell(new Paragraph("$" + receipt.getTotal(), fontContent)));

		document.add(additionalDetailsTable);

		// Footer
		Paragraph footer = new Paragraph("Este documento es una representación impresa de un CFDI.", fontContent);
		footer.setAlignment(Element.ALIGN_CENTER);
		footer.setSpacingBefore(20f);
		document.add(footer);

		document.close();
	}*/
}
