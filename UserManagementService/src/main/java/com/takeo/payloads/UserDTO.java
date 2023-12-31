package com.takeo.payloads;

import com.takeo.entity.Address;
import com.takeo.entity.Roles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String fullName;
    private String email;
    private String phoneNum;
    private int age;
    private String gender;
    private List<Roles> role;
   private Address address;
}
