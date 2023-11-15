package com.takeo.controller;

import com.takeo.payloads.UpdateUserDTO;
import com.takeo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("admin/api/mgmt")
public class AdminController {
    @Autowired
    UserServiceImpl userService;

    @PutMapping("/updateUser")
    public ResponseEntity<Map<String, String>> updateUser(@RequestParam("id") int id, @RequestBody UpdateUserDTO userDTO) {
        String update = userService.updateUserDetails(id, userDTO);
        Map<String, String> response = new HashMap<>();
        response.put("message", update);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
