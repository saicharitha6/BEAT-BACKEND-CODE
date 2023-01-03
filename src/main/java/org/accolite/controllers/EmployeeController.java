package org.accolite.controllers;

import org.accolite.buisnesslogic.EmployeeComponent;
import org.accolite.db.entities.Employee;
import org.accolite.db.services.EmployeeService;
import org.accolite.pojo.EmployeeUpdateDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200/profile")
@RequestMapping(value = PathConstants.employeePath)
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeComponent employeeComponent;

    @PostMapping(value = PathConstants.createPath, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok().body(this.employeeService.createEmployee(employee));
    }

    @PutMapping(value = PathConstants.updatePath, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    private ResponseEntity<String> updateEmployee(@RequestBody EmployeeUpdateDetails employeeUpdateDetailsFromClient) {
        boolean empUpdated = employeeComponent.updateEmployee(employeeUpdateDetailsFromClient);
        if (empUpdated) return ResponseEntity.ok().body("Employee updated");
        else return ResponseEntity.notFound().build();
    }

    @GetMapping(value = PathConstants.getPath, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Employee> getEmployee(@PathVariable String id) {
        long empId = Long.valueOf(id);
        Optional<Employee> empDetails = this.employeeService.getEmployeeObj(empId);
        if (empDetails.isPresent()){
            return ResponseEntity.ok().body(empDetails.get());
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }

    @PutMapping(value = PathConstants.disablePath, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    private ResponseEntity<String> disableEmployee(@RequestBody Employee employee) {
        boolean empDisabled = this.employeeService.disableEmployee(employee);
        if (empDisabled) return ResponseEntity.ok().body("Employee disabled");
        else return ResponseEntity.notFound().build();
    }
}