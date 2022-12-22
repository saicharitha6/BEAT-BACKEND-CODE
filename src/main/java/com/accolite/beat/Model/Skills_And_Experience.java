package com.accolite.beat.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
@Entity
public class Skills_And_Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int Id;
    @NonNull
    private int Emp_Id;
    @NonNull
    private String Skills;
    @NonNull
    private Date Exp_At;
    @NonNull
    private int Exp;
    @NonNull
    private boolean Status;
    @NonNull
    private Date Updated_On;
    @NonNull
    private int Editor_ID;
}
