package com.accolite.beat.Model;


import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "SKILLS")
public class Skills {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String skill;
}
