package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.JpaRepositoryNameSpaceHandler;
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

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/employee/")//source path
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	// http://localhost:8080/employee/save
	@PostMapping("save")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		Employee emp = employeeService.savaEmployee(employee);
		return ResponseEntity.ok().body(emp);
	}
	// http://localhost:8080/employee/getList/2
	@GetMapping("getList")
	public List<Employee> getAllEmployee() {
		return employeeService.getAllEmployee();
	}

	// http://localhost:8080/employee/getById/2
	@GetMapping("getById/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int empId) {

		return new ResponseEntity<Employee>(employeeService.getEmployeeId(empId), HttpStatus.OK);

	}

	// http://localhost:8080/employee/getById/2
	@PutMapping("update/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") int empId, @RequestBody Employee employee) {
		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, empId), HttpStatus.OK);
	}

	// Delete Employee
//	http://localhost:8080/employee/delete/2
	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") long empId) {
		employeeService.deleteEmployee(empId);
		return new ResponseEntity<String>("Employee Delete SuccessFully !..", HttpStatus.OK);
	}
//	http://localhost:8080/employee/deleteAll
	@DeleteMapping("deleteAll")
	public ResponseEntity<String> deleteAllValue(){
		employeeService.deleteAllValue();
		return new ResponseEntity<String>("Table Value delete Successfully!..",HttpStatus.OK);
	}

}
