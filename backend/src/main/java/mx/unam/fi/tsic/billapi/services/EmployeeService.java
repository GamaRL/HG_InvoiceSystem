package mx.unam.fi.tsic.billapi.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import mx.unam.fi.tsic.billapi.entities.Employee;
import mx.unam.fi.tsic.billapi.entities.PaymentReceipt;
import mx.unam.fi.tsic.billapi.repositories.CurrencyRepository;
import mx.unam.fi.tsic.billapi.repositories.EmployeePaymentRepository;
import mx.unam.fi.tsic.billapi.repositories.EmployeeRepository;
import mx.unam.fi.tsic.billapi.repositories.PaymentReceiptRepository;
import mx.unam.fi.tsic.billapi.repositories.TaxReceiptTypeRepository;
import mx.unam.fi.tsic.billapi.responses.EmployeePaymentDetails;
import mx.unam.fi.tsic.billapi.responses.EmployeePaymentResponse;

@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService {

  private final EmployeeRepository employeeRepository;
  private final EmployeePaymentRepository employeePaymentRepository;
  private final PaymentReceiptRepository paymentReceiptRepository;
  private final TaxReceiptTypeRepository taxReceiptTypeRepository;
  private final CurrencyRepository currencyRepository;
  private final ModelMapper modelMapper;

  @Override
  public Iterable<EmployeePaymentResponse> getEmployeePaymentsById(long id) {
    Employee employee = employeeRepository.findById(id).orElseThrow();

    return () -> employeePaymentRepository.getPaymentsByEmployeeId(employee)
        .stream()
        .map(p -> modelMapper.map(p, EmployeePaymentResponse.class))
        .iterator();
  }

  @Override
  public PaymentReceipt getPaymentReceiptById(long id) {
    return paymentReceiptRepository.findById(id).orElseThrow();
  }

  @Override
  public EmployeePaymentDetails getEmployeePaymentDetailsById(long employeeId, long paymentId) {
    Employee employee = employeeRepository.findById(employeeId).orElseThrow();

    var employeePayment = employeePaymentRepository
        .findEmployeePaymentById(employee, paymentId)
        .orElseThrow();

      return modelMapper.map(employeePayment, EmployeePaymentDetails.class);
  }

  private BigDecimal calculateTotal(BigDecimal subtotal, BigDecimal taxes) {
    return subtotal.multiply(BigDecimal.ONE.subtract(taxes));
  }

  @Override
  public void generateBill(long employeeId, long paymentId) {
    Employee employee = employeeRepository.findById(employeeId).orElseThrow();

    var payment = employeePaymentRepository
        .findEmployeePaymentById(employee, paymentId)
        .orElseThrow();

    if (payment.getPaymentReceipt() == null) {
      var paymentReceipt = new PaymentReceipt();

      paymentReceipt.setInvoce(UUID.randomUUID().toString());
      paymentReceipt.setVersion("3.3");
      paymentReceipt.setSubtotal(
          payment.getAmount());

      paymentReceipt.setTaxes(new BigDecimal("0.30"));
      paymentReceipt.setTotal(calculateTotal(paymentReceipt.getSubtotal(), paymentReceipt.getTaxes()));
      paymentReceipt.setIssuePlace("04510");
      paymentReceipt.setIssuerRfc("UNAM019424GFI");
      paymentReceipt.setCreatedAt(LocalDateTime.now());

      var receiptType = taxReceiptTypeRepository.findById("N").orElseThrow();
      var currency = currencyRepository.findById("MXN").orElseThrow();

      paymentReceipt.setReceiverRfc(employee.getUser().getRfc());
      paymentReceipt.setCurrency(currency);
      paymentReceipt.setType(receiptType);
      paymentReceipt.setId(paymentId);

        paymentReceiptRepository.save(paymentReceipt);
    }
  }

}
