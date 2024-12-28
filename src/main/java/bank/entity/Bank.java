package bank.entity;


import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(nullable = false)
    String name;
    Integer numberOffices;
    Integer numberAtms;
    Integer numberEmployees;
    Integer numberUsers;
    Integer bankRating;
    Integer totalMoney;
    Float interestRate;
}