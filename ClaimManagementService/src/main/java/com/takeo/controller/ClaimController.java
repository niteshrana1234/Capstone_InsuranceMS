package com.takeo.controller;

import com.takeo.entity.Claim;
import com.takeo.payloads.ClaimDTO;
import com.takeo.payloads.UpdateClaimDTO;
import com.takeo.payloads.UserClaims;
import com.takeo.service.ClaimServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
    @PostMapping("/update-claim")
    public ResponseEntity<Map<String,String>> updateClaim(@RequestBody UpdateClaimDTO updateClaimDTO){
       String updateClaim = claimService.updateClaim(updateClaimDTO);
        Map<String,String> response = new HashMap<>();
        response.put("message",updateClaim);
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping("/getUserClaims/{id}")
    public ResponseEntity<UserClaims> getUserClaims(@PathVariable("id") int userId){
        UserClaims userClaims = claimService.getUserClaim(userId);
        return new ResponseEntity<>(userClaims,HttpStatus.OK);
    }
    @GetMapping("/getAllClaims")
    public ResponseEntity<List<Claim>> getAllClaims(){
      List<Claim> claimList =  claimService.getAllClaim();
        return new ResponseEntity<>(claimList,HttpStatus.OK);
    }
}
