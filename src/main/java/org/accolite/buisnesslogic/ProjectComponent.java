package org.accolite.buisnesslogic;

import lombok.extern.slf4j.Slf4j;
import org.accolite.db.entities.Project;
import org.accolite.db.services.impl.EmployeeHistoryService;

import org.accolite.db.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class ProjectComponent {
    @Autowired
    ProjectService projectService;

    @Autowired
    EmployeeHistoryService employeeHistoryService;

    public boolean updateProject(Project project) {
        Optional<Project> projectFromDbObj = projectService.getProjectObj(project.getId());

        if (projectFromDbObj.isPresent()) {
            Project projectUpdate = projectFromDbObj.get();

            if (projectUpdate.equals(project)) {
                log.debug("No changes detected in project with project ID: " + project.getId());
                return false;
            }
            if (!projectUpdate.isStatus()) {
                log.debug("Project with project ID: " + project.getId() + " has already ended");
                return false;
            }

            projectUpdate = projectService.compareDetails(projectUpdate, project);

            projectService.saveUpdateProject(projectUpdate);

            return true;
        }
        else {
            log.debug("Project with project ID: " + project.getId() + " is not present");
            return false;
        }
    }

}
