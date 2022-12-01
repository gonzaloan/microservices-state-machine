package cl.gonmunoz.microservicesstatemachine.repository;

import cl.gonmunoz.microservicesstatemachine.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
