package org.accolite.controllers;


import org.accolite.buisnesslogic.OrganizationComponent;
import org.accolite.db.entities.Organization;
import org.accolite.db.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = PathConstants.organizationPath)
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private OrganizationComponent organizationComponent;

    @PostMapping(value = PathConstants.createPath, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Organization> createOrganization(@RequestBody Organization organization){
        return ResponseEntity.ok().body(this.organizationService.createOrganization(organization));
    }

    @PutMapping(value = PathConstants.updatePath, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    private ResponseEntity<String> updateOrganization(@RequestBody Organization organization){
        boolean orgUpdated = organizationComponent.updateOrganization(organization);
        if (orgUpdated) {
            return ResponseEntity.ok().body("Organization updated");
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = PathConstants.getPath, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<Organization>> getOrganization(){
        List<Organization> orgList = this.organizationService.getOrganization();
        if (orgList.size()==0) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        else return ResponseEntity.ok().body(this.organizationService.getOrganization());
    }

    @PutMapping(value = PathConstants.disablePath, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    private ResponseEntity<String> disableOrganization(@RequestBody Organization organization){
        boolean orgDisabled = this.organizationService.disableOrganization(organization);
        if (orgDisabled) {
            return ResponseEntity.ok().body("Organization disabled");
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
