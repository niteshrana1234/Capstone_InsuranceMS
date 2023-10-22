package com.takeo.service;

import com.takeo.entity.Claim;
import com.takeo.payloads.ClaimDTO;
import com.takeo.payloads.UpdateClaimDTO;
import com.takeo.payloads.UserClaims;

import java.util.List;

public interface ClaimService {
    public String createClaim(ClaimDTO claimDTO);
    public String updateClaim(UpdateClaimDTO updateDTO);
    public UserClaims getUserClaim(int userId);
    public List<Claim> getAllClaim();
}
