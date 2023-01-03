package org.accolite.db.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.accolite.db.entities.Project;
import org.accolite.db.repo.ProjectRepository;
import org.accolite.db.services.ProjectService;
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
    public Optional<Project> getProjectObj(long id) {
        Optional<Project> projectFromDbObj = projectRepository.findById(id);
        return projectFromDbObj;
    }

    @Override
    public List<Project> getProject() {
        return this.projectRepository.findAll();
    }

    @Override
    public Project disableProject(Project project) {
        Optional<Project> prjObj = this.projectRepository.findById(project.getId());

        if (prjObj.isPresent()) {
            Project projectUpdate = prjObj.get();
            projectUpdate.setStatus(false);

            projectRepository.save(projectUpdate);
            return projectUpdate;
        }
        else {
            log.debug("Employee with employee ID: " + project.getId() + "is not present");
            return project;
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
    public Project compareDetails(Project projectUpdate, Project project) {
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
        projectUpdate.setStatus(project.isStatus());
        return projectUpdate;
    }

}
