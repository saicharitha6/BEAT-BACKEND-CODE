package org.accolite.buisnesslogic;

import lombok.extern.slf4j.Slf4j;
import org.accolite.db.entities.Project;
import org.accolite.db.services.ProjectService;
import org.accolite.pojo.ProjectCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class ProjectComponent {
    @Autowired
    ProjectService projectService;

    public boolean updateProject(Project project) {
        Optional<Project> projectFromDbObj = projectService.getProjectById(project.getId());

        if (projectFromDbObj.isPresent()) {
            Project projectUpdate = projectFromDbObj.get();

            if (projectUpdate.equals(project)) {
                log.info("No changes detected in project with project ID: " + project.getId());
                return false;
            }
            if (!projectUpdate.isStatus()) {
                log.info("Project with project ID: " + project.getId() + " has already ended");
                return false;
            }

            projectUpdate = projectService.cloneDetails(projectUpdate, project);

            projectService.saveUpdateProject(projectUpdate);

            return true;
        }
        else {
            log.info("Project with project ID: " + project.getId() + " is not present");
            return false;
        }
    }

    public List<ProjectCard> getProjectCardsByName(String name) {
        List<ProjectCard> projectCardList = new ArrayList<>();
        List<Project> projectList = this.projectService.getProjectsByName(name);
        for (int i = 0; i < projectList.size(); i++) {
            Project curProject = projectList.get(i);
            ProjectCard curProjectCard = new ProjectCard();
            projectCardList.add(this.projectService.cloneToProjectCard(curProjectCard, curProject));
        }
        return projectCardList;
    }
}