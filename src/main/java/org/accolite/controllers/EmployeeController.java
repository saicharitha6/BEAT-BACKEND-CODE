package org.accolite.controllers;

import org.accolite.buisnesslogic.EmployeeComponent;
import org.accolite.db.entities.Employee;
import org.accolite.db.services.EmployeeService;
import org.accolite.pojo.EmployeeCard;
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

    @GetMapping(value = PathConstants.getPath, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<Employee>> getEmployee() {
        List<Employee> empList = this.employeeService.getEmployee();
        if (empList.size() == 0) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        else return ResponseEntity.ok().body(empList);
    }

    @PutMapping(value = PathConstants.disablePath, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    private ResponseEntity<String> disableEmployee(@PathVariable long id) {
        boolean empDisabled = this.employeeService.disableEmployee(id);
        if (empDisabled) return ResponseEntity.ok().body("Employee disabled");
        else return ResponseEntity.notFound().build();
    }

    @GetMapping(value = PathConstants.getByIdPath, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Employee> getEmployeeById(@PathVariable long id){
        Optional<Employee> employeeFromDbObj = employeeService.getEmployeeById(id);
        if (employeeFromDbObj.isPresent()) return ResponseEntity.ok().body(employeeFromDbObj.get());
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping(value = PathConstants.getHierarchyPath, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<EmployeeCard>> getEmployeeHierarchy(@PathVariable long id) {
        List<EmployeeCard> empCardList = this.employeeComponent.getEmployeeHierarchy(id);
        if (empCardList.size() == 0) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        else return ResponseEntity.ok().body(empCardList);
    }

    @GetMapping(value = PathConstants.searchPath, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<EmployeeCard>> searchEmployee(@PathVariable String name){
        List<EmployeeCard> empCardList = this.employeeComponent.getEmployeeCardsByName(name);
        if(empCardList.size() == 0) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        else return ResponseEntity.ok().body(empCardList);
    }
}