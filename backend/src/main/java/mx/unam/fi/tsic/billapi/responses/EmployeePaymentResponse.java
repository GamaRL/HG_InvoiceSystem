package mx.unam.fi.tsic.billapi.responses;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EmployeePaymentResponse {
  
  private Long id;
  private BigDecimal amount;
  private LocalDateTime createdAt;
}
