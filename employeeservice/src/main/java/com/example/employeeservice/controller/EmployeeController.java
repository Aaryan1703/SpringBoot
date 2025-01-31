package com.example.employeeservice.controller;

import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepo employeeRepo;


    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
        employeeRepo.save(employee);
        return new ResponseEntity<>("Employee Added", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeRepo.findAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        Optional<Employee> employee = employeeRepo.findById(id);
        return employee.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable int id) {
        employeeRepo.deleteById(id);
        return new ResponseEntity<>("Employee Deleted", HttpStatus.OK);
    }
}