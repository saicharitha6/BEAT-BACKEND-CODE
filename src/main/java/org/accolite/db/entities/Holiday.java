package org.accolite.db.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "HOLIDAY")
public class Holiday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nameOfHoliday;
    private Date date;
    private String typeOfHoliday;
    private String location;
    private String description;
    private String status;
}
