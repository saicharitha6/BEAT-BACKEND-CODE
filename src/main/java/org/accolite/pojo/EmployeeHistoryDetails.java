package org.accolite.pojo;

import jakarta.annotation.Nullable;
import lombok.Data;

import java.util.Date;
@Data
public class EmployeeHistoryDetails {
    private long id;
    private long empId;
    private String name;
    private long leadId;
    @Nullable
    private long clientCounterpartId;
    private long organizationId;
    private Date fromDate;
    @Nullable
    private Date toDate;
    private long editorId;
    private long projectId;
    private long dateOfJoiningProject;
    private long dateOfLeavingProject;
    private boolean status;

    private String editorIdName;
    private String projectIdName;
    private String clientCounterpartIdName;
    private String organizationIdName;
    private String leadIdName;
}
