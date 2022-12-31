package org.accolite.buisnesslogic;

import lombok.extern.slf4j.Slf4j;
import org.accolite.db.entities.Employee;
import org.accolite.db.entities.EmployeeHistory;
import org.accolite.db.services.employee.EmployeeHistoryService;
import org.accolite.db.services.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
public class EmployeeComponent {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeHistoryService employeeHistoryService;

    public boolean updateEmployee(Employee employee) {
        Optional<Employee> employeeFromDbObj = Optional.ofNullable(employeeService.getEmployeeObj(employee));

        if (employeeFromDbObj.isPresent()) {
            Employee employeeUpdate = employeeFromDbObj.get();

            if (employeeUpdate.equals(employee)) {
                log.debug("No changes detected in employee with employee ID: " + employee.getId());
                return false;
            }
            if (employeeUpdate.getDateOfLeaving() != null) {
                log.debug("Employee with employee ID: " + employee.getId() + " has left the organization");
                return false;
            }


            updateEmployeeHistory(employee, employeeUpdate);

//            employeeUpdate.setId(employee.getId());
            employeeUpdate.setName(employee.getName());
            employeeUpdate.setLocation(employee.getLocation());
            employeeUpdate.setGender(employee.getGender());
//            employeeUpdate.setDateOfJoining(employee.getDateOfJoining());
            employeeUpdate.setDesignation(employee.getDesignation());
            employeeUpdate.setCategory(employee.getCategory());
            employeeUpdate.setProjectId(employee.getProjectId());
            employeeUpdate.setLeadId(employee.getLeadId());
            employeeUpdate.setOrganizationId(employee.getOrganizationId());
            employeeUpdate.setBand(employee.getBand());
//            employeeUpdate.setDateOfLeaving(employee.getDateOfLeaving());

            employeeService.saveUpdateEmployee(employeeUpdate);
            return true;
        }
        else {
            return false;
        }
    }

    public void updateEmployeeHistory(Employee employee, Employee employeeUpdate){
        EmployeeHistory employeeHistoryUpdate = employeeHistoryService.getLastActiveRecordByEmpId(employee.getId());

        EmployeeHistory employeeHistoryUpdateObj = new EmployeeHistory();

//        employeeHistoryUpdateObj.setEmpId(employeeHistoryUpdate.getEmpId());
        employeeHistoryUpdateObj.setName(employeeHistoryUpdate.getName());
        employeeHistoryUpdateObj.setLeadId(employeeHistoryUpdate.getLeadId());
        employeeHistoryUpdateObj.setClientCounterpartId(employeeHistoryUpdate.getClientCounterpartId());
        employeeHistoryUpdateObj.setOrganizationId(employeeHistoryUpdate.getOrganizationId());
        employeeHistoryUpdateObj.setFromDate(employeeHistoryUpdate.getFromDate());
        employeeHistoryUpdateObj.setToDate(employeeHistoryUpdate.getToDate());
        employeeHistoryUpdateObj.setEditorId(employeeHistoryUpdate.getEditorId());
        employeeHistoryUpdateObj.setProjectId(employeeHistoryUpdate.getProjectId());
        employeeHistoryUpdateObj.setDateOfJoiningProject(employeeHistoryUpdate.getDateOfJoiningProject());
        employeeHistoryUpdateObj.setDateOfLeavingProject(employeeHistoryUpdate.getDateOfLeavingProject());
        employeeHistoryUpdateObj.setStatus(employeeHistoryUpdate.getStatus());


        if (!Objects.equals(employee.getName(), employeeUpdate.getName())) {
            employeeHistoryUpdateObj.setName(employee.getName());
        }
        if (employee.getLeadId() != employeeUpdate.getLeadId()) {
            employeeHistoryUpdateObj.setLeadId(employee.getLeadId());
        }
        if (employee.getOrganizationId() != employeeUpdate.getOrganizationId()) {
            employeeHistoryUpdateObj.setOrganizationId(employee.getOrganizationId());
        }
        if (employee.getProjectId() != employeeUpdate.getProjectId()) {
            employeeHistoryUpdateObj.setProjectId(employee.getProjectId());
        }
        employeeHistoryService.createNewRecordInEmployeeHistory(employeeHistoryUpdateObj);
    }


}




