package com.takeo.payloads;

import com.takeo.entity.Claim;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserClaims {
    private String userName;
    private List<Claim> userClaims;
}
