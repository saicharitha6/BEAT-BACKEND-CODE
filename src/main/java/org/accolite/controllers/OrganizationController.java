package org.accolite.controllers;


import lombok.extern.slf4j.Slf4j;
import org.accolite.buisnesslogic.OrganizationComponent;
import org.accolite.db.entities.Organization;
import org.accolite.db.services.OrganizationService;
import org.accolite.pojo.OrganizationCard;
import org.accolite.pojo.OrganizationUpdateDetails;
import org.accolite.pojo.SlabDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
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
    private ResponseEntity<String> updateOrganization(@RequestBody OrganizationUpdateDetails organizationUpdateDetailsFromClient){
        boolean orgUpdated = organizationComponent.updateOrganization(organizationUpdateDetailsFromClient);
        if (orgUpdated) return ResponseEntity.ok().body("Organization updated");
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping(value = PathConstants.getPath, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<Organization>> getOrganization(){
        List<Organization> orgList = this.organizationService.getOrganization();
        if (orgList.size()==0) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        else return ResponseEntity.ok().body(this.organizationService.getOrganization());
    }

    @PutMapping(value = PathConstants.disablePath, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    private ResponseEntity<String> disableOrganization(@PathVariable long id){
        boolean orgDisabled = this.organizationService.disableOrganization(id);
        if (orgDisabled) return ResponseEntity.ok().body("Organization disabled");
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping(value = PathConstants.getByIdPath, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Organization> getOrganizationById(@PathVariable long id){
        Optional<Organization> organizationFromDbObj = organizationService.getOrganizationById(id);
        if (organizationFromDbObj.isPresent()) return ResponseEntity.ok().body(organizationFromDbObj.get());
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping(value = PathConstants.getHierarchyPath, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<OrganizationCard>> getOrganizationHierarchy(@PathVariable long id) {
        List<OrganizationCard> orgCardList = this.organizationComponent.getOrganizationHierarchy(id);
        if (orgCardList.size() == 0) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        else return ResponseEntity.ok().body(orgCardList);
    }

    @GetMapping(value = PathConstants.searchPath, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<OrganizationCard>> searchOrganization(@PathVariable String name){
        List<OrganizationCard> orgCardList = this.organizationComponent.getOrganizationCardsByName(name);
        if(orgCardList.size() == 0) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        else return ResponseEntity.ok().body(orgCardList);
    }

    @GetMapping(value = PathConstants.getSlabDetailsByIdPath, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<SlabDetails>> getSlabDetails(@PathVariable long id) {
        List<SlabDetails> slabDetailsList = this.organizationComponent.getSlabDetailsList(id);
        if (slabDetailsList.size() == 0) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        else return ResponseEntity.ok().body(slabDetailsList);
    }
}