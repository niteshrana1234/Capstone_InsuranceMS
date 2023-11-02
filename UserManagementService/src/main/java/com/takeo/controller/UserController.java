package com.takeo.controller;

import com.takeo.entity.UserEntity;
import com.takeo.payloads.LoginDTO;
import com.takeo.payloads.UpdateUserDTO;
import com.takeo.payloads.UserDTO;
import com.takeo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    DaoAuthenticationProvider authenticationProvider;

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
    public ResponseEntity<UserEntity> getUser(@RequestParam("id") int id) {
        UserEntity user = userService.getUserDetails(id);
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
    public ResponseEntity<String> userLogin(@RequestBody LoginDTO loginDTO){
        try{
            UserDetails user = userService.loadUserByUsername(loginDTO.getEmail());
            Authentication authentication = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername()
                    ,loginDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new ResponseEntity<>("Login success",HttpStatus.ACCEPTED);
        } catch (AuthenticationException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
        }
    }

}
