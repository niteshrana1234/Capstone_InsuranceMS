package com.takeo.service;

import com.takeo.entity.UserDetails;
import com.takeo.payloads.LoginDTO;
import com.takeo.payloads.Policy;
import com.takeo.payloads.UpdateUserDTO;
import com.takeo.payloads.UserDTO;

public interface UserService {
    public  String registerUser(UserDTO userDTO);
    public String verifyOtp(String otp);
    public String loginUser(LoginDTO loginDTO);
    public UserDetails getUserDetails(int id);
    public String updateUserDetails(int id, UpdateUserDTO userDTO);

}
