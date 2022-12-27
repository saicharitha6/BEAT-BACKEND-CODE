package org.accolite.db.entities;


import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "SKILLS")
public class Skills {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String skill;
}
