package mx.unam.fi.tsic.billapi.entities;

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
@Table(name = "[User]")
@Data
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "Id")
  private Long id;

  @Column(name = "Name", nullable = false, length = 100)
  private String name;

  @Column(name = "Email", nullable = false, unique = true, length = 50)
  private String email;

  @Column(name = "Rfc", nullable = false, unique = true, length = 13)
  private String rfc;

  @Column(name = "Password", nullable = false, length = 100)
  private String password;

  @ManyToOne
  @JoinColumn(name = "TaxRegimeCode", referencedColumnName = "Code", nullable = false)
  private TaxRegime taxRegime;

  @Column(name = "PostalCode", nullable = false)
  private String postalCode;

  @Column(name = "Role", nullable = false, length = 10)
  private String role;
}
