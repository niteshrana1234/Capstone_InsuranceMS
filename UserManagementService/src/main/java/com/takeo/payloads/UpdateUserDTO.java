package com.takeo.payloads;

import com.takeo.entity.Address;
import com.takeo.entity.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDTO {
    private String fullName;
    private String email;
    private String phoneNum;
    private Address address;
}
