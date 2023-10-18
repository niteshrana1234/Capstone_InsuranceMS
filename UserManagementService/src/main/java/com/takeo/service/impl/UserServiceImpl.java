package com.takeo.service.impl;

import com.takeo.entity.Address;
import com.takeo.entity.UserDetails;
import com.takeo.payloads.LoginDTO;
import com.takeo.payloads.Policy;
import com.takeo.payloads.UpdateUserDTO;
import com.takeo.payloads.UserDTO;
import com.takeo.repo.UserRepo;
import com.takeo.service.UserService;
import com.takeo.utils.EmailSender;
import com.takeo.utils.PasswordGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    RestTemplate restTemplate;
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
            if(user.getPassword()!=null){
                user.setPassword(encryptPass); //For authenticate user use bcrypt.matches(a,b)
                user.setOtp("");
                String sendPass = EmailSender.sendRandomPassword(user.getEmail(),password);
                if(sendPass!=null){
                    message ="User updated successfully \tNew Password sent to "+user.getEmail();
                }
            }
            else {
                user.setPassword(encryptPass); //For authenticate user use bcrypt.matches(a,b)
                user.setOtp("");
                String sendPass = EmailSender.sendRandomPassword(user.getEmail(),password);
                if(sendPass!=null){
                    message ="User registered successfully";
                }
            }

           userRepo.save(user);
        }

        return message;
    }

    @Override
    public String loginUser(LoginDTO loginDTO) {

    Optional<UserDetails> usr = userRepo.findByEmail(loginDTO.getEmail());
    String message = "No user found with email:  "+loginDTO.getEmail();
    if(usr.isPresent()){
        message = "Invalid username or password. Please try again";
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        UserDetails user = usr.get();
        if(bCrypt.matches(loginDTO.getPassword(),user.getPassword())){
            message = "User authenticated, Login successful";
        }
    }
        return message;
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
    public String updateUserDetails(int id, UpdateUserDTO userDTO) {
        Optional<UserDetails> usr = userRepo.findById(id);
        String message = "Failed to update user details";
        if(usr.isPresent()){
            message = "User updated successfully";
            UserDetails user = usr.get();
            if(!user.getFullName().equals(userDTO.getFullName())){
                user.setFullName(userDTO.getFullName());
            }
            if(userDTO.getAddress()!=null && user.getAddress()!=null){
                user.setAddress(userDTO.getAddress());
            }

            if(!user.getPhoneNum().equals(userDTO.getPhoneNum())){
                user.setPhoneNum(userDTO.getPhoneNum());
            }
            if(!user.getEmail().equals(userDTO.getEmail())){
               String otp = EmailSender.sendOtp(userDTO.getEmail()); // Assume verification code is send to new email
                if(otp!=null){
                    user.setEmail(userDTO.getEmail());
                    user.setOtp(otp);
                    message = "Otp sent to new email. Please verify OTP";
                }
            }
            user.setUpdatedDate(new Date());
            userRepo.save(user);
        }
        return message;
    }


}
