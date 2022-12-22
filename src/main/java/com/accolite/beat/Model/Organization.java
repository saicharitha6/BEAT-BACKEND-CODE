package com.accolite.beat.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NonNull;


@Entity
@Data
public class Organization {

    @Id @NonNull @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @NonNull
    private String orgName;

    @NonNull
    private String location;

    @NonNull
    private long owner;

    @NonNull
    private long parentOrg;

}
