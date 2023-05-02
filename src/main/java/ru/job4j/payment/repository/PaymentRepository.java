package ru.job4j.payment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.payment.domain.Payment;

import java.util.Collection;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Integer> {
    Collection<Payment> findAll();
}
