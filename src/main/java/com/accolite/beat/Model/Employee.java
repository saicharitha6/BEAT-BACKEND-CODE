package com.accolite.beat.Model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import java.util.Date;
@Data
@Entity
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NonNull
    private long id;
    @NonNull
    private String name;
    @NonNull
    private String gender;
    @NonNull
    private Date date_of_joining;
    @NonNull
    private String designation;
    @NonNull
    private String category;
    @NonNull
    private long project_id;
    @NonNull
    private long lead_id;
    @NonNull
    private long organization_id;
    @NonNull
    private long band;
    @Nullable
    private Date date_of_leaving;
}
