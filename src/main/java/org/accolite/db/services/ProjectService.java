package org.accolite.db.services;

import org.accolite.db.entities.Project;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProjectService {
    Project createProject(Project project);
    Optional<Project> getProjectObj(long id);
    List<Project> getProject();
    Project disableProject(Project project);

    boolean saveUpdateProject(Project project);
    Project compareDetails(Project projectUpdate, Project project);
}
