package ru.job4j.payment.service;

import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.payment.domain.Payment;
import ru.job4j.payment.repository.PayMethodRepository;
import ru.job4j.payment.repository.PaymentRepository;
import ru.job4j.payment.repository.StatusRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimplePaymentService implements PaymentService {
    private final PaymentRepository payments;
    private final StatusRepository statuses;
    private final PayMethodRepository paymentMethods;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public Optional<Payment> findById(int id) {
        return payments.findById(id);
    }

    @Override
    public Optional<Payment> save(Payment payment) {
        payment.setMethod(paymentMethods.findById(payment.getMethod().getId()).get());
        payment.setStatus(statuses.findById(payment.getId()).get());
        var savedPayment = payments.save(payment);
        return Optional.of(savedPayment);
    }

    @Override
    public Collection<Payment> findAll() {
        return payments.findAll();
    }

    @KafkaListener(topics = "payment_service")
    public void receiveStatus(Map<String, Integer> data) {

    }
    @Override
    public boolean update(Payment payment) {
        if (payments.findById(payment.getId()).isEmpty()) {
            return false;
        }
        payments.save(payment);
        changeStatus(payment);
        return true;
    }
    @Transactional
    public void changeStatus(Payment payment) {
        Map<String, Integer> data = new HashMap();
        data.put("id", payment.getId());
        data.put("status", payment.getStatus().getId());
        kafkaTemplate.send("cooked_order", data);
    }
}
