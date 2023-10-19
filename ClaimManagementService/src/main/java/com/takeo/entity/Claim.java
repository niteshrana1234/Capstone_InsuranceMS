package com.takeo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int claimId;
    private String claimStatus;
    private double claimAmount;
    private int policyId;
    private int userId;
    @Temporal(TemporalType.DATE)
    private Date dateFilled = new Date();
}
