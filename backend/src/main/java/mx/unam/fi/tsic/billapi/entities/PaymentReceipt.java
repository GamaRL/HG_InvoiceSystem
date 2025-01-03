package mx.unam.fi.tsic.billapi.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "PaymentReceipt")
@Data
public class PaymentReceipt {
  
  @Id
  @Column(name = "Id")
  private Long id;

  @Column(name = "Invoice", length = 30, nullable = false, unique = true)
  private String invoce;

  @Column(name = "Version", length = 4, nullable = false)
  private String version;

  @Column(name = "Total", nullable = false)
  private BigDecimal total;
  
  @Column(name = "Subtotal", nullable = false)
  private BigDecimal subtotal;

  @Column(name = "Taxes", nullable = false)
  private BigDecimal taxes;

  @ManyToOne
  @JoinColumn(name = "Type", referencedColumnName = "Code")
  private ReceiptType type;

  @ManyToOne
  @JoinColumn(name = "CurrencyCode", referencedColumnName = "Code")
  private Currency currency;

  @Column(name = "IssuePlace")
  private String issuePlace;

  @Column(name = "IssuerRfc")
  private String issuerRfc;

  @Column(name = "ReceiverRfc", nullable = false)
  private String receiverRfc;

  @Column(name = "CreatedAt")
  private LocalDateTime createdAt;

  @OneToOne
  @JoinColumn(name = "Id", referencedColumnName = "Id", nullable = false)
  @MapsId("id")
  private EmployeePayment employeePayment;
}
