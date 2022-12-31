package org.accolite.controllers;

import org.accolite.buisnesslogic.ProjectComponent;
import org.accolite.db.entities.Project;
import org.accolite.db.services.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/beat/api/v1/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectComponent projectComponent;

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    private ResponseEntity<Project> createProject(@RequestBody Project project){
        return ResponseEntity.ok().body(this.projectService.createProject(project));
    }

    @PutMapping(value = "/update", consumes = "application/json", produces = "plain/text")
    private ResponseEntity<String> updateProject(@RequestBody Project project){
        boolean prjUpdated = projectComponent.updateProject(project);
        if (prjUpdated) {
            return ResponseEntity.ok().body("Project Updated");
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/get", consumes = "application/json", produces = "application/json")
    private ResponseEntity<List<Project>> getProject(){
        return ResponseEntity.ok().body(this.projectService.getProject());
    }

    @PutMapping(value = "/disable", consumes = "application/json", produces = "application/json")
    private ResponseEntity<Project> disableProject(@RequestBody Project project){
        return ResponseEntity.ok().body(this.projectService.disableProject(project));
    }
}
