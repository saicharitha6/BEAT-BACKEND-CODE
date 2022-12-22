package com.accolite.beat.Model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="ROLES_GROUP")
public class RolesGroup {
    @Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private long id;

    private String name;

    @Nullable
    private String access;
}
