package com.employeeapicrud.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeeapicrud.EmployeeApicrudApplication;
import com.employeeapicrud.exception.EmployeeDatabaseEmptyException;
import com.employeeapicrud.exception.EmployeeNotFoundException;
import com.employeeapicrud.model.Employee;
import com.employeeapicrud.repository.EmployeeRepository;

import org.apache.catalina.core.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
 Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	EmployeeRepository employeeRepository;
	
	@PostMapping("/employee")
	public String createEmployee(@RequestBody Employee employee) {
		
		employeeRepository.save(employee);
		logger.info("Data inserted Sucessfully");
		return "Employee cretaed in database";
		
	}

	@GetMapping("/employee")
	public ResponseEntity<List<Employee>>getAllEmployee(){
		List<Employee>  empList=new ArrayList<>();
		employeeRepository.findAll().forEach(empList::add);
		if (empList.isEmpty()) {
			 logger.info("Employee not found in DB");
            throw new EmployeeDatabaseEmptyException("No employees found in the database.");
           
        }
		 logger.info("Employee Found..!");
		return new ResponseEntity<List<Employee>>(empList,HttpStatus.OK);
		
	}
	
	@GetMapping("/employee/{empid}")
	public ResponseEntity<Employee>getEmployeeById(@PathVariable long empid){
		Optional<Employee> emp=employeeRepository.findById(empid);
		if(emp.isPresent()) {
			logger.info("Employee Found..!");
			return new ResponseEntity<Employee>(emp.get(),HttpStatus.FOUND);
		}else {
			logger.info("Employee not found in DB");
			throw new EmployeeNotFoundException("Employee with ID " + empid + " not found.");
		}
	}
	
	@PutMapping("/employee/{empid}")
	public String updateEmployeeById(@PathVariable long empid,@RequestBody Employee employee) {
		Optional<Employee> emp=employeeRepository.findById(empid);
		if(emp.isPresent()) {
			Employee existEmp=emp.get();
			existEmp.setEmp_name(employee.getEmp_name());
			existEmp.setEmp_salery(employee.getEmp_salery());
			existEmp.setEmp_age(employee.getEmp_age());
			existEmp.setEmp_city(employee.getEmp_city());
			employeeRepository.save(existEmp);
			logger.info("Employee Details Updated Sucessfully");
			return "Employeee Details against id "+empid;
		}
		
		else {
			logger.info("Employee id { } NOT Found..!",empid);
			throw new EmployeeNotFoundException("Employee with ID " + empid + " not found.");
		}
	}
	
	@DeleteMapping("/employee/{empid}")
	 public ResponseEntity<String> deleteEmpById(@PathVariable Long empid) {
	        if (employeeRepository.existsById(empid)) {
	            employeeRepository.deleteById(empid);
	            logger.info("Employee Details Deleted Sucessfully");
	            return ResponseEntity.ok("Employee with ID " + empid + " deleted successfully.");
	        } else {
	        	logger.info("Employee id {} Not Found  Sucessfully",empid);
	            throw new EmployeeNotFoundException("Employee with ID " + empid + " not found.");
	        }
	    }
	
	@DeleteMapping("/employee")
	public ResponseEntity<String> deleteAllEmployee() {
        if (employeeRepository.count() == 0) {
        	logger.info("Databse is Empty");
            throw new EmployeeDatabaseEmptyException("No employees to delete in the database.");
        }
        employeeRepository.deleteAll();
        logger.info("Employee Record are Deleted");
        return ResponseEntity.ok("All employees deleted successfully.");
    }
	

	
}










