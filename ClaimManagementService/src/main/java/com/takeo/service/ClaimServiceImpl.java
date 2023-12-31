package com.takeo.service;

import com.takeo.entity.Claim;
import com.takeo.payloads.ClaimDTO;
import com.takeo.payloads.Policy;
import com.takeo.payloads.UpdateClaimDTO;
import com.takeo.payloads.UserClaims;
import com.takeo.payloads.UserDTO.UserEntity;
import com.takeo.repo.ClaimRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClaimServiceImpl implements ClaimService {
    @Autowired
    ClaimRepo claimRepo;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public String createClaim(ClaimDTO claimDTO) {
        String message = "Policy not found with given user id " + claimDTO.getPolicyId();
        Policy policy = restTemplate.getForObject("http://10.0.0.206:2222/policy/getPolicy/" + claimDTO.getPolicyId(), Policy.class);
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
    public String updateClaim(UpdateClaimDTO updateDTO) {
        String message ="Policy doesn't exist with id : "+updateDTO.getPolicyId();
        Policy policy = restTemplate.getForObject("http://10.0.0.206:2222/policy/getPolicy/" + updateDTO.getPolicyId(), Policy.class);
        if(policy!=null ){
            message ="Claim not found with given policy id : "+updateDTO.getPolicyId();
            Optional<Claim> optional = claimRepo.findByPolicyId(updateDTO.getPolicyId());
            if(optional.isPresent()) {
                Claim claim = optional.get();
                BeanUtils.copyProperties(updateDTO,claim);
                claim.setClaimUpdated(new Date());
                claimRepo.save(claim);
                message = "Update success";
            }
        }
        return message;
    }

    @Override
    public UserClaims getUserClaim(int userId) {
        UserEntity user = restTemplate.getForObject("http://10.0.0.206:1111/user/getUser?id="+userId, UserEntity.class);
        if(user!=null){
            List<Claim> claimList = claimRepo.findAll();
            List<Claim> claims = new ArrayList<>();
            UserClaims userClaims = new UserClaims();
            for(Claim claim : claimList){
                if(claim.getUserId() == user.getId()){
                    claims.add(claim);
                }
            }
            userClaims.setUserName(user.getFullName());
            userClaims.setUserClaims(claims);
            return  userClaims;
        }
        return null;
    }

    @Override
    public List<Claim> getAllClaim() {

        return claimRepo.findAll();
    }
}
