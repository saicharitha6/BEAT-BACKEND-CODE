package org.accolite.db.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.accolite.db.entities.EmployeeHistory;
import org.accolite.db.repo.EmployeeHistoryRepository;
import org.accolite.db.services.EmployeeService;
import org.accolite.db.services.OrganizationService;
import org.accolite.db.services.ProjectService;
import org.accolite.pojo.EmployeeHistoryDetails;
import org.accolite.pojo.EmployeeUpdateDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.String.valueOf;

@Service
@Slf4j
public class EmployeeHistoryService {

    @Autowired
    EmployeeHistoryRepository employeeHistoryRepository;

    @Autowired
    ProjectService projectService;

    @Autowired
    ClientCounterpartService clientCounterpartService;

    @Autowired
    OrganizationService organizationService;

    @Autowired
    EmployeeService employeeService;

    public void createNewRecordInEmployeeHistory(EmployeeHistory employeeHistory){
        employeeHistoryRepository.save(employeeHistory);
    }

    public Optional<EmployeeHistory> getLastActiveRecordByEmpId(long empId){
        Optional<EmployeeHistory> currentEmpHistoryObj = employeeHistoryRepository.findByEmpIdAndStatus(empId, true);
        return currentEmpHistoryObj;
    }

    public EmployeeHistory cloneDetails(EmployeeHistory employeeHistoryUpdateObj, EmployeeHistory employeeHistoryDetailsFromDb) {

        employeeHistoryUpdateObj.setEmpId(employeeHistoryDetailsFromDb.getEmpId());
        employeeHistoryUpdateObj.setName(employeeHistoryDetailsFromDb.getName());
        employeeHistoryUpdateObj.setLeadId(employeeHistoryDetailsFromDb.getLeadId());
        employeeHistoryUpdateObj.setClientCounterpartId(employeeHistoryDetailsFromDb.getClientCounterpartId());
        employeeHistoryUpdateObj.setOrganizationId(employeeHistoryDetailsFromDb.getOrganizationId());
        employeeHistoryUpdateObj.setFromDate(employeeHistoryDetailsFromDb.getFromDate());
        employeeHistoryUpdateObj.setToDate(employeeHistoryDetailsFromDb.getToDate());
        employeeHistoryUpdateObj.setEditorId(employeeHistoryDetailsFromDb.getEditorId());
        employeeHistoryUpdateObj.setProjectId(employeeHistoryDetailsFromDb.getProjectId());
        employeeHistoryUpdateObj.setDateOfJoiningProject(employeeHistoryDetailsFromDb.getDateOfJoiningProject());
        employeeHistoryUpdateObj.setDateOfLeavingProject(employeeHistoryDetailsFromDb.getDateOfLeavingProject());
        employeeHistoryUpdateObj.setStatus(employeeHistoryDetailsFromDb.isStatus());

        return employeeHistoryUpdateObj;
    }

    public EmployeeHistory cloneToEmployeeHistory(EmployeeHistory employeeHistoryUpdateObj, EmployeeUpdateDetails employeeUpdateDetailsFromClient) {

        employeeHistoryUpdateObj.setEmpId(employeeUpdateDetailsFromClient.getId());
        employeeHistoryUpdateObj.setLeadId(employeeUpdateDetailsFromClient.getLeadId());
        employeeHistoryUpdateObj.setClientCounterpartId(employeeUpdateDetailsFromClient.getClientCounterpartId());
        employeeHistoryUpdateObj.setOrganizationId(employeeUpdateDetailsFromClient.getOrganizationId());
        employeeHistoryUpdateObj.setProjectId(employeeUpdateDetailsFromClient.getProjectId());

        return employeeHistoryUpdateObj;
    }

    public List<EmployeeHistoryDetails> getEmployeeHistoryRecords(long id) {
        List<EmployeeHistory> employeeHistoryList = employeeHistoryRepository.findAllByEmpId(id);
        List<EmployeeHistoryDetails> employeeHistoryDetailsList = new ArrayList<>();
        for (int i = 0; i < employeeHistoryList.size(); i++) {
            EmployeeHistory curEmpHis = employeeHistoryList.get(i);
            EmployeeHistoryDetails curEmpHisDet = new EmployeeHistoryDetails();
            employeeHistoryDetailsList.add(cloneToEmployeeHistoryDetails(curEmpHisDet, curEmpHis));
        }
        return employeeHistoryDetailsList;
    }

    public EmployeeHistoryDetails cloneToEmployeeHistoryDetails(EmployeeHistoryDetails curEmpHisDet, EmployeeHistory curEmpHis) {

        curEmpHisDet.setId(curEmpHis.getId());
        curEmpHisDet.setEmpId(curEmpHis.getEmpId());
        curEmpHisDet.setName(curEmpHis.getName());
        curEmpHisDet.setLeadId(curEmpHis.getLeadId());
        curEmpHisDet.setProjectId(curEmpHis.getProjectId());
        curEmpHisDet.setClientCounterpartId(curEmpHis.getClientCounterpartId());
        curEmpHisDet.setOrganizationId(curEmpHis.getOrganizationId());
        curEmpHisDet.setFromDate(curEmpHis.getFromDate());
        curEmpHisDet.setToDate(curEmpHis.getToDate());
        curEmpHisDet.setEditorId(curEmpHis.getEditorId());
        curEmpHisDet.setProjectId(curEmpHis.getProjectId());
        curEmpHisDet.setDateOfJoiningProject(curEmpHis.getDateOfJoiningProject());
        curEmpHisDet.setDateOfLeavingProject(curEmpHis.getDateOfLeavingProject());
        curEmpHisDet.setStatus(curEmpHis.isStatus());

        curEmpHisDet.setEditorIdName("");

        String projectName = this.projectService.getProjectName(curEmpHis.getProjectId());
        String projectId = valueOf(curEmpHis.getProjectId());
        String projectIdName = projectName + " " + projectId;
        curEmpHisDet.setProjectIdName(projectIdName);

        String clientCounterpartName = this.clientCounterpartService.getClientCounterpartName(curEmpHis.getClientCounterpartId());
        String clientCounterpartId = valueOf(curEmpHis.getClientCounterpartId());
        String clientCounterpartIdName = clientCounterpartName + " " + clientCounterpartId;
        curEmpHisDet.setClientCounterpartIdName(clientCounterpartIdName);

        String organizationName = this.organizationService.getOrganizationName(curEmpHis.getOrganizationId());
        String organizationId = valueOf(curEmpHis.getOrganizationId());
        String organizationIdName = organizationName + " " + organizationId;
        curEmpHisDet.setOrganizationIdName(organizationIdName);

        String leadName = this.employeeService.getLeadName(curEmpHis.getLeadId());
        String leadId = valueOf(curEmpHis.getLeadId());
        String leadIdName = leadName + " " + leadId;
        curEmpHisDet.setLeadIdName(leadIdName);

        return curEmpHisDet;
    }
}