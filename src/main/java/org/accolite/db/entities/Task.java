package org.accolite.db.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "TASK")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String task;
}
