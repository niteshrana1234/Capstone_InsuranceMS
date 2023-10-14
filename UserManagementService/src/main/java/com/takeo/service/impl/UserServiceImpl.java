package com.takeo.service.impl;

import com.takeo.entity.UserDetails;
import com.takeo.payloads.LoginDTO;
import com.takeo.payloads.UserDTO;
import com.takeo.repo.UserRepo;
import com.takeo.service.UserService;
import com.takeo.utils.EmailSender;
import com.takeo.utils.PasswordGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;
    @Override
    public String registerUser(UserDTO userDTO) {
        Optional<UserDetails> usr = userRepo.findByEmail(userDTO.getEmail());
        String message = "User already exist with email : "+userDTO.getEmail();
        if(usr.isEmpty()){
            String otp = EmailSender.sendOtp(userDTO.getEmail());
            if(otp!=null){
                message = "OTP sent to respected email !!";
                UserDetails user = new UserDetails();
                BeanUtils.copyProperties(userDTO,user);
                user.setOtp(otp);
                userRepo.save(user);
            }
        }
        return message;
    }
    @Override
    public String verifyOtp(String otp) {

        Optional<UserDetails> usr = userRepo.findByOtp(otp);
        String message = "Invalid otp";
        if(usr.isPresent()){
            UserDetails user = usr.get();
            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
            String password = PasswordGenerator.getRandomPassword();
            String encryptPass = bcrypt.encode(password);
            user.setPassword(encryptPass); //For authenticate user use bcrypt.matches(a,b)
            user.setOtp("");
           String sendPass = EmailSender.sendRandomPassword(user.getEmail(),password);
           if(sendPass!=null){
               message ="User registered successfully";
           }
           userRepo.save(user);
        }

        return message;
    }

    @Override
    public String loginUser(LoginDTO loginDTO) {
        


        return null;
    }

    @Override
    public UserDetails getUserDetails(int id) {
        String message = "User not found with given id";
        Optional<UserDetails> usr = userRepo.findById(id);
        if(usr.isPresent()){
            UserDetails user = usr.get();

            return user;
        }
        return null;
    }

    @Override
    public String updateUserDetails(int id) {
        return null;
    }
}
