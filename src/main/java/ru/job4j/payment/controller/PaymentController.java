package ru.job4j.payment.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.payment.domain.Payment;
import ru.job4j.payment.domain.Status;
import ru.job4j.payment.service.PaymentService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService payments;

    @GetMapping("/")
    public List<Payment> findAll() {
        return (List<Payment>) payments.findAll();
    }

    @Transactional
    @PatchMapping("/patch/{id}")
    public ResponseEntity<Payment> patch(@RequestBody Status status, @PathVariable int id) {
        var payment =payments.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        payment.setStatus(status);
        var rsl = payments.update(payment);
        return new ResponseEntity<>(payment, rsl ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
