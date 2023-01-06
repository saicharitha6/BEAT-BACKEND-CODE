package org.accolite.pojo;

import lombok.Data;

import java.util.Date;
@Data
public class ProfileDetails {
    private long id;
    private String name;
    private String location;
    private String gender;
    private Date dateOfJoining;
    private String designation;
    private String category;
    private Date dateOfLeaving;
    private String projectIdName;
    private String leadIdName;
    private String organizationIdName;
    private String clientCounterpartIdName;
    private String bandName;
    private String email;
}
