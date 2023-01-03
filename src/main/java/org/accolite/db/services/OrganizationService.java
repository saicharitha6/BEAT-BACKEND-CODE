package org.accolite.db.services;

import org.accolite.db.entities.Organization;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrganizationService {
    Organization createOrganization(Organization organization);
    Optional<Organization> getOrganizationObj(long id);
    List<Organization> getOrganization();
    boolean disableOrganization(Organization organization);
    boolean saveUpdateOrganization(Organization organization);
    Organization compareDetails(Organization organizationUpdate, Organization organization);
}





