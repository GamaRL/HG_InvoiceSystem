package mx.unam.fi.tsic.billapi.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "ProductCatalogue")
@Data
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "Name", length = 100, nullable = false)
  private String name;

  @Column(name = "Description", length = 200, nullable = false)
  private String description;

  @Column(name = "UnitPrice", nullable = false)
  private BigDecimal unitPrice;

  @ManyToOne
  @JoinColumn(name = "UnitCode", referencedColumnName = "Code", nullable = false)
  private Unit unit;

  @ManyToOne
  @JoinColumn(name = "ProductCode", referencedColumnName = "Code", nullable = false)
  private ProductType type;
}
