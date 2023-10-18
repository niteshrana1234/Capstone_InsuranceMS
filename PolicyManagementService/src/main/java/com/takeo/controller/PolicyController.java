package com.takeo.controller;

import com.netflix.discovery.converters.Auto;
import com.takeo.entity.Policy;
import com.takeo.payloads.UpdatePolicyDTO;
import com.takeo.service.impl.PolicyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.parser.Entity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("policy")
public class PolicyController {

    @Autowired
    PolicyServiceImpl policyService;

    @PostMapping("/buy-policy/{id}")
    public ResponseEntity<Map<String,String>> createPolicy(@PathVariable("id") int userId,@RequestBody Policy policy){
       String savePolicy = policyService.createPolicy(userId,policy);
       Map<String,String> response = new HashMap<>();
       response.put("message",savePolicy);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PostMapping("/update-policy/{id}")
    public ResponseEntity<Map<String,String>> updatePolicy(@PathVariable("id") int userId,@RequestBody UpdatePolicyDTO updatePolicyDTO){
          String update =  policyService.updatePolicy(userId,updatePolicyDTO);
          Map<String,String> response = new HashMap<>();
          response.put("message",update);
          return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping("/getUserPolicy/{id}")
    public ResponseEntity<List<Policy>> userPolicies(@PathVariable("id") int userId){
       List<Policy> policies = policyService.getPolicy(userId);
      return new ResponseEntity<>(policies,HttpStatus.OK);
    }

}
