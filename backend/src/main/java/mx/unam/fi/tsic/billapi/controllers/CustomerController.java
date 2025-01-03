package mx.unam.fi.tsic.billapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import mx.unam.fi.tsic.billapi.responses.CustomerPurchasesResponse;
import mx.unam.fi.tsic.billapi.responses.PurchaseDetails;
import mx.unam.fi.tsic.billapi.services.IAuthService;
import mx.unam.fi.tsic.billapi.services.ICustomerPurchaseService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@CrossOrigin
@RequiredArgsConstructor
public class CustomerController {

  private final IAuthService authService;
  private final ICustomerPurchaseService customerPurchaseService;

  @GetMapping("/api/customer/purchase")
  public ResponseEntity<Iterable<CustomerPurchasesResponse>> getCustomerPurchase(
    @CurrentSecurityContext(expression = "authentication") Authentication authentication) {

    var userData = authService.validate(authentication.getName());
    return ResponseEntity.ok(customerPurchaseService.getPurchasesById(userData.getId()));
  }

  @GetMapping("/api/customer/purchase/{purchaseId}")
  public ResponseEntity<PurchaseDetails> getMethodName(
    @CurrentSecurityContext(expression = "authentication") Authentication authentication,
    @PathVariable Long purchaseId) {

      var userData = authService.validate(authentication.getName());

      var items = customerPurchaseService.getPurchaseDetailsById(userData.getId(), purchaseId);
      return ResponseEntity.ok(items);
  }
  
}
