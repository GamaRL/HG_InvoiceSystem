package mx.unam.fi.tsic.billapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.unam.fi.tsic.billapi.entities.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
  
}
