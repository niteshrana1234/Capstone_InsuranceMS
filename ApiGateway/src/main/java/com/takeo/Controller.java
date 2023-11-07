package com.takeo;

import com.takeo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class Controller {
    @Autowired
    JwtUtil jwtUtil;

    @GetMapping("/validate")
    public String validate(@RequestParam("token") String token) {
        boolean check = jwtUtil.validateToken(token);
        if (check) {
            return "Positive";
        }
        return "negative";
    }
}
