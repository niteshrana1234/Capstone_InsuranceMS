package com.takeo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_details")
public class UserDetails {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String fullName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    @NotBlank
    @Email
    private String email;
    @NotEmpty
    private String phoneNum;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Roles role;
    private int age;
    private String gender;
    private String otp;
    private String password;
    @Temporal(TemporalType.DATE)
    @JsonIgnore
    @Column(updatable = false)
    private Date createdDate = new Date();
    @Temporal(TemporalType.DATE)
    @JsonIgnore
    @Column(insertable = false)
    private Date updatedDate = new Date();

}
