package org.accolite.pojo;

import jakarta.annotation.Nullable;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ProjectViewDetails {
    private long id;
    private String name;
    private String organization;
    private String projectManager;
    private Date startDate;
    private String location;
    @Nullable
    private Date endDate;
    @Nullable
    private String description;
    private String projectType;
    private long editorId;
    private boolean status;

    private List<String> employeesInProject;
}
