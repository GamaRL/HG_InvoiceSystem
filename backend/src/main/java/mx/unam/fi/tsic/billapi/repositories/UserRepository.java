package mx.unam.fi.tsic.billapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.unam.fi.tsic.billapi.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
  @Query("SELECT u FROM User u WHERE u.email = :email")
  Optional<User> getUserByUsername(@Param("email") String username);
}
