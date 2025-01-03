package mx.unam.fi.tsic.billapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import mx.unam.fi.tsic.billapi.responses.EmployeePaymentDetails;
import mx.unam.fi.tsic.billapi.responses.EmployeePaymentResponse;
import mx.unam.fi.tsic.billapi.services.EmployeeService;
import mx.unam.fi.tsic.billapi.services.IAuthService;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class EmployeeController {

  private final IAuthService authService;
  private final EmployeeService employeeService;

  @GetMapping("/api/employee/payment")
  public ResponseEntity<Iterable<EmployeePaymentResponse>> getEmployeePayment(
    @CurrentSecurityContext(expression = "authentication") Authentication authentication) {

    var userData = authService.validate(authentication.getName());
    return ResponseEntity.ok(employeeService.getEmployeePaymentsById(userData.getId()));
  }

  @GetMapping("/api/employee/payment/{paymentId}")
  public ResponseEntity<EmployeePaymentDetails> getEmployeePaymentDetails(
    @CurrentSecurityContext(expression = "authentication") Authentication authentication,
    @PathVariable Long paymentId) {

      var userData = authService.validate(authentication.getName());

      var items = employeeService.getEmployeePaymentDetailsById(userData.getId(), paymentId);
      return ResponseEntity.ok(items);
  }
  
}