package com.takeo.payloads.UserDTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private int id;
    private int buildingNum;
    private String street;
    private int zipCode;
    private String city;
    private String state;
    private String country;
}
