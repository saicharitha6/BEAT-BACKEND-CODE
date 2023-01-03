package org.accolite.db.entities;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project project)) return false;
        return getId() == project.getId() && getEditorId() == project.getEditorId() && isStatus() == project.isStatus() && getName().equals(project.getName()) && getOrganization().equals(project.getOrganization()) && getProjectManager().equals(project.getProjectManager()) && getStartDate().equals(project.getStartDate()) && getLocation().equals(project.getLocation()) && getEndDate().equals(project.getEndDate()) && getDescription().equals(project.getDescription()) && getProjectType().equals(project.getProjectType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getOrganization(), getProjectManager(), getStartDate(), getLocation(), getEndDate(), getDescription(), getProjectType(), getEditorId(), isStatus());
    }
}
