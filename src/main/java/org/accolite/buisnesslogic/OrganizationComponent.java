package org.accolite.buisnesslogic;

import lombok.extern.slf4j.Slf4j;
import org.accolite.db.entities.Organization;
import org.accolite.db.services.impl.EmployeeHistoryService;
import org.accolite.db.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class OrganizationComponent {
    @Autowired
    OrganizationService organizationService;
    @Autowired
    EmployeeHistoryService employeeHistoryService;

    public boolean updateOrganization(Organization organization) {
        Optional<Organization> organizationFromDbObj = organizationService.getOrganizationObj(organization.getId());

        if (organizationFromDbObj.isPresent()) {
            Organization organizationUpdate = organizationFromDbObj.get();

            if (organizationUpdate.equals(organization)) {
                log.debug("No changes detected in organization with organization ID: " + organization.getId());
                return false;
            }
            if (!organizationUpdate.isStatus()) {
                log.debug("Organization with organization ID: " + organization.getId() + " is currently inactive");
                return false;
            }

            organizationUpdate = organizationService.compareDetails(organizationUpdate, organization);

            organizationService.saveUpdateOrganization(organizationUpdate);
            return true;
        }
        else {
            log.debug("Organization with organization ID: " + organization.getId() + " is not present");
            return false;
        }
    }

}
