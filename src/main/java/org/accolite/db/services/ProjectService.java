package org.accolite.db.services;

import org.accolite.db.entities.Project;
import org.accolite.pojo.ProjectCard;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProjectService {
    Project createProject(Project project);
    Optional<Project> getProjectById(long id);
    List<Project> getProjectsByName(String name);
    List<Project> getProject();
    boolean disableProject(Project project);
    boolean saveUpdateProject(Project project);
    Project cloneDetails(Project projectUpdate, Project project);
    ProjectCard cloneToProjectCard(ProjectCard curProjectCard, Project curProject);
}
