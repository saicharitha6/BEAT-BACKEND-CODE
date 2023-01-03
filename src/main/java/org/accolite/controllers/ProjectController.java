package org.accolite.controllers;

import org.accolite.buisnesslogic.ProjectComponent;
import org.accolite.db.entities.Project;
import org.accolite.db.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = PathConstants.projectPath)
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectComponent projectComponent;

    @PostMapping(value = PathConstants.createPath, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Project> createProject(@RequestBody Project project){
        return ResponseEntity.ok().body(this.projectService.createProject(project));
    }

    @PutMapping(value = PathConstants.updatePath, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    private ResponseEntity<String> updateProject(@RequestBody Project project){
        boolean prjUpdated = projectComponent.updateProject(project);
        if (prjUpdated) {
            return ResponseEntity.ok().body("Project Updated");
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = PathConstants.getPath, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<Project>> getProject(){
        return ResponseEntity.ok().body(this.projectService.getProject());
    }

    @PutMapping(value = PathConstants.disablePath, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Project> disableProject(@RequestBody Project project){
        return ResponseEntity.ok().body(this.projectService.disableProject(project));
    }
}
