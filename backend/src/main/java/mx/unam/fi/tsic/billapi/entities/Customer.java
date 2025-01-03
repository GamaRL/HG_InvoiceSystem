package mx.unam.fi.tsic.billapi.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Customer")
@Data
public class Customer {

  @Id
  private Long id;

  @OneToOne
  @JoinColumn(name = "Id", referencedColumnName = "Id", nullable = false)
  @MapsId("id")
  private User user;

  @OneToMany(mappedBy = "customer")
  private List<Purchase> customer;
}
