package mx.unam.fi.tsic.billapi.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Employee")
@Data
public class Employee {
  
  @Id
  private Long id;

  @OneToOne
  @JoinColumn(name = "Id", referencedColumnName = "Id", nullable = false)
  @MapsId("id")
  private User user;

  @Column(name = "Position")
  private String position;

  @Column(name = "Salary")
  private BigDecimal salary;
}
