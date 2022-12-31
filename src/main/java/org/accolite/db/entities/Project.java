package org.accolite.db.entities;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
@Entity
@Data
@Table(name="PROJECT")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String organization;
    private String projectManager;
    @CreationTimestamp
    private Date startDate;
    private String location;
    @Nullable
    private Date endDate;
    @Nullable
    private String description;
    private String projectType;
    private long editorId;
    private boolean status;

    public boolean getStatus() {
        return status;
    }
}
