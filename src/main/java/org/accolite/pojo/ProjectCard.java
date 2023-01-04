package org.accolite.pojo;

import lombok.Data;

@Data
public class ProjectCard {
    private long id;
    private String name;
    private String organization;
    private String projectManager;
}