package com.takeo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;
    private String policyNUm;
    private String policyType;
    private String coverage;
    @Column(updatable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @JsonIgnore
    private Date expireDate;
    private int duration;
    @JsonIgnore
    private int userId;
    @JsonIgnore
    private long premium;

    @PrePersist
    public void calculateExpireDate() {
        if (startDate != null && duration > 0) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            calendar.add(Calendar.MONTH, duration); // Add duration months to start date
            expireDate = calendar.getTime();
        }
    }
}
