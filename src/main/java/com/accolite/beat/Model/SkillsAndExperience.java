package com.accolite.beat.Model;

import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;

@Data
@Entity
@Table(name = "SKILL_AND_EXPERIENCE")
public class SkillsAndExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int dd;

    private int empId;

    private String skills;

    private Date expAt;

    private int exp;

    private boolean status;

    private Date updatedOn;

    private int editorID;
}
