package com.sanchi.model;


import com.sanchi.domain.USER_ROLE;
import jakarta.persistence.*;
import lombok.Data;
import jakarta.persistence.Id;
import com.fasterxml.jackson.annotation.JsonProperty;



@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String fullname;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Embedded
    private TwoFactAuth twoFactAuth = new TwoFactAuth();

    private USER_ROLE role = USER_ROLE.ROLE_CUSTOMER;



}

