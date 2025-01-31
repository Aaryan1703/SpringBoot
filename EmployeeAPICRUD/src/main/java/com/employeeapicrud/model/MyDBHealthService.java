package com.employeeapicrud.model;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

public class MyDBHealthService implements HealthIndicator {

	public static String DB_Service="Database Service";
	
	public boolean isHealthGood() {
		return true;
	}
	@Override
	public Health health() {
		if(isHealthGood()) {
			return Health.up().withDetail(DB_Service,"Database Service is Running up").build();
		}
		return Health.down().withDetail(DB_Service, "Database Service is Runnning down").build();
	}

}
