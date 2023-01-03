package org.accolite.buisnesslogic;

import lombok.extern.slf4j.Slf4j;
import org.accolite.db.entities.Employee;
import org.accolite.db.entities.EmployeeHistory;
import org.accolite.db.services.impl.EmployeeHistoryService;
import org.accolite.db.services.EmployeeService;
import org.accolite.pojo.EmployeeUpdateDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class EmployeeComponent {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeHistoryService employeeHistoryService;

    public boolean updateEmployee(EmployeeUpdateDetails employeeUpdateDetailsFromClient) {
        Optional<Employee> employeeFromDbObj = employeeService.getEmployeeObj(employeeUpdateDetailsFromClient.getId());

        if (employeeFromDbObj.isPresent()) {
            Employee employeeDetailsFromDb = employeeFromDbObj.get();
            EmployeeUpdateDetails employeeUpdateDetailsFromDb = new EmployeeUpdateDetails();
            employeeUpdateDetailsFromDb = employeeService.cloneDetails(employeeUpdateDetailsFromDb, employeeDetailsFromDb);

            if (employeeUpdateDetailsFromClient.equals(employeeUpdateDetailsFromDb)) {
                log.info("No changes detected in employee with employee ID: " + employeeUpdateDetailsFromDb.getId());
                return false;
            }
            if (employeeDetailsFromDb.getDateOfLeaving() != null) {
                log.info("Employee with employee ID: " + employeeDetailsFromDb.getId() + " has left the organization");
                return false;
            }

            updateEmployeeHistory(employeeUpdateDetailsFromClient);

            employeeService.saveUpdateEmployee(employeeDetailsFromDb);
            return true;
        }
        else {
            log.info("Employee with employee ID: " + employeeUpdateDetailsFromClient.getId() + " is not present");
            return false;
        }
    }

    public void updateEmployeeHistory(EmployeeUpdateDetails employeeUpdateDetailsFromClient){
        Optional<EmployeeHistory> employeeHistoryUpdate = employeeHistoryService.getLastActiveRecordByEmpId(employeeUpdateDetailsFromClient.getId());

        if (employeeHistoryUpdate.isPresent()) {
            EmployeeHistory employeeHistoryUpdateObj = new EmployeeHistory();
            EmployeeHistory employeeHistoryDetailsFromDb = employeeHistoryUpdate.get();

            employeeHistoryUpdateObj = employeeHistoryService.compareDetails(employeeHistoryUpdateObj, employeeHistoryDetailsFromDb);

            employeeHistoryService.createNewRecordInEmployeeHistory(employeeHistoryUpdateObj);
        }
       else {
            log.debug("Employee with employee ID: " + employeeUpdateDetailsFromClient.getId() + " has no previous history");
            return;
        }
    }
}
