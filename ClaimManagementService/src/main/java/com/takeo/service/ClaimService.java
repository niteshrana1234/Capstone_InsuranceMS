package com.takeo.service;

import com.takeo.entity.Claim;
import com.takeo.payloads.ClaimDTO;
import com.takeo.payloads.updateClaimDTO;

import java.util.List;

public interface ClaimService {
    public String createClaim(ClaimDTO claimDTO);
    public String updateClaim(updateClaimDTO updateDTO);
    public Claim getUserClaim(int userId);
    public List<Claim> getAllClaim();
}
