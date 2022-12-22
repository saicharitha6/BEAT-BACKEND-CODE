package com.accolite.beat.Model;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
@Data
@Entity
@Table(name="EmployeeHistory")
public class EmployeeHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private long empId;
    private String name;
    private long leadId;
    @Nullable
    private long clientCounterpartId;
    private long organizationId;
    private String organizationDepartment;
    private String accoliteDepartment;
    private Date fromDate;
    @Nullable
    private Date toDate;
    private long editorId;
    private long projectId;
    private long dateOfJoiningProject;
    private long dateOfLeavingProject;
}
