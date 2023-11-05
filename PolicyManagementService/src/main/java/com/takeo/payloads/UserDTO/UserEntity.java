package com.takeo.payloads.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    private int id;
    private String fullName;
    private Address address;
    private String email;
    private String phoneNum;
    private List<Roles> role;
    private int age;
    private String gender;
    private String otp;
    private String password;
    private Date createdDate = new Date();
    private Date updatedDate = new Date();

}
