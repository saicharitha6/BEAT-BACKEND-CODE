package org.accolite.db.services;

import org.accolite.db.entities.Organization;
import org.accolite.pojo.OrganizationCard;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrganizationService {
    Organization createOrganization(Organization organization);
    Optional<Organization> getOrganizationById(long id);
    List<Organization> getOrganization();
    boolean disableOrganization(Organization organization);
    boolean saveUpdateOrganization(Organization organization);
    Organization cloneDetails(Organization organizationUpdate, Organization organization);
    String getOrganizationName(long organizationId);
    OrganizationCard getParentCard(long parentOrg);
    OrganizationCard cloneToOrganizationCard(OrganizationCard curOrganizationCard, Organization curOrganization);
    List<Organization> getOrganizationsByName(String name);
}