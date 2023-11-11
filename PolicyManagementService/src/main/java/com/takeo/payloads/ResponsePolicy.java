package com.takeo.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.util.Date;

@Data
public class ResponsePolicy {
    @JsonProperty("policy_id")
    private int id;
    private String policyNum;
    private String policyType;
    private String coverage;
    private Date startDate;
    private Date expireDate;
    private int userId;
    private long premium;
}
