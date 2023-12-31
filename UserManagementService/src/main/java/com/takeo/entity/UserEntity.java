package com.takeo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_details")
public class UserEntity {
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
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private List<Roles> role = new ArrayList<>();
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
