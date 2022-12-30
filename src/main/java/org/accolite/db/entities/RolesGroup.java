package org.accolite.db.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="ROLE_GROUPS")
public class RolesGroup {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private String name;

    @Nullable
    private String access;
}


