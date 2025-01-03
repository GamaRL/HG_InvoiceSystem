package mx.unam.fi.tsic.billapi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "ProductTypeCatalogue")
@Data
public class ProductType {
  @Id
  @Column(name = "Code", length = 10)
  private String code;

  @Column(name = "Description", length = 50)
  private String description;
}
