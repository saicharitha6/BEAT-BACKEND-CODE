package org.accolite.pojo;

import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class OrganizationUpdateDetails {
    private long id;
    private String orgName;
    private String location;
    private String owner;
    @Nullable
    private long ownerEmpId;
    private long parentOrg;
}