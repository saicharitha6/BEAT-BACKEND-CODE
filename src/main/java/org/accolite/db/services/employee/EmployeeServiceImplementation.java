package org.accolite.db.services.employee;

import lombok.extern.slf4j.Slf4j;
import org.accolite.db.entities.Employee;
import org.accolite.db.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EmployeeServiceImplementation implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public Employee getEmployeeObj(Employee employee) {
        Optional<Employee> employeeFromDbObj = employeeRepository.findById(employee.getId());
        return employeeFromDbObj.orElse(null);

    }

    @Override
    public List<Employee> getEmployee() {
        return this.employeeRepository.findAll();
    }

    @Override
    @Transactional
    public Employee disableEmployee(Employee employee) {
        Optional<Employee> empObj = this.employeeRepository.findById(employee.getId());
        Date date = new Date();
        if (empObj.isPresent()) {
            Employee employeeUpdate = empObj.get();
            employeeUpdate.setDateOfLeaving(date);

            employeeRepository.save(employeeUpdate);
            return employeeUpdate;
        } else {
            log.debug("Employee with employee ID: " + employee.getId() + " is not present");
            return employee;
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


}