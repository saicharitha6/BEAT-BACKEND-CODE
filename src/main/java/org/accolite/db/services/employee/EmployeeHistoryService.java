package org.accolite.db.services.employee;

import lombok.extern.slf4j.Slf4j;
import org.accolite.db.entities.EmployeeHistory;
import org.accolite.db.repo.EmployeeHistoryRepository;
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

    public EmployeeHistory getLastActiveRecordByEmpId(long empId){
        Optional<EmployeeHistory> currentEmpHistoryObj = employeeHistoryRepository.findByEmpIdAndStatus(empId, true);
        return currentEmpHistoryObj.orElse(null);
    }

    public EmployeeHistory getLastActiveRecordByOrganizationId(long organizationId){
        Optional<EmployeeHistory> currentEmpHistoryObj = employeeHistoryRepository.findByOrganizationIdAndStatus(organizationId, true);
        return currentEmpHistoryObj.orElse(null);
    }
}
