package com.takeo.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Policy {

    private String policyNum;
    private String policyType;
    private String coverage;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @JsonIgnore
    @Temporal(TemporalType.DATE)
    private Date expireDate;
    private int duration;
    private int userId;
    @JsonIgnore
    private long premium;
}
