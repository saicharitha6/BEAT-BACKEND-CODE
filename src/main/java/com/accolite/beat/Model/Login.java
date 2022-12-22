package com.accolite.beat.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NonNull;

@Entity
@Data
public class Login {

    @Id @NonNull @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @NonNull
    private long empId;

    @NonNull
    private String name;

    @NonNull
    private String email;

    @NonNull
    private String password;

    @NonNull
    private long accessId;


}
