package org.accolite.db.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.accolite.db.entities.Organization;
import org.accolite.db.repo.OrganizationRepository;
import org.accolite.db.services.OrganizationService;
import org.accolite.pojo.OrgView;
import org.accolite.pojo.OrganizationCard;
import org.accolite.pojo.OrganizationUpdateDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.String.valueOf;

@Slf4j
@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public Organization createOrganization(Organization organization) {
        organization.setStatus(true);
        return organizationRepository.save(organization);
    }

    @Override
    public Optional<Organization> getOrganizationById(long id) {
        Optional<Organization> organizationFromDbObj = this.organizationRepository.findById(id);
        return organizationFromDbObj;
    }

    @Override
    public List<Organization> getOrganizationsByName(String name) {
        List<Organization> organizationList = organizationRepository.findAllByOrgName(name);
        return organizationList;
    }

    @Override
    public List<Organization> getOrganization() { return this.organizationRepository.findAll(); }

    @Override
    public boolean disableOrganization(long id) {
        Optional<Organization> orgObj = this.organizationRepository.findById(id);
        if (orgObj.isPresent()) {
            Organization organizationUpdate = orgObj.get();
            organizationUpdate.setStatus(false);

            organizationRepository.save(organizationUpdate);
            return true;
        }
        else {
            log.info("Employee with employee ID: " + id + "is not present");
            return false;
        }
    }

    @Override
    public boolean saveUpdateOrganization(Organization organization) {
        Optional<Organization> organizationFromDbObj = organizationRepository.findById(organization.getId());
        if (organizationFromDbObj.isPresent()) {
            Organization previousOrganizationRecord = organizationFromDbObj.get();
            previousOrganizationRecord = organization;
            organizationRepository.save(previousOrganizationRecord);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public OrganizationUpdateDetails cloneToOrganizationUpdateDetails(OrganizationUpdateDetails organizationUpdateDetailsFromDb, Organization organizationDetailsFromDb) {
        organizationUpdateDetailsFromDb.setId(organizationDetailsFromDb.getId());
        organizationUpdateDetailsFromDb.setOrgName(organizationDetailsFromDb.getOrgName());
        organizationUpdateDetailsFromDb.setLocation(organizationDetailsFromDb.getLocation());
        organizationUpdateDetailsFromDb.setOwner(organizationDetailsFromDb.getOwner());
        organizationUpdateDetailsFromDb.setOwnerEmpId(organizationDetailsFromDb.getOwnerEmpId());
        organizationUpdateDetailsFromDb.setParentOrg(organizationDetailsFromDb.getParentOrg());
        return organizationUpdateDetailsFromDb;
    }

    @Override
    public Organization cloneToOrganization(Organization organizationDetailsFromDb, OrganizationUpdateDetails organizationUpdateDetailsFromClient) {

        organizationDetailsFromDb.setId(organizationUpdateDetailsFromClient.getId());
        organizationDetailsFromDb.setOrgName(organizationUpdateDetailsFromClient.getOrgName());
        organizationDetailsFromDb.setLocation(organizationUpdateDetailsFromClient.getLocation());
        organizationDetailsFromDb.setOwner(organizationUpdateDetailsFromClient.getOwner());
        organizationDetailsFromDb.setOwnerEmpId(organizationUpdateDetailsFromClient.getOwnerEmpId());
        organizationDetailsFromDb.setParentOrg(organizationUpdateDetailsFromClient.getParentOrg());

        return organizationDetailsFromDb;
    }

    @Override
    public String getOrganizationName (long organizationId) {
        Optional<Organization> organizationFromDbObj = organizationRepository.findById(organizationId);
        if (organizationFromDbObj.isPresent()) {
            Organization organizationFromDb = organizationFromDbObj.get();
            return organizationFromDb.getOrgName();
        }
        else {
            log.info("This employee is not related to any organization");
            return null;
        }
    }

    @Override
    public OrganizationCard getParentCard(long parentOrg) {
        Organization parent = new Organization();
        List<Organization> orgList = getOrganization();
        for (int i = 0; i < orgList.size(); i++) {
            if (orgList.get(i).getId() == parentOrg) {
                parent = orgList.get(i);
                break;
            }
        }
        OrganizationCard parentCard = new OrganizationCard();
        parentCard.setId(parent.getId());
        parentCard.setOrgName(parent.getOrgName());
        parentCard.setLocation(parent.getLocation());
        parentCard.setOwner(parent.getOwner());
        return parentCard;
    }

    @Override
    public OrganizationCard cloneToOrganizationCard(OrganizationCard curOrganizationCard, Organization curOrganization) {
        curOrganizationCard.setId(curOrganization.getId());
        curOrganizationCard.setOrgName(curOrganization.getOrgName());
        curOrganizationCard.setLocation(curOrganization.getLocation());
        curOrganizationCard.setOwner(curOrganization.getOwner());
        return curOrganizationCard;
    }

    @Override
    public OrgView cloneToOrgView(OrgView curOrgView, Organization curOrganization) {
        curOrgView.setId(curOrganization.getId());
        curOrgView.setOrgName(curOrganization.getOrgName());
        curOrgView.setLocation(curOrganization.getLocation());
        curOrgView.setOwner(curOrganization.getOwner());
        curOrgView.setOwnerEmpId(curOrganization.getOwnerEmpId());
        curOrgView.setParentOrg(curOrganization.getParentOrg());
        curOrgView.setStatus(curOrganization.isStatus());

        String parentOrgName = getOrgName(curOrganization.getParentOrg());
        String parentOrgId = valueOf(curOrganization.getParentOrg());
        String parentOrgIdName = parentOrgName + " " + parentOrgId;
        curOrgView.setParentOrgIdName(parentOrgIdName);
        return curOrgView;
    }

    public String getOrgName(long id) {
        Optional<Organization> organizationObj = this.organizationRepository.findById(id);
        if (organizationObj.isPresent()) {
            Organization organization = organizationObj.get();
            return organization.getOrgName();
        }
        else {
            log.info("Parent organization not found");
            return "";
        }
    }
}