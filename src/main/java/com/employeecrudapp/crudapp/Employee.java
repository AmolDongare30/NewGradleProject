package com.employeecrudapp.crudapp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;



@Entity
@Data
public class Employee 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int empID;
	private String empName;
	private String empAddress;
	private long empSalary;
	// we dont need to add getter,setter and @RequiredArgsConstructor @ToString @EqualsAndHashCode when we define annotation @Data.

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(int empID, String empName, String empAddress, long empSalary) {
		super();
		this.empID = empID;
		this.empName = empName;
		this.empAddress = empAddress;
		this.empSalary = empSalary;
	}

	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpAddress() {
		return empAddress;
	}

	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}

	public long getEmpSalary() {
		return empSalary;
	}

	public void setEmpSalary(long empSalary) {
		this.empSalary = empSalary;
	}

	@Override
	public String toString() {
		return "Employee [empID=" + empID + ", empName=" + empName + ", empAddress=" + empAddress + ", empSalary="
				+ empSalary + "]";
	}
	
	
}
