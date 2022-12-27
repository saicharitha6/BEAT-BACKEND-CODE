package org.accolite.db.entities;

import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;

@Data
@Entity
@Table(name = "SKILL_AND_EXPERIENCE")
public class SkillsAndExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dd;

    private long empId;

    private String skills;

    private Date expAt;

    private int exp;

    private boolean status;

    private Date updatedOn;

    private long editorID;
}
