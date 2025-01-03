package mx.unam.fi.tsic.billapi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "TaxRegimeCatalogue")
@Data
public class TaxRegime {
  @Id
  @Column(name = "Code", length = 3, nullable = false)
  private String code;

  @Column(name = "Description", length = 100, nullable = false)
  private String description;
}
