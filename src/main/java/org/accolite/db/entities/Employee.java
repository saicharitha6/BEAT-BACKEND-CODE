package org.accolite.db.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
@Data
@Entity
@Table(name="EMPLOYEE")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String location;
    private String gender;
    @CreationTimestamp
    private Date dateOfJoining;
    private String designation;
    private String category;
    private long projectId;
    private long leadId;
    private long organizationId;
    private long clientCounterpartId;
    private long band;
    @Nullable
    private Date dateOfLeaving;
}
