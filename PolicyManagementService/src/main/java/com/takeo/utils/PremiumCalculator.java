package com.takeo.utils;

import com.takeo.entity.Policy;
public class PremiumCalculator {

    public static int totalPremium(Policy policy) {

        String policyTYpe = policy.getPolicyType().toLowerCase();
        int duration = policy.getDuration();
        String coverage = policy.getCoverage().toLowerCase();
        int premium = 0;
        switch (policyTYpe) {
            case "home": //Dwelling, flood , liability
                if (coverage.equals("dwelling")) {
                    premium = duration * 200;
                } else if (coverage.equals("flood")) {
                    premium = duration * 250;
                } else if (coverage.equals("liability")) {
                    premium = duration * 300;
                }
                break;
            case "auto": // liability, full , medical
                if (coverage.equals("liability")) {
                    premium = duration * 150;
                } else if (coverage.equals("full")) {
                    premium = duration * 250;
                } else if (coverage.equals("medical")) {
                    premium = duration * 300;
                }
                break;
            case "health": // hmo, ppo, medicaid, medicare
                if (coverage.equals("hmo")) {
                    premium = duration * 185;
                } else if (coverage.equals("ppo")) {
                    premium = duration * 215;
                } else if (coverage.equals("medicaid")) {
                    premium = duration * 265;
                } else if (coverage.equals("medicare")) {
                    premium = duration * 100;
                }
                break;
        }
        return  premium;

    }

}
