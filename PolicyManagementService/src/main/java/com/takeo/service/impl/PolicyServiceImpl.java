package com.takeo.service.impl;

import com.takeo.entity.Policy;
import com.takeo.payloads.UpdatePolicyDTO;
import com.takeo.repo.PolicyRepo;
import com.takeo.service.PolicyService;
import com.takeo.utils.PremiumCalculator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class PolicyServiceImpl implements PolicyService {

    @Autowired
    PolicyRepo policyRepo;
    @Override
    public String createPolicy(Policy policy) {
        String message ="Error";
        if(policy!=null){
//            System.out.println("******************User Id : "+ policy.getUserId());
            policy.setPremium(PremiumCalculator.totalPremium(policy));
            Policy savedPolicy = policyRepo.save(policy);
            if(savedPolicy!=null){
                message = "Success!!";
            }
        }
        return message;
    }

    @Override
    public String updatePolicy(int userId, UpdatePolicyDTO updatePolicyDTO) {
        String message = "Policy not found with given id";
        Optional<Policy> policy1 = policyRepo.findByUserId(userId);
        if(policy1.isPresent()){
            message = "Update success";
            Policy policy = policy1.get();
            BeanUtils.copyProperties(updatePolicyDTO,policy);
            policyRepo.save(policy);
        }
        return message;
    }

    @Override
    public Policy getPolicy(int id) {
        Optional<Policy> optionalPolicy = policyRepo.findByUserId(id);
        if(optionalPolicy.isPresent()){
            return optionalPolicy.get();
        }
        return null;
    }

    @Override
    public List<Policy> getAllPolicy(int id) {
        return null;
    }
}
