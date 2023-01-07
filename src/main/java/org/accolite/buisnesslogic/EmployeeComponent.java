package org.accolite.buisnesslogic;

import lombok.extern.slf4j.Slf4j;
import org.accolite.db.entities.Employee;
import org.accolite.db.entities.EmployeeHistory;
import org.accolite.db.services.impl.EmployeeHistoryService;
import org.accolite.db.services.EmployeeService;
import org.accolite.pojo.EmployeeCard;
import org.accolite.pojo.EmployeeUpdateDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class EmployeeComponent {

    @Autowired
    EmployeeService employeeService;


    @Autowired
    EmployeeHistoryService employeeHistoryService;

    public boolean updateEmployee(EmployeeUpdateDetails employeeUpdateDetailsFromClient) {
        Optional<Employee> employeeFromDbObj = employeeService.getEmployeeById(employeeUpdateDetailsFromClient.getId());

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
            employeeDetailsFromDb = employeeService.cloneToEmployee(employeeDetailsFromDb, employeeUpdateDetailsFromClient);
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
            Date date = new Date();

            EmployeeHistory employeeHistoryUpdateObj = new EmployeeHistory();
            EmployeeHistory employeeHistoryDetailsFromDb = employeeHistoryUpdate.get();

            employeeHistoryUpdateObj = employeeHistoryService.cloneDetails(employeeHistoryUpdateObj, employeeHistoryDetailsFromDb);
            employeeHistoryUpdateObj = employeeHistoryService.cloneToEmployeeHistory(employeeHistoryUpdateObj, employeeUpdateDetailsFromClient);
            employeeHistoryDetailsFromDb.setStatus(false);
            employeeHistoryDetailsFromDb.setToDate(date);
            employeeHistoryUpdateObj.setFromDate(date);
            employeeHistoryService.createNewRecordInEmployeeHistory(employeeHistoryUpdateObj);
        }
       else {
            log.info("Employee with employee ID: " + employeeUpdateDetailsFromClient.getId() + " has no previous history");
        }
    }

    public List<EmployeeCard> getEmployeeHierarchy(long id) {
        List<EmployeeCard> hierarchyList = new ArrayList<>();
        Optional<Employee> employeeFromDbObj = employeeService.getEmployeeById(id);
        if (employeeFromDbObj.isPresent()) {
            Employee employeeFromDb = employeeFromDbObj.get();
            EmployeeCard leadCard = employeeService.getLeadCard(employeeFromDb.getLeadId());
            hierarchyList.add(leadCard);

            EmployeeCard employeeCard = new EmployeeCard();
            employeeCard = this.employeeService.cloneToEmployeeCard(employeeCard, employeeFromDb);

            hierarchyList.add(employeeCard);

            List<Employee> empList = this.employeeService.getEmployee();
            for (int i = 0; i < empList.size(); i++) {
                if (empList.get(i).getLeadId() == employeeFromDb.getId()) {
                    Employee curEmployee = empList.get(i);
                    EmployeeCard curEmployeeCard = new EmployeeCard();
                    hierarchyList.add(this.employeeService.cloneToEmployeeCard(curEmployeeCard, curEmployee));
                }
            }

            return hierarchyList;
        }
        else {
            log.info("Employee with employee ID:" + id + "is not present");
            return hierarchyList;
        }
    }

    public List<EmployeeCard> getEmployeeCardsByName(String name) {
        List<EmployeeCard> employeeCardList = new ArrayList<>();
        List<Employee> employeeList = this.employeeService.getEmployeesByName(name);
        for (int i = 0; i < employeeList.size(); i++) {
            Employee curEmployee = employeeList.get(i);
            EmployeeCard curEmployeeCard = new EmployeeCard();
            employeeCardList.add(this.employeeService.cloneToEmployeeCard(curEmployeeCard, curEmployee));
        }
        return employeeCardList;
    }

    public List<EmployeeHistory> getEmployeeHistoryById(long id) {
        List<EmployeeHistory> employeeHistoryList = this.employeeHistoryService.getEmployeeHistoryRecords(id);
        return employeeHistoryList;
    }

    public void createEmployeeHistoryForNewEmployee(Employee employee, long editorId) {
        EmployeeHistory newEmployeeHistory = new EmployeeHistory();
        newEmployeeHistory.setEmpId(employee.getId());
        newEmployeeHistory.setName(employee.getName());
        newEmployeeHistory.setLeadId(employee.getLeadId());
        newEmployeeHistory.setClientCounterpartId(employee.getClientCounterpartId());
        newEmployeeHistory.setOrganizationId(employee.getOrganizationId());
        newEmployeeHistory.setProjectId(employee.getProjectId());

        Date date = new Date();
        newEmployeeHistory.setFromDate(date);

        newEmployeeHistory.setEditorId(editorId);

        newEmployeeHistory.setStatus(true);
        employeeHistoryService.createNewRecordInEmployeeHistory(newEmployeeHistory);
    }

    public void createEmployeeHistoryForDisabledEmployee(long id, long editorId) {
        Optional<Employee> employeeObj = this.employeeService.getEmployeeById(id);
        if (employeeObj.isPresent()){
            Employee employee = employeeObj.get();



            EmployeeHistory newEmployeeHistory = new EmployeeHistory();
            newEmployeeHistory.setEmpId(employee.getId());
            newEmployeeHistory.setName(employee.getName());
            newEmployeeHistory.setLeadId(employee.getLeadId());
            newEmployeeHistory.setClientCounterpartId(employee.getClientCounterpartId());
            newEmployeeHistory.setOrganizationId(employee.getOrganizationId());
            newEmployeeHistory.setProjectId(employee.getProjectId());

            Date date = new Date();
            newEmployeeHistory.setFromDate(date);

            newEmployeeHistory.setEditorId(editorId);

            newEmployeeHistory.setStatus(true);

            Optional<EmployeeHistory> previousActiveHistoryObj = employeeHistoryService.getLastActiveRecordByEmpId(id);
            EmployeeHistory previousActiveHistory = previousActiveHistoryObj.get();
            previousActiveHistory.setStatus(false);
            previousActiveHistory.setToDate(date);

            employeeHistoryService.createNewRecordInEmployeeHistory(newEmployeeHistory);
        }
        else {
            log.info("This employee with ID: " + id + " is not present");
        }
    }
}