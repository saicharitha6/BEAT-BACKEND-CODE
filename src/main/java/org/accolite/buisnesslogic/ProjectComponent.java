package org.accolite.buisnesslogic;

import lombok.extern.slf4j.Slf4j;
import org.accolite.db.entities.Employee;
import org.accolite.db.entities.Project;
import org.accolite.db.services.EmployeeService;
import org.accolite.db.services.ProjectService;
import org.accolite.pojo.EmployeeCard;
import org.accolite.pojo.ProjectCard;
import org.accolite.pojo.ProjectUpdateDetails;
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

    @Autowired
    EmployeeService employeeService;

    public boolean updateProject(ProjectUpdateDetails projectUpdateDetailsFromClient) {
        Optional<Project> projectFromDbObj = projectService.getProjectById(projectUpdateDetailsFromClient.getId());

        if (projectFromDbObj.isPresent()) {
            Project projectDetailsFromDb = projectFromDbObj.get();
            ProjectUpdateDetails projectUpdateDetailsFromDb = new ProjectUpdateDetails();

            projectUpdateDetailsFromDb = projectService.cloneToProjectUpdateDetails(projectUpdateDetailsFromDb, projectDetailsFromDb);
            if (projectUpdateDetailsFromClient.equals(projectUpdateDetailsFromDb)) {
                log.info("No changes detected in project with project ID: " + projectUpdateDetailsFromDb.getId());
                return false;
            }
            if (!projectDetailsFromDb.isStatus()) {
                log.info("Project with project ID: " + projectDetailsFromDb.getId() + " has already ended");
                return false;
            }

            projectDetailsFromDb = projectService.cloneToProject(projectDetailsFromDb, projectUpdateDetailsFromClient);

            projectService.saveUpdateProject(projectDetailsFromDb);

            return true;
        }
        else {
            log.info("Project with project ID: " + projectUpdateDetailsFromClient.getId() + " is not present");
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

    public List<EmployeeCard> getEmployeeCardsByProjectId(long id) {
        List<EmployeeCard> employeeCardList = new ArrayList<>();
        List<Employee> employeeList = this.employeeService.getEmployeesByProjectId(id);
        for (int i = 0; i < employeeList.size(); i++) {
            Employee curEmployee = employeeList.get(i);
            EmployeeCard curEmployeeCard = new EmployeeCard();
            employeeCardList.add(this.employeeService.cloneToEmployeeCard(curEmployeeCard, curEmployee));
        }
        return employeeCardList;
    }
}