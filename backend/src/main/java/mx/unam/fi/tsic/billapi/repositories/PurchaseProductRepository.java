package mx.unam.fi.tsic.billapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mx.unam.fi.tsic.billapi.entities.PurchaseProduct;
import mx.unam.fi.tsic.billapi.entities.id.PurchaseProductId;

@Repository
public interface PurchaseProductRepository extends JpaRepository<PurchaseProduct, PurchaseProductId> {

  @Query("SELECT p FROM PurchaseProduct p WHERE p.id.purchaseId = :purchaseId")
  List<PurchaseProduct> getPurchaseProductsById(Long purchaseId);
}
