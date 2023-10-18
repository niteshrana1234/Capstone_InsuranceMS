package com.takeo.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.takeo.entity.Policy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicyDetails {
    private String policyHolder;
    private List<Policy> policies;

}
