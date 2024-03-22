package com.picpaychallenge.module.transaction.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transactions")
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Long sender;

    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Long receiver;

    @Column(nullable = false)
    private BigDecimal value;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public Transaction(Long sender, Long receiver, BigDecimal value) {
        this.sender = sender;
        this.receiver = receiver;
        this.value = value;
        this.timestamp = LocalDateTime.now();
    }

}
