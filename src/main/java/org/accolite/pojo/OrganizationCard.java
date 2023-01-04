package org.accolite.pojo;

import lombok.Data;

@Data
public class OrganizationCard {
    private long id;
    private String orgName;
    private String location;
    private String owner;
}