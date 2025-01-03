package mx.unam.fi.tsic.billapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.unam.fi.tsic.billapi.entities.TaxRegime;

@Repository
public interface TaxRegimeRepository extends CrudRepository<TaxRegime, String>{
  @Query("SELECT tr FROM TaxRegime tr WHERE tr.code = :code")
  Optional<TaxRegime> getByCode(@Param("code") String code);
}
