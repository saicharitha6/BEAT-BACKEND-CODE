package org.accolite.pojo;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class OrgView {
    private long id;
    private String orgName;
    private String location;
    private String owner;
    @Nullable
    private long ownerEmpId;
    private long parentOrg;
    private boolean status;
    private String parentOrgIdName;
}
