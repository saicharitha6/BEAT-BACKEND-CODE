package org.accolite.controllers;


import org.accolite.buisnesslogic.OrganizationComponent;
import org.accolite.db.entities.Organization;
import org.accolite.db.services.organization.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/beat/api/v1/organization")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private OrganizationComponent organizationComponent;

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    private ResponseEntity<Organization> createOrganization(@RequestBody Organization organization){
        return ResponseEntity.ok().body(this.organizationService.createOrganization(organization));
    }

    @PutMapping(value = "/update", consumes = "application/json", produces = "plain/text")
    private ResponseEntity<String> updateOrganization(@RequestBody Organization organization){
        boolean orgUpdated = organizationComponent.updateOrganization(organization);
        if (orgUpdated) {
            return ResponseEntity.ok().body("Organization Updated");
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/get", consumes = "application/json", produces = "application/json")
    private ResponseEntity<List<Organization>> getOrganization(){
        return ResponseEntity.ok().body(this.organizationService.getOrganization());
    }

    @PutMapping(value = "/disable", consumes = "application/json", produces = "application/json")
    private ResponseEntity<Organization> disableOrganization(@RequestBody Organization organization){
        return ResponseEntity.ok().body(this.organizationService.disableOrganization(organization));
    }
}
