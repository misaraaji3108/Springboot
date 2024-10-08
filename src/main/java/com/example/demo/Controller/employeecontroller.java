package com.example.demo.Controller;

import com.example.demo.dto.employeedto;
import com.example.demo.entities.employeeentity;
import com.example.demo.service.Employeeservice;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class employeecontroller {

    private final Employeeservice employeeservice;

    public employeecontroller(Employeeservice employeeservice) {
        this.employeeservice = employeeservice;
    }

    // Constructor for dependency injection of the repository
    // Get an employee by ID
    @GetMapping("/{employeeid}")
    public ResponseEntity<employeedto> getemployeeBYId(@PathVariable(name = "employeeid") Long id) {
     Optional <employeedto> Employeedto =  employeeservice.getEmployeeById(id);// Corrected method to `findById`
       return Employeedto
               .map(employeedto1 -> ResponseEntity.ok(employeedto1))
               .orElse(ResponseEntity.notFound().build());

    }

    // Get all employees, with optional query parameters
    @GetMapping
    public ResponseEntity<List<employeedto>> getAllemployee(@RequestParam(required = false) Integer age,
                                            @RequestParam(required = false) String sortBy) {
        return ResponseEntity.ok(employeeservice.getAllEmployees()); // Return a list of employees
    }

    // Create a new employee
    @PostMapping
    public ResponseEntity<employeedto> createNewemployee(@RequestBody @Valid employeedto inputemployee) {
        employeedto savedemployee = employeeservice.createNewEmployee(inputemployee); // Save the employee and return the entity
        return new ResponseEntity<>(savedemployee, HttpStatus.CREATED);
    }

    // Example PUT method for future functionality
    @PutMapping(path = "/{employeeid}")
    public ResponseEntity<employeedto> updateemployeeid(@RequestBody employeedto Employeedto, @PathVariable Long employeeid) {
        return ResponseEntity.ok(employeeservice.updateemployeeid(employeeid, Employeedto));
    }

    // for delete some data
    @DeleteMapping(path = "/{employeeid}")
    public ResponseEntity<Boolean> Deleteemployeebyid(@PathVariable Long employeeid) {
       boolean getdeleted = employeeservice.Deleteemployeebyid(employeeid);
       if(getdeleted) return ResponseEntity.ok(true);
       return ResponseEntity.notFound().build();
    }
    @PatchMapping(path = "/{employeeid}")
    public ResponseEntity<employeedto> updatePartialEmployeeById(@RequestBody Map<String,Object> updates, @PathVariable Long employeeid) {
        employeedto Employeedto =  employeeservice.updatePartialEmployeeById(employeeid, updates);
        if (Employeedto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(Employeedto);
    }

}