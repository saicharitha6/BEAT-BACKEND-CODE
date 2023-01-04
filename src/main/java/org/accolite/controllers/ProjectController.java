package org.accolite.controllers;

import org.accolite.buisnesslogic.ProjectComponent;
import org.accolite.db.entities.Project;
import org.accolite.db.services.ProjectService;
import org.accolite.pojo.ProjectCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        if (prjUpdated) return ResponseEntity.ok().body("Project updated");
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping(value = PathConstants.getPath, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<Project>> getProject(){
        List<Project> prjList = this.projectService.getProject();
        if (prjList.size()==0) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        else return ResponseEntity.ok().body(this.projectService.getProject());
    }

    @PutMapping(value = PathConstants.disablePath, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<String> disableProject(@RequestBody Project project){
        boolean prjDisabled = this.projectService.disableProject(project);
        if (prjDisabled) return ResponseEntity.ok().body("Project disabled");
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping(value = PathConstants.getById, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Project> getProjectById(@PathVariable long id){
        Optional<Project> projectFromDbObj = projectService.getProjectById(id);
        if (projectFromDbObj.isPresent()) return ResponseEntity.ok().body(projectFromDbObj.get());
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping(value = PathConstants.search, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<ProjectCard>> searchProject(@PathVariable String name){
        List<ProjectCard> prjCardList = this.projectComponent.getProjectCardsByName(name);
        if(prjCardList.size() == 0) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        else return ResponseEntity.ok().body(prjCardList);
    }
}