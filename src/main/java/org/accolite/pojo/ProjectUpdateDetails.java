package org.accolite.pojo;

import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class ProjectUpdateDetails {
    private long id;
    private String name;
    private String organization;
    private String projectManager;
    private String location;
    @Nullable
    private String description;
    private String projectType;
}