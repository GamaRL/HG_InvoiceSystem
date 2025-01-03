package mx.unam.fi.tsic.billapi.entities.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class PurchaseProductId {
  
  @Column(name = "PurchaseId")
  private Long purchaseId;

  @Column(name = "ProductId")
  private Long productId;
}
