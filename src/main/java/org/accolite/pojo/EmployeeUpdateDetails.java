package org.accolite.pojo;

import lombok.Data;

@Data
public class EmployeeUpdateDetails {
    private String location;
    private String designation;
    private String category;
    private long projectId;
    private long leadId;
    private long organizationId;
    private long clientCounterpartId;
    private long band;
}
