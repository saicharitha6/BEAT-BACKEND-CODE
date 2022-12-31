package org.accolite.buisnesslogic;

import lombok.extern.slf4j.Slf4j;
import org.accolite.db.entities.Organization;
import org.accolite.db.services.employee.EmployeeHistoryService;
import org.accolite.db.services.organization.OrganizationService;
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
        Optional<Organization> organizationFromDbObj = Optional.ofNullable(organizationService.getOrganizationObj(organization));

        if (organizationFromDbObj.isPresent()) {
            Organization organizationUpdate = organizationFromDbObj.get();

            if (organizationUpdate.equals(organization)) {
                log.debug("No changes detected in organization with organization ID: " + organization.getId());
                return false;
            }
            if (!organizationUpdate.getStatus()) {
                log.debug("Organization with organization ID: " + organization.getId() + " is currently inactive");
                return false;
            }

            organizationUpdate.setId(organization.getId());
            organizationUpdate.setOrgName(organization.getOrgName());
            organizationUpdate.setLocation(organization.getLocation());
            organizationUpdate.setOwner(organization.getOwner());
            organizationUpdate.setOwnerEmpId(organization.getOwnerEmpId());
            organizationUpdate.setParentOrg(organization.getParentOrg());
            organizationUpdate.setStatus(organization.getStatus());

            organizationService.saveUpdateOrganization(organizationUpdate);
            return true;
        }
        else {
            return false;
        }
    }

}
