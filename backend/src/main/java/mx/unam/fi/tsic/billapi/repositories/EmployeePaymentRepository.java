package mx.unam.fi.tsic.billapi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mx.unam.fi.tsic.billapi.entities.Employee;
import mx.unam.fi.tsic.billapi.entities.EmployeePayment;

public interface EmployeePaymentRepository extends JpaRepository<EmployeePayment, Long>{

  @Query("SELECT p FROM EmployeePayment p WHERE p.employee = :employee ORDER BY p.createdAt ASC")
  List<EmployeePayment> getPaymentsByEmployeeId(Employee employee);

  @Query("SELECT p FROM EmployeePayment p WHERE p.employee = :employee AND p.id = :paymentId")
  Optional<EmployeePayment> findEmployeePaymentById(Employee employee, Long paymentId);
}
