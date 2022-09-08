package com.example.demo.serviceImpl;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee savaEmployee(Employee employee) {

		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeId(long id) {
//		Optional<Employee> emp = employeeRepository.findById((int) id);
//		if(emp.isPresent()) {
//			return emp.get();
//			
//		}else {
//			throw new ResourceNotFoundException("Employee","Id",id);
//		}
		return employeeRepository.findById((int) id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));

	}

	@Override
	public Employee updateEmployee(Employee employee, int id) {
		// TODO Auto-generated method stub
		//we need to check whether Employee the givan id exit database or not
		Employee exitEmployee= employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
		
		//
		exitEmployee.setName(employee.getName());
		exitEmployee.setCity(employee.getCity());
		exitEmployee.setAddress(employee.getAddress());
		
		
		//save database
		employeeRepository.save(exitEmployee);
			
		return exitEmployee;
	}

	@Override
	public void deleteEmployee(long id) {
		//check whether a employee exit in a database or not .
		employeeRepository.findById((int) id)
		.orElseThrow(()-> new ResourceNotFoundException("Employee", "Id", id));
		employeeRepository.deleteById((int) id);
		
		
	}

	@Override
	public void deleteAllValue() {
		
		employeeRepository.deleteAll();
	}

}
