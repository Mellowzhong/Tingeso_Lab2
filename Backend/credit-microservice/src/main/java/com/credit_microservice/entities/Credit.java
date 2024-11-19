package com.credit_microservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "credit")
public class Credit {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String creditType;

    private Integer requestedAmount;

    private Integer totalPriceHome;

    private Integer monthlyClientIncome;

    private String status;

    private Date applicationDate;

    private UUID userId;

//    @OneToMany(mappedBy = "credit", cascade = CascadeType.ALL)
//    private List<Document> documents = new ArrayList<>();
//
//    @OneToOne
//    private FinancialEvaluation financialEvaluation;
}