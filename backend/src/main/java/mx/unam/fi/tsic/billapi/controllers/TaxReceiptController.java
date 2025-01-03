package mx.unam.fi.tsic.billapi.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import mx.unam.fi.tsic.billapi.services.IAuthService;
import mx.unam.fi.tsic.billapi.services.ICustomerPurchaseService;
import mx.unam.fi.tsic.billapi.services.IEmployeeService;
import mx.unam.fi.tsic.billapi.utils.PDFGenerator;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@CrossOrigin
@RequiredArgsConstructor
public class TaxReceiptController {
  
  private final ICustomerPurchaseService customerPurchaseService;
  private final IEmployeeService employeeService;
  private final IAuthService authService;
  private final PDFGenerator generator;
  
  @GetMapping("/api/bills/payments/{paymentId}")
  public void generatePaymentPdf(
    @CurrentSecurityContext(expression = "authentication") Authentication authentication,
    HttpServletResponse response,
    @PathVariable Long paymentId) throws DocumentException, IOException {
      var userData = authService.validate(authentication.getName());

    response.setContentType("application/pdf");
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		String headerkey = "Content-Disposition";
		String headervalue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
		response.setHeader(headerkey, headervalue);

        employeeService.generateBill(userData.getId(), paymentId);
		var purchase = employeeService.getEmployeePaymentDetailsById(userData.getId(), paymentId);
		
		generator.generateForPayment(response, purchase.getId());
  }

  @GetMapping("/api/bills/{purchaseId}")
  public void generatePdf(
    @CurrentSecurityContext(expression = "authentication") Authentication authentication,
    HttpServletResponse response,
    @PathVariable Long purchaseId) throws DocumentException, IOException {

    var userData = authService.validate(authentication.getName());

    response.setContentType("application/pdf");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		String headerkey = "Content-Disposition";
		String headervalue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
		response.setHeader(headerkey, headervalue);
		
    customerPurchaseService.generateBill(userData.getId(), purchaseId);
		var purchase = customerPurchaseService.getPurchaseDetailsById(userData.getId(), purchaseId);
		
		generator.generateForPurchase(response, purchase.getId());
  }
}
