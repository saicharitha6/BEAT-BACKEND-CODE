package com.accolite.beat.Model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name="ORGANIZATION")
public class Organization {

    @Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private long id;

    private String orgName;

    private String location;

    private long owner;

    private long parentOrg;

}


