package com.takeo.controller;

import com.takeo.entity.UserDetails;
import com.takeo.payloads.UserDTO;
import com.takeo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String,String>> registerUser(@RequestBody UserDTO userDTO){
       String register = userService.registerUser(userDTO);
        Map<String,String> response = new HashMap<>();
        response.put("message",register);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PostMapping("/verifyOtp")
    public ResponseEntity<Map<String,String>> verifyOtp(@RequestParam("otp") String otp){
        String verify = userService.verifyOtp(otp);
        Map<String,String> response = new HashMap<>();
        response.put("message",verify);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping("/getUser")
    public ResponseEntity<UserDetails> getUser(@RequestParam("id") int id){
      UserDetails user = userService.getUserDetails(id);
      return new ResponseEntity<>(user,HttpStatus.OK);
    }

}
