package org.accolite.db.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "DAY_TYPE")
public class DayType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String dayType;
}
