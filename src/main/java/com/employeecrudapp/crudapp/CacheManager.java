package com.employeecrudapp.crudapp;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CacheManager 
{
	@Autowired
	EmployeeRepository employeeRepository;
	public static HashMap<Integer,Employee> cache=new HashMap<>();
	
	@Scheduled(cron = "* * * * * *")
	public void loadCache()
	{
		List<Employee>employeeList= employeeRepository.findAll();
		employeeList.forEach(employee -> cache.put(employee.getEmpID(),employee));
	}
}
