package bank.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BankUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(nullable = false)
    String fullName;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    Date dateOfBirth;

    @Column(nullable = false)
    String workplace;

    Integer monthlyIncome;

    @Column(nullable = false)
    String bankUsed;

    Long creditAccountId;
    Long paymentAccountId;
    Integer creditRating;
}