package mx.unam.fi.tsic.billapi.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Purchase")
@Data
public class Purchase {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "CustomerId", referencedColumnName = "Id", nullable = false)
  private Customer customer;

  @OneToMany(mappedBy = "purchase")
  private List<PurchaseProduct> purchaseProducts;

  @Column(name = "CreatedAt", nullable = false)
  private LocalDateTime createdAt;

  @OneToOne
  @JoinColumn(name = "Id", referencedColumnName = "Id")
  private TaxReceipt taxReceipt;
}
