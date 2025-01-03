package mx.unam.fi.tsic.billapi.responses;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EmployeePaymentDetails {
  private Long id;
  private LocalDateTime createdAt;
}
