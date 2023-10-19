package com.takeo.service;

import com.takeo.entity.Policy;
import com.takeo.payloads.PolicyDetails;
import com.takeo.payloads.UpdatePolicyDTO;

import java.util.List;

public interface PolicyService {
    public String createPolicy(int userId,Policy policy);
    public String updatePolicy(int userId,UpdatePolicyDTO updatePolicyDTO);
    public List<Policy> getPolicyByUserId(int userId);
    public Policy getPolicy(int policyId);
    public List<PolicyDetails> getAllPolicy();

}
