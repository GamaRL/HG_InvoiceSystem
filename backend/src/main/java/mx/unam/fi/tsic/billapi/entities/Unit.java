package mx.unam.fi.tsic.billapi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "UnitsCatalogue")
@Data
public class Unit {
  @Id
  @Column(name = "Code", length = 4)
  private String code;

  @Column(name = "Name", length = 50)
  private String name;
}
