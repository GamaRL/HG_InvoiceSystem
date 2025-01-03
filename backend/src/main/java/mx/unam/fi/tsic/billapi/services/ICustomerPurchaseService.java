package mx.unam.fi.tsic.billapi.services;

import mx.unam.fi.tsic.billapi.entities.TaxReceipt;
import mx.unam.fi.tsic.billapi.responses.CustomerPurchasesResponse;
import mx.unam.fi.tsic.billapi.responses.PurchaseDetails;

public interface ICustomerPurchaseService {
  Iterable<CustomerPurchasesResponse> getPurchasesById(long id);
  TaxReceipt getTaxReceiptById(long id);
  PurchaseDetails getPurchaseDetailsById(long customerId, long purchaseId);
  void generateBill(long customerId, long purchaseId);
}
