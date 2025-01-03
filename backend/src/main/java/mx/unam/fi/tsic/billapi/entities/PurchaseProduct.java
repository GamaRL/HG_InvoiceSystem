package mx.unam.fi.tsic.billapi.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;
import mx.unam.fi.tsic.billapi.entities.id.PurchaseProductId;

@Entity
@Table(name = "PurchaseProduct")
@Data
public class PurchaseProduct {
  @EmbeddedId
  private PurchaseProductId id;

  @Column(name = "ProductPrice", nullable = false)
  private BigDecimal price;

  @Column(name = "Quantity", nullable = false)
  private Integer quantity;

  @ManyToOne
  @JoinColumn(name = "ProductId", referencedColumnName = "Id", nullable = false)
  @MapsId("productId")
  private Product product;

  @ManyToOne
  @JoinColumn(name = "PurchaseId", referencedColumnName = "Id", nullable = false)
  @MapsId("purchaseId")
  private  Purchase purchase;
}
