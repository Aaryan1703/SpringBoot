package com.employeeapicrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeeapicrud.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

	
}
