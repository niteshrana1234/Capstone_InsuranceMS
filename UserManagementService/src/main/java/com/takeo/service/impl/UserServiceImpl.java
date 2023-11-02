package com.takeo.service.impl;

import com.takeo.entity.Roles;
import com.takeo.entity.UserEntity;
import com.takeo.payloads.LoginDTO;
import com.takeo.payloads.UpdateUserDTO;
import com.takeo.payloads.UserDTO;
import com.takeo.payloads.UserPrincipal;
import com.takeo.repo.RolesRepo;
import com.takeo.repo.UserRepo;
import com.takeo.service.UserService;
import com.takeo.utils.EmailSender;
import com.takeo.utils.PasswordGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    RolesRepo rolesRepo;
    @Autowired
    RestTemplate restTemplate;
//    @Autowired
//    AuthenticationManager authenticationManager;

    @Override
    public String registerUser(UserDTO userDTO) {
        Optional<UserEntity> usr = userRepo.findByEmail(userDTO.getEmail());
        String message = "User already exist with email : " + userDTO.getEmail();
        System.out.println("here");
        if (usr.isEmpty()) {
            String otp = EmailSender.sendOtp(userDTO.getEmail());
            if (otp != null) {
                List<Roles> rolesList = new ArrayList<>();
                message = "OTP sent to respected email !!";
                UserEntity user = new UserEntity();
                BeanUtils.copyProperties(userDTO, user);
                user.setRole(null);
                for (Roles role : userDTO.getRole()) {
                    Optional<Roles> optional = rolesRepo.findByRoleName(role.getRoleName().toUpperCase());
                    optional.ifPresent(rolesList::add);
                }
                user.setRole(rolesList);
                user.setOtp(otp);
                userRepo.save(user);
            }
        }
        return message;
    }

    @Override
    public String verifyOtp(String otp) {

        Optional<UserEntity> usr = userRepo.findByOtp(otp);
        String message = "Invalid otp";
        if (usr.isPresent()) {
            UserEntity user = usr.get();
            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
            String password = PasswordGenerator.getRandomPassword();
            String encryptPass = bcrypt.encode(password);
            if (user.getPassword() != null) {
                user.setPassword(encryptPass); //For authenticate user use bcrypt.matches(a,b)
                user.setOtp("");
                String sendPass = EmailSender.sendRandomPassword(user.getEmail(), password);
                if (sendPass != null) {
                    message = "User updated successfully \tNew Password sent to " + user.getEmail();
                }
            } else {
                user.setPassword(encryptPass); //For authenticate user use bcrypt.matches(a,b)
                user.setOtp("");
                String sendPass = EmailSender.sendRandomPassword(user.getEmail(), password);
                if (sendPass != null) {
                    message = "User registered successfully";
                }
            }

            userRepo.save(user);
        }

        return message;
    }

//    @Override
//    public String loginUser(LoginDTO loginDTO) {
//        try{
//            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail()
//                    , loginDTO.getPassword()));
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            return "Login Successful";
//        } catch (AuthenticationException e){
//            return e.getMessage();
//        }
//    }

    @Override
    public UserEntity getUserDetails(int id) {
        Optional<UserEntity> usr = userRepo.findById(id);
        if (usr.isPresent()) {
            return usr.get();
        }
        return null;
    }

    @Override
    public String updateUserDetails(int id, UpdateUserDTO userDTO) {
        Optional<UserEntity> usr = userRepo.findById(id);
        String message = "Failed to update user details";
        if (usr.isPresent()) {
            message = "User updated successfully";
            UserEntity user = usr.get();
            if (!user.getFullName().equals(userDTO.getFullName())) {
                user.setFullName(userDTO.getFullName());
            }
            if (userDTO.getAddress() != null && user.getAddress() != null) {
                user.setAddress(userDTO.getAddress());
            }

            if (!user.getPhoneNum().equals(userDTO.getPhoneNum())) {
                user.setPhoneNum(userDTO.getPhoneNum());
            }
            if (!user.getEmail().equals(userDTO.getEmail())) {
                String otp = EmailSender.sendOtp(userDTO.getEmail()); // Assume verification code is send to new email
                if (otp != null) {
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


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntity> optionalUser = userRepo.findByEmail(username);
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            return new UserPrincipal(user);
        }
        throw new UsernameNotFoundException("User doesn't exist with given email");
    }
}
