package com.takeo.service;

import com.takeo.entity.Claim;
import com.takeo.payloads.ClaimDTO;
import com.takeo.payloads.Policy;
import com.takeo.payloads.updateClaimDTO;
import com.takeo.repo.ClaimRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ClaimServiceImpl implements ClaimService {
    @Autowired
    ClaimRepo claimRepo;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public String createClaim(ClaimDTO claimDTO) {
        String message = "Policy not found with given user id " + claimDTO.getPolicyId();
        Policy policy = restTemplate.getForObject("http://laptop-jspnhksk.hsd1.tx.comcast.net:2222/policy/getPolicy/" + claimDTO.getPolicyId(), Policy.class);
        if (policy != null) {
            Claim claim = new Claim();
            BeanUtils.copyProperties(claimDTO, claim);
            claim.setPolicyId(claimDTO.getPolicyId());
            claim.setUserId(policy.getUserId());
            claim.setClaimStatus("filled");
            claimRepo.save(claim);
            message = "Claim created successfully";
        }
        return message;
    }

    @Override
    public String updateClaim(updateClaimDTO updateDTO) {
        return null;
    }

    @Override
    public Claim getUserClaim(int userId) {
        return null;
    }

    @Override
    public List<Claim> getAllClaim() {
        return null;
    }
}
