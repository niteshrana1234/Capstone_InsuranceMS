package com.takeo.controller;

import com.netflix.discovery.converters.Auto;
import com.takeo.entity.Policy;
import com.takeo.service.impl.PolicyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("policy")
public class PolicyController {

    @Autowired
    PolicyServiceImpl policyService;

    @PostMapping("/buy-policy")
    public ResponseEntity<Map<String,String>> createPolicy(@RequestBody Policy policy){
       String savePolicy = policyService.createPolicy(policy);
       Map<String,String> response = new HashMap<>();
       response.put("message",savePolicy);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
