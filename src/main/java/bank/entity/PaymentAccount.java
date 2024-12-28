package bank.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(nullable = false)
    Long userId;

    @Column(nullable = false)
    String bankName;

    @Column(nullable = false)
    Integer currentAmount;
}