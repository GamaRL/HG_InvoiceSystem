package mx.unam.fi.tsic.billapi.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import mx.unam.fi.tsic.billapi.entities.Customer;
import mx.unam.fi.tsic.billapi.entities.TaxReceipt;
import mx.unam.fi.tsic.billapi.repositories.CurrencyRepository;
import mx.unam.fi.tsic.billapi.repositories.CustomerPurchaseRepository;
import mx.unam.fi.tsic.billapi.repositories.CustomerRepository;
import mx.unam.fi.tsic.billapi.repositories.PurchaseProductRepository;
import mx.unam.fi.tsic.billapi.repositories.TaxReceiptRepository;
import mx.unam.fi.tsic.billapi.repositories.TaxReceiptTypeRepository;
import mx.unam.fi.tsic.billapi.responses.CustomerPurchasesResponse;
import mx.unam.fi.tsic.billapi.responses.PurchaseDetails;
import mx.unam.fi.tsic.billapi.responses.PurchaseProductData;

@Service
@RequiredArgsConstructor
public class CustomerPurchaseService implements ICustomerPurchaseService {

  private final CustomerPurchaseRepository customerPurchaseRepository;
  private final PurchaseProductRepository purchaseProductRepository;
  private final CustomerRepository customerRepository;
  private final TaxReceiptRepository taxReceiptRepository;
  private final TaxReceiptTypeRepository taxReceiptTypeRepository;
  private final CurrencyRepository currencyRepository;
  private final ModelMapper modelMapper;

  @Override
  public Iterable<CustomerPurchasesResponse> getPurchasesById(long id) {
    
    Customer customer = customerRepository.findById(id).orElseThrow();
    return () -> customerPurchaseRepository.getPurchasesByCustomerId(customer)
    .stream()
    .map(p -> {
      return modelMapper.map(p, CustomerPurchasesResponse.class);
    })
    .iterator();
  }

  @Override
  public PurchaseDetails getPurchaseDetailsById(long customerId, long purchaseId) {

    Customer customer = customerRepository.findById(customerId).orElseThrow();

    var purchase = customerPurchaseRepository
      .findCustomerPurchaseById(customer, purchaseId)
      .orElseThrow();
      

    var items = purchaseProductRepository.getPurchaseProductsById(purchaseId).stream()
      .map(p -> {
        return modelMapper.map(p, PurchaseProductData.class);
      })
      .iterator();

    var response = modelMapper.map(purchase, PurchaseDetails.class);


    response.setItems(items);

    return response;
  }

  private BigDecimal calculateTotal(BigDecimal subtotal, BigDecimal discount, BigDecimal taxes) {
    return subtotal.subtract(discount).multiply(BigDecimal.ONE.add(taxes));
  }

  @Override
  public void generateBill(long customerId, long purchaseId) {
    
    Customer customer = customerRepository.findById(customerId).orElseThrow();

    var purchase = customerPurchaseRepository
      .findCustomerPurchaseById(customer, purchaseId)
      .orElseThrow();
    
    if (purchase.getTaxReceipt() == null) {
      var taxReceipt = new TaxReceipt();

      taxReceipt.setInvoce(UUID.randomUUID().toString());
      taxReceipt.setVersion("3.3");
      taxReceipt.setSubtotal(
        purchase.getPurchaseProducts()
        .stream()
        .map(p -> p.getPrice().multiply(new BigDecimal(p.getQuantity())))
        .reduce(BigDecimal.ZERO, BigDecimal::add)
      );

      taxReceipt.setDiscount(BigDecimal.ZERO);
      taxReceipt.setTaxes(new BigDecimal("0.16"));
      taxReceipt.setTotal(calculateTotal(taxReceipt.getSubtotal(), taxReceipt.getDiscount(), taxReceipt.getTaxes()));
      taxReceipt.setIssuePlace("04510");
      taxReceipt.setIssuerRfc("UNAM019424GFI");
      taxReceipt.setCreatedAt(LocalDateTime.now());

      var receiptType = taxReceiptTypeRepository.findById("I").orElseThrow();
      var currency = currencyRepository.findById("MXN").orElseThrow();

      taxReceipt.setReceiverRfc(customer.getUser().getRfc());
      taxReceipt.setCurrency(currency);
      taxReceipt.setType(receiptType);
      taxReceipt.setId(purchaseId);

      //taxReceipt.setPurchase(purchase);
      //purchase.setTaxReceipt(taxReceipt);

      taxReceipt = taxReceiptRepository.save(taxReceipt);

    }
  }

  @Override
  public TaxReceipt getTaxReceiptById(long id) {
    return taxReceiptRepository.findById(id).orElseThrow();
  }
  
}
