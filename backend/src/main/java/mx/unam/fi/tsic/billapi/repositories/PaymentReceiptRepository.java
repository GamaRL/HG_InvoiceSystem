package mx.unam.fi.tsic.billapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.unam.fi.tsic.billapi.entities.PaymentReceipt;

@Repository
public interface PaymentReceiptRepository extends JpaRepository<PaymentReceipt, Long> { }
