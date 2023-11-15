package com.takeo.controller;


import com.takeo.entity.Policy;
import com.takeo.payloads.PolicyDetails;
import com.takeo.payloads.ResponsePolicy;
import com.takeo.payloads.UpdatePolicyDTO;
import com.takeo.service.impl.PolicyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin/api/policy")
public class AdminPolicyController {

    @Autowired
    PolicyServiceImpl policyService;

    @PostMapping("/buy-policy/{id}")
    public ResponseEntity<Map<String,String>> createPolicy(@PathVariable("id") int userId,@RequestBody Policy policy){
       String savePolicy = policyService.createPolicy(userId,policy);
       Map<String,String> response = new HashMap<>();
       response.put("message",savePolicy);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PutMapping("/update-policy/{id}")
    public ResponseEntity<Map<String,String>> updatePolicy(@PathVariable("id") int userId,@RequestBody UpdatePolicyDTO updatePolicyDTO){
          String update =  policyService.updatePolicy(userId,updatePolicyDTO);
          Map<String,String> response = new HashMap<>();
          response.put("message",update);
          return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping("/getUserPolicy/{id}")
    public ResponseEntity<List<Policy>> userPolicies(@PathVariable("id") int userId){
       List<Policy> policies = policyService.getPolicyByUserId(userId);
      return new ResponseEntity<>(policies,HttpStatus.OK);
    }
    @GetMapping("getPolicy/{id}")
    public ResponseEntity<ResponsePolicy> getPolicyById(@PathVariable("id") int policyId){
    ResponsePolicy policy = policyService.getPolicy(policyId);
    return new ResponseEntity<>(policy,HttpStatus.OK);
    }

    @GetMapping("/getAllPolicy")
    public ResponseEntity<List<PolicyDetails>> getAllPolicy(){
          List<PolicyDetails>  listOfPolicy =  policyService.getAllPolicy();
          return new ResponseEntity<>(listOfPolicy,HttpStatus.OK);
    }

}
