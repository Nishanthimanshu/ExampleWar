package com.example.dao;

import org.springframework.stereotype.Repository;

import com.example.entity.Employee;
import com.example.entity.Employees;

@Repository
public class EmployeeDAO {
	private static Employees list = new Employees();
    
    static
    {
        list.getEmployeeList().add(new Employee(1, "Lokesh", "Gupta", "lgupta@gmail.com"));
        list.getEmployeeList().add(new Employee(2, "Alex", "Kolenchiskey", "abc@gmail.com"));
        list.getEmployeeList().add(new Employee(3, "David", "Kameron", "titanic@gmail.com"));
    }
     
    public Employees getAllEmployees() 
    {
        return list;
    }
     
    public void addEmployee(Employee employee) {
        list.getEmployeeList().add(employee);
    }
    
    public boolean deleteEmployee(int id) {
    	for(Employee e: list.getEmployeeList()) {
    		if(e.getId()==id) {
    			list.getEmployeeList().remove(e);
    			return true;
    		}
    	}
    	return false;
    }
    
    public boolean updateEmployee(int id, Employee employee) {
    	for(Employee e: list.getEmployeeList()) {
    		if(e.getId()==id) {
    			employee.setId(id);
    			list.getEmployeeList().remove(e);
    			list.getEmployeeList().add(employee);
    			return true;
    		}
    	}
    	return false;
    }
}
