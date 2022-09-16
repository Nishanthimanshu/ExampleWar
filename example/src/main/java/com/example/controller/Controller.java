package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.EmployeeDAO;
import com.example.entity.Employee;
import com.example.entity.Employees;

@RestController
public class Controller {
	@Autowired
    private EmployeeDAO employeeDao;
     
	@RequestMapping(value = "/employees")
    @GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public Employees getEmployees() 
    {
        return employeeDao.getAllEmployees();
    }
     
	@RequestMapping(value = "/addemployee")
    @PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
        produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee) 
    {
        Integer id = employeeDao.getAllEmployees().getEmployeeList().size() + 1;
        employee.setId(id);
        employeeDao.addEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body("Sucessfully created the employee with id ="+id);
    }
    
	@RequestMapping("/add")
    @PostMapping
    //@ResponseBody
    public double add(@RequestParam(name="a") double a, @RequestParam(name="b") double b){
    	double c = a+b;
    	return c;
    }
    
	@RequestMapping("/delete/{id}")
    @DeleteMapping
    public ResponseEntity<String> delete(@PathVariable(value="id") int id) {
    	boolean result = employeeDao.deleteEmployee(id);
    	String s = "";
    	if(result) s="Sucessfully deleted the Employee with id "+id;
    	else s ="Please provide a valid Employee Id!!!!";
    	return ResponseEntity.status(HttpStatus.OK).body(s);
    }
    
	@RequestMapping(value = "/update/{id}", consumes = {"application/json", "application/xml"})
    @PutMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<String> update(@PathVariable(value="id") int id, @RequestBody Employee employee) {
    	boolean result = employeeDao.updateEmployee(id, employee);
    	String s = "";
    	if(result) s="Sucessfully updated the Employee with id "+id;
    	else s ="Please provide a valid Employee Id!!!!";
    	return ResponseEntity.status(HttpStatus.OK).body(s);
    }
}
