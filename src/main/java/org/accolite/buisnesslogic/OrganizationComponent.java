package org.accolite.buisnesslogic;

import lombok.extern.slf4j.Slf4j;
import org.accolite.db.entities.Organization;
import org.accolite.db.services.OrganizationService;
import org.accolite.pojo.OrganizationCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class OrganizationComponent {
    @Autowired
    OrganizationService organizationService;

    public boolean updateOrganization(Organization organization) {
        Optional<Organization> organizationFromDbObj = organizationService.getOrganizationById(organization.getId());

        if (organizationFromDbObj.isPresent()) {
            Organization organizationUpdate = organizationFromDbObj.get();

            if (organizationUpdate.equals(organization)) {
                log.info("No changes detected in organization with organization ID: " + organization.getId());
                return false;
            }
            if (!organizationUpdate.isStatus()) {
                log.info("Organization with organization ID: " + organization.getId() + " is currently inactive");
                return false;
            }

            organizationUpdate = organizationService.cloneDetails(organizationUpdate, organization);

            organizationService.saveUpdateOrganization(organizationUpdate);
            return true;
        }
        else {
            log.info("Organization with organization ID: " + organization.getId() + " is not present");
            return false;
        }
    }

    public List<OrganizationCard> getOrganizationHierarchy(long id) {
        List<OrganizationCard> hierarchyList = new ArrayList<>();
        Optional<Organization> organizationFromDbObj = organizationService.getOrganizationById(id);
        if (organizationFromDbObj.isPresent()) {
            Organization organizationFromDb = organizationFromDbObj.get();
            OrganizationCard parentCard = organizationService.getParentCard(organizationFromDb.getParentOrg());
            hierarchyList.add(parentCard);

            OrganizationCard organizationCard = new OrganizationCard();
            organizationCard = this.organizationService.cloneToOrganizationCard(organizationCard, organizationFromDb);

            hierarchyList.add(organizationCard);

            List<Organization> orgList = this.organizationService.getOrganization();
            for (int i = 0; i < orgList.size(); i++) {
                if (orgList.get(i).getParentOrg() == organizationFromDb.getId()) {
                    Organization curOrganization = orgList.get(i);
                    OrganizationCard curOrganizationCard = new OrganizationCard();
                    hierarchyList.add(this.organizationService.cloneToOrganizationCard(curOrganizationCard, curOrganization));
                }
            }

            return hierarchyList;
        }
        else {
            log.info("Organization with organization ID:" + id + "is not present");
            return Collections.EMPTY_LIST;
        }
    }

    public List<OrganizationCard> getOrganizationCardsByName(String name) {
        List<OrganizationCard> organizationCardList = new ArrayList<>();
        List<Organization> organizationList = this.organizationService.getOrganizationsByName(name);
        for (int i = 0; i < organizationList.size(); i++) {
            Organization curOrganization = organizationList.get(i);
            OrganizationCard curOrganizationCard = new OrganizationCard();
            organizationCardList.add(this.organizationService.cloneToOrganizationCard(curOrganizationCard, curOrganization));
        }
        return organizationCardList;
    }
}