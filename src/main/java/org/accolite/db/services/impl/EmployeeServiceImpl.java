package org.accolite.db.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.accolite.db.entities.Employee;
import org.accolite.db.repo.EmployeeRepository;
import org.accolite.db.services.EmployeeService;
import org.accolite.db.services.OrganizationService;
import org.accolite.pojo.EmployeeCard;
import org.accolite.pojo.EmployeeUpdateDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private OrganizationService organizationService;

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> getEmployeeById(long id) {
        Optional<Employee> employeeFromDbObj = employeeRepository.findById(id);
        return employeeFromDbObj;
    }

    @Override
    public List<Employee> getEmployeesByName(String name) {
        List<Employee> employeeList = employeeRepository.findAllByName(name);
        return employeeList;
    }

    @Override
    public List<Employee> getEmployee() {
        return this.employeeRepository.findAll();
    }

    @Override
    public boolean disableEmployee(long id) {
        Optional<Employee> empObj = this.employeeRepository.findById(id);
        Date date = new Date();
        if (empObj.isPresent()) {
            Employee employeeUpdate = empObj.get();
            employeeUpdate.setDateOfLeaving(date);

            employeeRepository.save(employeeUpdate);
            return true;
        } else {
            log.info("Employee with employee ID: " + id + " is not present");
            return false;
        }
    }

    @Override
    public boolean saveUpdateEmployee(Employee employee) {
        Optional<Employee> employeeFromDbObj = employeeRepository.findById(employee.getId());
        if (employeeFromDbObj.isPresent()) {
            Employee previousEmployeeRecord = employeeFromDbObj.get();
            previousEmployeeRecord = employee;
            employeeRepository.save(previousEmployeeRecord);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public EmployeeUpdateDetails cloneDetails(EmployeeUpdateDetails employeeUpdateDetailsFromDb, Employee employeeDetailsFromDb) {

        employeeUpdateDetailsFromDb.setId(employeeDetailsFromDb.getId());
        employeeUpdateDetailsFromDb.setLocation(employeeDetailsFromDb.getLocation());
        employeeUpdateDetailsFromDb.setDesignation(employeeDetailsFromDb.getDesignation());
        employeeUpdateDetailsFromDb.setCategory(employeeDetailsFromDb.getCategory());
        employeeUpdateDetailsFromDb.setProjectId(employeeDetailsFromDb.getProjectId());
        employeeUpdateDetailsFromDb.setLeadId(employeeDetailsFromDb.getLeadId());
        employeeUpdateDetailsFromDb.setOrganizationId(employeeDetailsFromDb.getOrganizationId());
        employeeUpdateDetailsFromDb.setClientCounterpartId(employeeDetailsFromDb.getClientCounterpartId());
        employeeUpdateDetailsFromDb.setBand(employeeDetailsFromDb.getBand());

        return employeeUpdateDetailsFromDb;
    }

    @Override
    public Employee cloneToEmployee(Employee employeeDetailsFromDb, EmployeeUpdateDetails employeeUpdateDetailsFromClient) {

        employeeDetailsFromDb.setId(employeeUpdateDetailsFromClient.getId());
        employeeDetailsFromDb.setLocation(employeeUpdateDetailsFromClient.getLocation());
        employeeDetailsFromDb.setDesignation(employeeUpdateDetailsFromClient.getDesignation());
        employeeDetailsFromDb.setCategory(employeeUpdateDetailsFromClient.getCategory());
        employeeDetailsFromDb.setProjectId(employeeUpdateDetailsFromClient.getProjectId());
        employeeDetailsFromDb.setLeadId(employeeUpdateDetailsFromClient.getLeadId());
        employeeDetailsFromDb.setOrganizationId(employeeUpdateDetailsFromClient.getOrganizationId());
        employeeDetailsFromDb.setClientCounterpartId(employeeUpdateDetailsFromClient.getClientCounterpartId());
        employeeDetailsFromDb.setBand(employeeUpdateDetailsFromClient.getBand());

        return employeeDetailsFromDb;
    }
    @Override
    public EmployeeCard getLeadCard(long leadId) {
        Employee lead = new Employee();
        List<Employee> empList = getEmployee();
        for (int i = 0; i < empList.size(); i++) {
            if (empList.get(i).getId() == leadId) {
                lead = empList.get(i);
                break;
            }
        }
        EmployeeCard leadCard = new EmployeeCard();

        return cloneToEmployeeCard(leadCard, lead);
    }
    @Override
    public EmployeeCard cloneToEmployeeCard(EmployeeCard curEmployeeCard, Employee curEmployee) {
        curEmployeeCard.setId(curEmployee.getId());
        curEmployeeCard.setName(curEmployee.getName());
        curEmployeeCard.setDesignation(curEmployee.getDesignation());
        String orgName = organizationService.getOrganizationName(curEmployee.getOrganizationId());
        curEmployeeCard.setOrgName(orgName);
        return curEmployeeCard;
    }
}