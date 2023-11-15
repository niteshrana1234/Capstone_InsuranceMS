package com.takeo.controller;

import com.takeo.entity.Policy;
import com.takeo.payloads.PolicyDetails;
import com.takeo.payloads.UpdatePolicyDTO;
import com.takeo.service.impl.PolicyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user/api/policy")
public class UserPolicyController {
    @Autowired
    PolicyServiceImpl policyService;
    @GetMapping("/getUserPolicy/{id}")
    public ResponseEntity<List<Policy>> userPolicies(@PathVariable("id") int userId){
        List<Policy> policies = policyService.getPolicyByUserId(userId);
        return new ResponseEntity<>(policies,HttpStatus.OK);
    }
}
