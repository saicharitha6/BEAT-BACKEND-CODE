package org.accolite.db.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "TIME_ENTRY")
public class TimeEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long entryId;
    private long empId;
    private long projectId;
    private long taskId;
    private Date date;
    private int time;
    private String type;

}
