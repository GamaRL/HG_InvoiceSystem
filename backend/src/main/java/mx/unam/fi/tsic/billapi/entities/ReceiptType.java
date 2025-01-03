package mx.unam.fi.tsic.billapi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * ReceiptType
 */
@Entity
@Table(name = "ReceiptTypeCatalogue")
@Data
public class ReceiptType {
  
  @Id
  @Column(name = "Code", nullable = false)
  private String code;

  @Column(name = "Description", nullable = false)
  private String description;
}