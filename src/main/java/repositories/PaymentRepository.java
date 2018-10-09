package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import models.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
	Payment findByPaymentnumber(Long payment_number);
}
