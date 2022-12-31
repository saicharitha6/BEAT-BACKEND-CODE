package org.accolite.db.services.employee;

import org.accolite.db.entities.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    Employee createEmployee(Employee employee);
    Employee getEmployeeObj(Employee employee);
    List<Employee> getEmployee();
    Employee disableEmployee(Employee employee);

    boolean saveUpdateEmployee(Employee employee);
}
