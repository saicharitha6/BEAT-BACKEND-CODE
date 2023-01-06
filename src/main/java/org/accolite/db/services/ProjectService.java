package org.accolite.db.services;

import org.accolite.db.entities.Project;
import org.accolite.pojo.ProjectCard;
import org.accolite.pojo.ProjectUpdateDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProjectService {
    Project createProject(Project project);
    Optional<Project> getProjectById(long id);
    List<Project> getProjectsByName(String name);
    List<Project> getProject();
    boolean disableProject(long id);
    boolean saveUpdateProject(Project project);
    ProjectUpdateDetails cloneToProjectUpdateDetails(ProjectUpdateDetails projectUpdateDetailsFromDb, Project projectDetailsFromDb);
    Project cloneToProject(Project projectDetailsFromDb, ProjectUpdateDetails projectUpdateDetailsFromClient);
    ProjectCard cloneToProjectCard(ProjectCard curProjectCard, Project curProject);
    String getProjectName (long projectId);
}
