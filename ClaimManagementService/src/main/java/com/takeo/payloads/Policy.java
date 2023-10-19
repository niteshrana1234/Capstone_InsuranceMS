package com.takeo.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Policy {
    private int id;
    private String policyNum;
    private String policyType;
    private String coverage;
    private Date startDate;
    private Date expireDate;
    private int duration;
    private int userId;
    private long premium;
}
