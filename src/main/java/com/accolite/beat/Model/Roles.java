package com.accolite.beat.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="ROLES")
public class Roles {
    @Id @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private long id;

    private String accessName;
}
