package org.accolite.db.services.project;

import org.accolite.db.entities.Project;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {
    Project createProject(Project project);
    Project getProjectObj(Project project);
    List<Project> getProject();
    Project disableProject(Project project);

    boolean saveUpdateProject(Project project);
}
