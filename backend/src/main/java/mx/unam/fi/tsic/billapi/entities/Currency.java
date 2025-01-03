package mx.unam.fi.tsic.billapi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Currency")
@Data
public class Currency {
  
  @Id
  @Column(name = "Code")
  private String code;

  @Column(name = "Description")
  private String description;
}
