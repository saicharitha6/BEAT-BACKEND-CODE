package org.accolite.db.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.accolite.db.entities.Employee;
import org.accolite.db.repo.EmployeeRepository;
import org.accolite.db.services.EmployeeService;
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

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> getEmployeeObj(long id) {
        Optional<Employee> employeeFromDbObj = employeeRepository.findById(id);
        return employeeFromDbObj;

    }

    @Override
    public List<Employee> getEmployee() {
        return this.employeeRepository.findAll();
    }

    @Override
    public boolean disableEmployee(Employee employee) {
        Optional<Employee> empObj = this.employeeRepository.findById(employee.getId());
        Date date = new Date();
        if (empObj.isPresent()) {
            Employee employeeUpdate = empObj.get();
            employeeUpdate.setDateOfLeaving(date);

            employeeRepository.save(employeeUpdate);
            return true;
        } else {
            log.debug("Employee with employee ID: " + employee.getId() + " is not present");
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
        employeeUpdateDetailsFromDb.setClientCounterpartId(employeeUpdateDetailsFromDb.getClientCounterpartId());
        employeeUpdateDetailsFromDb.setBand(employeeDetailsFromDb.getBand());

        return employeeUpdateDetailsFromDb;
    }


}