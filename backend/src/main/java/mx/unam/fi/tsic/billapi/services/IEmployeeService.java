package mx.unam.fi.tsic.billapi.services;

import mx.unam.fi.tsic.billapi.entities.PaymentReceipt;
import mx.unam.fi.tsic.billapi.responses.EmployeePaymentDetails;
import mx.unam.fi.tsic.billapi.responses.EmployeePaymentResponse;

public interface IEmployeeService {
  Iterable<EmployeePaymentResponse> getEmployeePaymentsById(long id);
  PaymentReceipt getPaymentReceiptById(long id);
  EmployeePaymentDetails getEmployeePaymentDetailsById(long employeeId, long paymentId);
  void generateBill(long employeeId, long paymentId);
}
