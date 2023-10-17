package com.takeo.service;

import com.takeo.entity.Policy;
import com.takeo.payloads.UpdatePolicyDTO;

import java.util.List;

public interface PolicyService {
    public String createPolicy(int userId, Policy policy);
    public String updatePolicy(int userId,UpdatePolicyDTO updatePolicyDTO);
    public Policy getPolicy(int id);
    public List<Policy> getAllPolicy(int id);

}
