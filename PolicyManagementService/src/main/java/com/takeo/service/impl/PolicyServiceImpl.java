package com.takeo.service.impl;

import com.takeo.entity.Policy;
import com.takeo.payloads.PolicyDetails;
import com.takeo.payloads.UpdatePolicyDTO;
import com.takeo.payloads.UserDTO.UserDetails;
import com.takeo.repo.PolicyRepo;
import com.takeo.service.PolicyService;
import com.takeo.utils.PremiumCalculator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class PolicyServiceImpl implements PolicyService {

    @Autowired
    PolicyRepo policyRepo;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public String createPolicy(int userId,Policy policy) {
        String message = "Error";
        UserDetails user = restTemplate.getForObject("http://10.0.0.206:1111/user/getUser?id=" + userId, UserDetails.class);
        if (user!=null && policy != null) {
            policy.setPremium(PremiumCalculator.totalPremium(policy));
            policy.setUserId(user.getId());
            Policy savedPolicy = policyRepo.save(policy);
            if (savedPolicy != null) {
                message = "Success!!";
            }
        }
        return message;
    }

    @Override
    public String updatePolicy(int userId, UpdatePolicyDTO updatePolicyDTO) {
        String message = "Policy not found with given id";
        UserDetails user = restTemplate.getForObject("http://10.0.0.206:1111/user/getUser?id=" + userId, UserDetails.class);
        if (user != null) {
            Optional<Policy> policy1 = policyRepo.findByUserId(user.getId());
            if (policy1.isPresent()) {
                message = "Update success";
                Policy policy = policy1.get();
                BeanUtils.copyProperties(updatePolicyDTO, policy);
                policy.setPremium(PremiumCalculator.totalPremium(policy));
                policyRepo.save(policy);
            }
        }

        return message;
    }

    @Override
    public List<Policy> getPolicy(int userId) {
        UserDetails user = restTemplate.getForObject("http://10.0.0.206:1111/user/getUser?id=" + userId,UserDetails.class);
        if(user!=null){
       List<Policy> policyList = policyRepo.findAll();
       List<Policy> userAllPolicy = new ArrayList<>();
            for(Policy policy: policyList){
                if(policy.getUserId() == user.getId()){
                    userAllPolicy.add(policy);
                }
            }
            return userAllPolicy;
        }

        return null;
    }

    @Override
    public List<PolicyDetails> getAllPolicy() {

        List<PolicyDetails> policyDetails = new ArrayList<>();
        Set<Integer> processedId = new HashSet<>();
        for(Policy policy : policyRepo.findAll()){
           int userId = policy.getUserId();
           UserDetails user = restTemplate.getForObject("http://10.0.0.206:1111/user/getUser?id=" + userId,UserDetails.class);
           if(!processedId.contains(userId) && user!=null){
               PolicyDetails details = new PolicyDetails();
               List<Policy> userPolicy = new ArrayList<>();
               details.setPolicyHolder(user.getFullName());
               processedId.add(user.getId());
               for(Policy policy1: policyRepo.findAll()){
                   if(policy1.getUserId() == user.getId()){
                       userPolicy.add(policy1);
                   }
               }
               details.setPolicies(userPolicy);
               policyDetails.add(details);
           }

        }

        return policyDetails;
    }
}
