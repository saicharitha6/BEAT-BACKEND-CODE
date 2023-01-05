package org.accolite.db.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.accolite.db.entities.Project;
import org.accolite.db.repo.ProjectRepository;
import org.accolite.db.services.ProjectService;
import org.accolite.pojo.ProjectCard;
import org.accolite.pojo.ProjectUpdateDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Optional<Project> getProjectById(long id) {
        Optional<Project> projectFromDbObj = projectRepository.findById(id);
        return projectFromDbObj;
    }

    @Override
    public List<Project> getProjectsByName(String name) {
        List<Project> projectList = projectRepository.findAllByName(name);
        return projectList;
    }

    @Override
    public List<Project> getProject() {
        return this.projectRepository.findAll();
    }

    @Override
    public boolean disableProject(long id) {
        Optional<Project> prjObj = this.projectRepository.findById(id);

        if (prjObj.isPresent()) {
            Project projectUpdate = prjObj.get();
            projectUpdate.setStatus(false);

            projectRepository.save(projectUpdate);
            return true;
        }
        else {
            log.info("Employee with employee ID: " + id + " is not present");
            return false;
        }
    }

    @Override
    public boolean saveUpdateProject(Project project) {
        Optional<Project> projectFromDbObj = projectRepository.findById(project.getId());
        if (projectFromDbObj.isPresent()) {
            Project previousProjectRecord = projectFromDbObj.get();
            previousProjectRecord = project;
            projectRepository.save(previousProjectRecord);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public ProjectUpdateDetails cloneToProjectUpdateDetails(ProjectUpdateDetails projectUpdateDetailsFromDb, Project projectDetailsFromDb) {
        projectUpdateDetailsFromDb.setId(projectDetailsFromDb.getId());
        projectUpdateDetailsFromDb.setName(projectDetailsFromDb.getName());
        projectUpdateDetailsFromDb.setOrganization(projectDetailsFromDb.getOrganization());
        projectUpdateDetailsFromDb.setProjectManager(projectDetailsFromDb.getProjectManager());
        projectUpdateDetailsFromDb.setLocation(projectDetailsFromDb.getLocation());
        projectUpdateDetailsFromDb.setDescription(projectDetailsFromDb.getDescription());
        projectUpdateDetailsFromDb.setProjectType(projectDetailsFromDb.getProjectType());

        return projectUpdateDetailsFromDb;
    }

    @Override
    public Project cloneToProject(Project projectDetailsFromDb, ProjectUpdateDetails projectUpdateDetailsFromClient) {

        projectDetailsFromDb.setId(projectUpdateDetailsFromClient.getId());
        projectDetailsFromDb.setName(projectUpdateDetailsFromClient.getName());
        projectDetailsFromDb.setOrganization(projectUpdateDetailsFromClient.getOrganization());
        projectDetailsFromDb.setProjectManager(projectUpdateDetailsFromClient.getProjectManager());
        projectDetailsFromDb.setLocation(projectUpdateDetailsFromClient.getLocation());
        projectDetailsFromDb.setDescription(projectUpdateDetailsFromClient.getDescription());
        projectDetailsFromDb.setProjectType(projectUpdateDetailsFromClient.getProjectType());

        return projectDetailsFromDb;
    }

    @Override
    public ProjectCard cloneToProjectCard(ProjectCard curProjectCard, Project curProject) {
        curProjectCard.setId(curProject.getId());
        curProjectCard.setName(curProject.getName());
        curProjectCard.setOrganization(curProject.getOrganization());
        curProjectCard.setProjectManager(curProject.getProjectManager());
        return curProjectCard;
    }
}