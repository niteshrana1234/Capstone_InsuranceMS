package com.takeo.utils;

import java.util.Random;

public class PolicyNumGenerator {
    public static  String generator(){
        Random random = new Random();
        int nineDigitNumber = random.nextInt(900000000) + 100000000;
        return Integer.toString(nineDigitNumber);
    }
}
