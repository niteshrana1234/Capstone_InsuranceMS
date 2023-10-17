package com.takeo.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePolicyDTO {
    private String policyType;
    private String coverage;
    private Date startDate;
    private int duration;

}
