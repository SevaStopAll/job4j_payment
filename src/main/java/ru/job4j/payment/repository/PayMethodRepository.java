package ru.job4j.payment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.payment.domain.PayMethod;

import java.util.Collection;

@Repository
public interface PayMethodRepository extends CrudRepository<PayMethod, Integer> {
    Collection<PayMethod> findAll();
}
