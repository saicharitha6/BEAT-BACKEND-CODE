package org.accolite.db.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Objects;


@Entity
@Data
@Table(name="ORGANIZATION")
@EqualsAndHashCode
public class Organization {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private String orgName;

    private String location;

    private String owner;

    @Nullable
    private long ownerEmpId;

    private long parentOrg;

    private boolean status;

}


