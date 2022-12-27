package org.accolite.db.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="ROLES")
public class Roles {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String accessName;
}



