package ru.job4j.payment.service;

import ru.job4j.payment.domain.Payment;

import java.util.Collection;
import java.util.Optional;

public interface PaymentService {
    Optional<Payment> findById(int id);

    Optional<Payment> save(Payment payment);

    Collection<Payment> findAll();

    boolean update(Payment payment);
}
