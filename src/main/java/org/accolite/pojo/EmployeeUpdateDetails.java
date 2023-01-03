package org.accolite.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Objects;

@Data
@EqualsAndHashCode
public class EmployeeUpdateDetails {
    private long id;
    private String location;
    private String designation;
    private String category;
    private long projectId;
    private long leadId;
    private long organizationId;
    private long clientCounterpartId;
    private long band;
}
