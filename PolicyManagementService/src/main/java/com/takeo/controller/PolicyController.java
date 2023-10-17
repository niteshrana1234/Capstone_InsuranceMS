package com.takeo.controller;

import com.netflix.discovery.converters.Auto;
import com.takeo.entity.Policy;
import com.takeo.service.impl.PolicyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("policy")
public class PolicyController {

    @Autowired
    PolicyServiceImpl policyService;

    @PostMapping("/buy-policy/{id}")
    public ResponseEntity<Map<String,String>> buyPolicy(@PathVariable("id") int userId,@RequestBody Policy policy){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject();
        return null;
    }

}
