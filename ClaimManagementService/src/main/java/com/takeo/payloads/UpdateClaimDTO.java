package com.takeo.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateClaimDTO {
    private String claimStatus;
    private double claimAmount;
    private int policyId;
}
