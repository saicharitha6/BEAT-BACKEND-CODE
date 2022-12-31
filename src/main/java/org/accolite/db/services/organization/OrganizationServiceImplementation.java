package org.accolite.db.services.organization;

import lombok.extern.slf4j.Slf4j;
import org.accolite.db.entities.Organization;
import org.accolite.db.repo.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OrganizationServiceImplementation implements OrganizationService{
    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public Organization createOrganization(Organization organization) {
        return organizationRepository.save(organization);
    }

    @Override
    public Organization getOrganizationObj(Organization organization) {
        Optional<Organization> organizationFromDbObj = this.organizationRepository.findById(organization.getId());
        return organizationFromDbObj.orElse(null);
    }

    @Override
    public List<Organization> getOrganization() { return this.organizationRepository.findAll(); }

    @Override
    public Organization disableOrganization(Organization organization) {
        Optional<Organization> orgObj = this.organizationRepository.findById(organization.getId());
        if (orgObj.isPresent()) {
            Organization organizationUpdate = orgObj.get();
            organizationUpdate.setStatus(false);

            organizationRepository.save(organizationUpdate);
            return organizationUpdate;
        }
        else {
            log.debug("Employee with employee ID: " + organization.getId() + "is not present");
            return organization;
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
}
