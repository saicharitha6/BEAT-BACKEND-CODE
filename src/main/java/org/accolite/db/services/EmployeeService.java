package org.accolite.db.services;

import org.accolite.db.entities.Employee;
import org.accolite.pojo.EmployeeUpdateDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeService {
    Employee createEmployee(Employee employee);
    Optional<Employee> getEmployeeObj(long id);
    List<Employee> getEmployee();
    boolean disableEmployee(Employee employee);

    boolean saveUpdateEmployee(Employee employee);

    EmployeeUpdateDetails cloneDetails(EmployeeUpdateDetails employeeUpdateDetailsFromDb, Employee employeeDetailsFromDb);
}
