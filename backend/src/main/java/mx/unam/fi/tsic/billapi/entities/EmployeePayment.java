package mx.unam.fi.tsic.billapi.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "EmployeePayment")
@Data
public class EmployeePayment {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "Id")
  private Long id;
  
  @ManyToOne
  @JoinColumn(name = "EmployeeId", referencedColumnName = "Id", nullable = false)
  private Employee employee;


  @Column(name = "Amount")
  private BigDecimal amount;

  @Column(name = "CreatedAt", nullable = false)
  private LocalDateTime createdAt;

  @OneToOne
  @JoinColumn(name = "Id", referencedColumnName = "Id")
  private PaymentReceipt paymentReceipt;
}
