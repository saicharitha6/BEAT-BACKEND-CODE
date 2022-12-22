package com.accolite.beat.Model;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
@Data
@Entity
public class Employee_History {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Nonnull
    private long emp_id;
    @Nonnull
    private String name;
    @Nonnull
    private long lead_id;
    @Nullable
    private long client_counterpart_id;
    @Nonnull
    private long organization_id;
    @Nonnull
    private String organization_department;
    @Nonnull
    private String accolite_department;
    @Nonnull
    private Date from_date;
    @Nullable
    private Date to_date;
    @Nonnull
    private long editor_id;
    @Nonnull
    private long project_id;
    @Nonnull
    private long date_of_joining_project;
    @Nullable
    private long date_of_leaving_project;
}
