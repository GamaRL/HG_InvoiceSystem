package mx.unam.fi.tsic.billapi.responses;

import java.time.LocalDateTime;
import java.util.Iterator;

import lombok.Data;

@Data
public class PurchaseDetails {
  private Long id;
  private LocalDateTime createdAt;
  private Iterator<PurchaseProductData> items;
}
