package com.employeeapicrud.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Employee")
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long empid ;
	
	@Column(name="emp_name")
	private String emp_name;
	
	@Column(name="emp_salery")
	private Float emp_salery;
	
	@Column(name="emp_age")
	private int emp_age;
	
	@Column(name="emp_city")
	private String emp_city;

	public Employee(long empid, String emp_name, Float emp_salery, int emp_age, String emp_city) {
		super();
		this.empid = empid;
		this.emp_name = emp_name;
		this.emp_salery = emp_salery;
		this.emp_age = emp_age;
		this.emp_city = emp_city;
	}
	public Employee() {
		
	}

	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", emp_name=" + emp_name + ", emp_salery=" + emp_salery + ", emp_age="
				+ emp_age + ", emp_city=" + emp_city + "]";
	}
	public long getEmpid() {
		return empid;
	}

	public void setEmpid(long empid) {
		this.empid = empid;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public Float getEmp_salery() {
		return emp_salery;
	}

	public void setEmp_salery(Float emp_salery) {
		this.emp_salery = emp_salery;
	}

	public int getEmp_age() {
		return emp_age;
	}

	public void setEmp_age(int emp_age) {
		this.emp_age = emp_age;
	}

	public String getEmp_city() {
		return emp_city;
	}

	public void setEmp_city(String emp_city) {
		this.emp_city = emp_city;
	}

}
