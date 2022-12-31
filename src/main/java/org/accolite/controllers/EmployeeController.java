package org.accolite.controllers;

import org.accolite.buisnesslogic.EmployeeComponent;
import org.accolite.db.entities.Employee;
import org.accolite.db.services.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/beat/api/v1/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeComponent employeeComponent;

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    private ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok().body(this.employeeService.createEmployee(employee));
    }

    @PutMapping(value = "/update", consumes = "application/json", produces = "plain/text")
    private ResponseEntity<String> updateEmployee(@RequestBody Employee employee) {
        boolean empUpdated = employeeComponent.updateEmployee(employee);
        if (empUpdated) {
            return ResponseEntity.ok().body("Employee Updated");
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/get", consumes = "application/json", produces = "application/json")
    private ResponseEntity<List<Employee>> getEmployee() {
        return ResponseEntity.ok().body(this.employeeService.getEmployee());
    }

    @PutMapping(value = "/disable", consumes = "application/json", produces = "application/json")
    private ResponseEntity<Employee> disableEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok().body(this.employeeService.disableEmployee(employee));
    }

}
