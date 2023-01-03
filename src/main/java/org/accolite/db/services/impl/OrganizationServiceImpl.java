package org.accolite.db.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.accolite.db.entities.Organization;
import org.accolite.db.repo.OrganizationRepository;
import org.accolite.db.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public Organization createOrganization(Organization organization) {
        return organizationRepository.save(organization);
    }

    @Override
    public Optional<Organization> getOrganizationObj(long id) {
        Optional<Organization> organizationFromDbObj = this.organizationRepository.findById(id);
        return organizationFromDbObj;
    }

    @Override
    public List<Organization> getOrganization() { return this.organizationRepository.findAll(); }

    @Override
    public boolean disableOrganization(Organization organization) {
        Optional<Organization> orgObj = this.organizationRepository.findById(organization.getId());
        if (orgObj.isPresent()) {
            Organization organizationUpdate = orgObj.get();
            organizationUpdate.setStatus(false);

            organizationRepository.save(organizationUpdate);
            return true;
        }
        else {
            log.debug("Employee with employee ID: " + organization.getId() + "is not present");
            return false;
        }
    }


    @Override
    public boolean saveUpdateOrganization(Organization organization) {
        Optional<Organization> organizationeFromDbObj = organizationRepository.findById(organization.getId());
        if (organizationeFromDbObj.isPresent()) {
            Organization previousOrganizationRecord = organizationeFromDbObj.get();
            previousOrganizationRecord = organization;
            organizationRepository.save(previousOrganizationRecord);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Organization compareDetails(Organization organizationUpdate, Organization organization) {

        organizationUpdate.setId(organization.getId());
        organizationUpdate.setOrgName(organization.getOrgName());
        organizationUpdate.setLocation(organization.getLocation());
        organizationUpdate.setOwner(organization.getOwner());
        organizationUpdate.setOwnerEmpId(organization.getOwnerEmpId());
        organizationUpdate.setParentOrg(organization.getParentOrg());
        organizationUpdate.setStatus(organization.isStatus());
        return organizationUpdate;
    }
}
