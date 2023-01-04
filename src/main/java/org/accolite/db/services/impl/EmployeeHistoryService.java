package org.accolite.db.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.accolite.db.entities.EmployeeHistory;
import org.accolite.db.repo.EmployeeHistoryRepository;
import org.accolite.pojo.EmployeeUpdateDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class EmployeeHistoryService {

    @Autowired
    EmployeeHistoryRepository employeeHistoryRepository;

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
}