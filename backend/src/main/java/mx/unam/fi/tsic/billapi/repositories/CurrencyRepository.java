package mx.unam.fi.tsic.billapi.repositories;

import org.springframework.data.repository.CrudRepository;

import mx.unam.fi.tsic.billapi.entities.Currency;

public interface CurrencyRepository extends CrudRepository<Currency, String> {
  
}
