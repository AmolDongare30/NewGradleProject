package com.employeecrudapp.crudapp;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employeecrudapp.crudapp.Employee;

@RestController
public class EmployeeController 
{
	//cache ---> is temporary data structure or it is buffer--> we can load data in cache
	// we will not call DB.
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private CacheManager cacheManager;
	
	@GetMapping("/getMsg/{name}")
	public String getMessage(@PathVariable String name)
	{
		Calendar c = Calendar.getInstance();
		int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

		if(timeOfDay >= 0 && timeOfDay < 12){
		  return "Good Morning --> "+name;      
		}else if(timeOfDay >= 12 && timeOfDay < 16){
		    return "Good Afternoon --> "+name;
		}else if(timeOfDay >= 16 && timeOfDay < 21){
		   return "Good Evening --> " +name;
		}else if(timeOfDay >= 21 && timeOfDay < 24){
			 return "Good Night --> "+name;
		}
		
		String greetings="Good Morning "+name;
		return greetings;
	}
	
	//CRUD Operation
	//create-->Request body-->Employee--->Controller--->Dao-->DB entry-->AutogenerateID
	//read--->Get all employee record those are present at DB 2) search specific employe with id
	//update--> Request Body-->update(update the existing employee)
	//delete--> delete record with employee(pathvariable--->employee id)
	
	
	//Http method -->establish communication with server
	//post-->to create entity with specific request body
	//put--> update entity but put method we use for other things also.but standard we are following
	//get-->fetch/search record
	//delete--> to delete existing entity.
	
	
	@PostMapping("/createEmp")
	public Employee createEmployee(@RequestBody Employee employee)
	{
		
		return employeeRepository.save(employee);
	}
	
	
	@GetMapping("/getAllEmps")
	//public List<Employee> getAllEmployees()
	public String getAllEmployees()
	{
		//return employeeRepository.findAll();
		return  cacheManager.cache.values().toString();
		
	}
	
	
	@GetMapping("/getEmp/{empID}")
	public String getEmployeeByID(@PathVariable int empID)
	{
		//Optional<Employee> employee =employeeRepository.findById(empID);//optional is use to avoid Null Pointer exception
		
		Optional<Employee> employee =Optional.ofNullable(CacheManager.cache.get(empID));
		if(employee.isPresent())
		{
			return employee.toString();
		}
		else 
		{
			return "Employee is not Present for given ID";
		}
		
	}
	
	@PutMapping("/updateEmp")
	public String updateEmployee(@RequestBody Employee employee)
	{
		if(employee.getEmpID()>0)
		{
			Optional<Employee> oldEmployee=employeeRepository.findById(employee.getEmpID());
			if(oldEmployee.isPresent())
			{
				
				return employeeRepository.save(employee).toString();
				/*
				 * oldEmployee.get().setEmpName(employee.getEmpName());
				 * oldEmployee.get().setEmpAddress(employee.getEmpAddress());
				 * oldEmployee.get().setEmpSalary(employee.getEmpSalary());
				 * employeeRepository.save(oldEmployee.get()).toString();
				 */
			}
			else
			{
				return "Employee is not Present for given ID";
			}
		}
		else
		{
			return "Employee ID is Invalid";
		}
		
	}
	@DeleteMapping("deleteEmp/{empID}")
	public String deleteEmployee(@PathVariable int empID)
	{
		Optional<Employee> oldEmployee = employeeRepository.findById(empID);
		if(oldEmployee.isPresent())
		{
			employeeRepository.deleteById(empID);
			return "Employee Deleted Successfully";
		}
		else
		{
			return "Employee is not present for given ID";
		}
		
	}

	
	
}
