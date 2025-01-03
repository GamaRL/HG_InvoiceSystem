package mx.unam.fi.tsic.billapi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.unam.fi.tsic.billapi.entities.Customer;
import mx.unam.fi.tsic.billapi.entities.Purchase;

@Repository
public interface CustomerPurchaseRepository extends CrudRepository<Purchase, Long>{
  
  @Query("SELECT p FROM Purchase p WHERE p.customer = :customer ORDER BY p.createdAt ASC")
  List<Purchase> getPurchasesByCustomerId(Customer customer);

  @Query("SELECT p FROM Purchase p WHERE p.customer = :customer AND p.id = :purchaseId")
  Optional<Purchase> findCustomerPurchaseById(Customer customer, Long purchaseId);
}
