package com.takeo.controller;

import com.takeo.entity.UserDetails;
import com.takeo.payloads.LoginDTO;
import com.takeo.payloads.UpdateUserDTO;
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
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody UserDTO userDTO) {
        String register = userService.registerUser(userDTO);
        Map<String, String> response = new HashMap<>();
        response.put("message", register);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/verifyOtp")
    public ResponseEntity<Map<String, String>> verifyOtp(@RequestParam("otp") String otp) {
        String verify = userService.verifyOtp(otp);
        Map<String, String> response = new HashMap<>();
        response.put("message", verify);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getUser")
    public ResponseEntity<UserDetails> getUser(@RequestParam("id") int id) {
        UserDetails user = userService.getUserDetails(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/updateUser")
    public ResponseEntity<Map<String, String>> updateUser(@RequestParam("id") int id, @RequestBody UpdateUserDTO userDTO) {
        String update = userService.updateUserDetails(id, userDTO);
        Map<String, String> response = new HashMap<>();
        response.put("message", update);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> userLogin(@RequestBody LoginDTO loginDTO){
       String login = userService.loginUser(loginDTO);
        Map<String,String> response = new HashMap<>();
        response.put("message",login);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
