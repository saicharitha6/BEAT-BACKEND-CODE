package org.accolite.buisnesslogic;

import lombok.extern.slf4j.Slf4j;
import org.accolite.db.entities.Project;
import org.accolite.db.services.employee.EmployeeHistoryService;

import org.accolite.db.services.project.ProjectService;
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
        Optional<Project> projectFromDbObj = Optional.ofNullable(projectService.getProjectObj(project));

        if (projectFromDbObj.isPresent()) {
            Project projectUpdate = projectFromDbObj.get();

            if (projectUpdate.equals(project)) {
                log.debug("No changes detected in project with project ID: " + project.getId());
                return false;
            }
            if (!projectUpdate.getStatus()) {
                log.debug("Project with project ID: " + project.getId() + " has already ended");
                return false;
            }

            projectUpdate.setId(project.getId());
            projectUpdate.setName(project.getName());
            projectUpdate.setOrganization(project.getOrganization());
            projectUpdate.setProjectManager(project.getProjectManager());
            projectUpdate.setStartDate(project.getStartDate());
            projectUpdate.setLocation(project.getLocation());
            projectUpdate.setEndDate(project.getEndDate());
            projectUpdate.setDescription(project.getDescription());
            projectUpdate.setProjectType(project.getProjectType());
            projectUpdate.setEditorId(project.getEditorId());
            projectUpdate.setStatus(project.getStatus());


            projectService.saveUpdateProject(projectUpdate);

            return true;

        }
        else {
            return false;
        }
    }

}
