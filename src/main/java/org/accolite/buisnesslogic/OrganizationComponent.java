package org.accolite.buisnesslogic;

import lombok.extern.slf4j.Slf4j;
import org.accolite.db.entities.Organization;
import org.accolite.db.entities.SlabCharges;
import org.accolite.db.services.OrganizationService;
import org.accolite.db.services.impl.ExpSlabsService;
import org.accolite.db.services.impl.SlabChargesService;
import org.accolite.pojo.OrganizationCard;
import org.accolite.pojo.OrganizationUpdateDetails;
import org.accolite.pojo.SlabDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class OrganizationComponent {
    @Autowired
    OrganizationService organizationService;

    @Autowired
    SlabChargesService slabChargesService;

    @Autowired
    ExpSlabsService expSlabsService;

    public boolean updateOrganization(OrganizationUpdateDetails organizationUpdateDetailsFromClient) {
        Optional<Organization> organizationFromDbObj = organizationService.getOrganizationById(organizationUpdateDetailsFromClient.getId());

        if (organizationFromDbObj.isPresent()) {
            Organization organizationDetailsFromDb = organizationFromDbObj.get();
            OrganizationUpdateDetails organizationUpdateDetailsFromDb = new OrganizationUpdateDetails();
            organizationUpdateDetailsFromDb = organizationService.cloneToOrganizationUpdateDetails(organizationUpdateDetailsFromDb, organizationDetailsFromDb);

            if (organizationUpdateDetailsFromClient.equals(organizationUpdateDetailsFromDb)) {
                log.info("No changes detected in organization with organization ID: " + organizationUpdateDetailsFromDb.getId());
                return false;
            }
            if (!organizationDetailsFromDb.isStatus()) {
                log.info("Organization with organization ID: " + organizationDetailsFromDb.getId() + " is currently inactive");
                return false;
            }
            organizationDetailsFromDb = organizationService.cloneToOrganization(organizationDetailsFromDb, organizationUpdateDetailsFromClient);

            organizationService.saveUpdateOrganization(organizationDetailsFromDb);
            return true;
        }
        else {
            log.info("Organization with organization ID: " + organizationUpdateDetailsFromClient.getId() + " is not present");
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
            return hierarchyList;
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

    public List<SlabDetails> getSlabDetailsList(long id) {
        List<SlabDetails> slabDetailsList = new ArrayList<>();

        List<SlabCharges> slabChargesList = this.slabChargesService.getSlabChargesListByOrgId(id);
        for (int i = 0; i < slabChargesList.size(); i++) {
            SlabCharges curSlabCharge = slabChargesList.get(i);
            long slabId = curSlabCharge.getSlabId();
            String slab = this.expSlabsService.getSlabById(slabId);
            long cost = curSlabCharge.getCost();
            SlabDetails curSlabDetails = new SlabDetails();
            curSlabDetails.setSlab(slab);
            curSlabDetails.setCost(cost);
            slabDetailsList.add(curSlabDetails);
        }
        return slabDetailsList;
    }
}