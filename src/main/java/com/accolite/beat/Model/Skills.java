package com.accolite.beat.Model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

@Data
@Entity
public class Skills {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int Id;
   @NonNull
    private String Skill;
}
