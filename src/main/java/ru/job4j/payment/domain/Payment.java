package ru.job4j.payment.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pay_method")
    @Setter
    @Getter
    private PayMethod method;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status")
    @Setter
    @Getter
    private Status status;
}
