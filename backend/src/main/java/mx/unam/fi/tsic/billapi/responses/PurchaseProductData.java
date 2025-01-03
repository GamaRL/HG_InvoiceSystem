package mx.unam.fi.tsic.billapi.responses;

import java.math.BigDecimal;

import lombok.Data;
import mx.unam.fi.tsic.billapi.entities.Product;

@Data
public class PurchaseProductData {
  private Product product;
  private BigDecimal price;
  private Integer quantity;
}
