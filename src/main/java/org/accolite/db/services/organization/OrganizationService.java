package org.accolite.db.services.organization;

import org.accolite.db.entities.Organization;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrganizationService {
    Organization createOrganization(Organization organization);
    Organization getOrganizationObj(Organization organization);
    List<Organization> getOrganization();
    Organization disableOrganization(Organization organization);
    boolean saveUpdateOrganization(Organization organization);
}





