package org.accolite.db.services;

import org.accolite.db.entities.Employee;
import org.accolite.pojo.EmployeeCard;
import org.accolite.pojo.EmployeeUpdateDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeService {
    Employee createEmployee(Employee employee);
    Optional<Employee> getEmployeeById(long id);
    List<Employee> getEmployeesByName(String name);
    List<Employee> getEmployee();
    boolean disableEmployee(long id);
    boolean saveUpdateEmployee(Employee employee);
    EmployeeUpdateDetails cloneDetails(EmployeeUpdateDetails employeeUpdateDetailsFromDb, Employee employeeDetailsFromDb);
    Employee cloneToEmployee(Employee employeeUpdateDetailsFromDb, EmployeeUpdateDetails employeeDetailsFromDb);
    EmployeeCard getLeadCard(long leadId);
    EmployeeCard cloneToEmployeeCard(EmployeeCard curEmployeeCard, Employee curEmployee);
}