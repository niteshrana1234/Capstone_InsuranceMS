package com.takeo.controller;

import com.takeo.payloads.ClaimDTO;
import com.takeo.service.ClaimServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("claim")
public class ClaimController {
    @Autowired
    ClaimServiceImpl claimService;
    @PostMapping("/create-claim")
    public ResponseEntity<Map<String,String>> createClaim(@RequestBody ClaimDTO claimDTO){
       String create = claimService.createClaim(claimDTO);
       Map<String,String> response = new HashMap<>();
       response.put("message",create);
       return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
