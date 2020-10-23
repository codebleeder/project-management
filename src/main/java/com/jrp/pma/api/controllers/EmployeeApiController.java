package com.jrp.pma.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jrp.pma.dao.IEmployeeRepository;
import com.jrp.pma.entities.Employee;

@RestController
@RequestMapping("/app-api/employees")
public class EmployeeApiController {
	@Autowired
	IEmployeeRepository employeeRepo;
	
	@GetMapping("")
	public Iterable<Employee> getEmployees(){
		return employeeRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable("id") Long id) {
		return employeeRepo.findById(id).get();
	}
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Employee create(@RequestBody @Valid Employee employee) {
		return employeeRepo.save(employee);
	}
	
	@PutMapping(consumes = "application/json", path="/{id}")
	@ResponseStatus(code = HttpStatus.OK)	
	public Employee update(@RequestBody @Valid Employee employee){	
		return employeeRepo.save(employee);
	}
	
	@PatchMapping(consumes = "application/json", path="/{id}")
	@ResponseStatus(code = HttpStatus.OK)	
	public Employee updatePartial(@RequestBody @Valid Employee patchEmp, @PathVariable("id") long id){
		Employee emp = employeeRepo.findById(id).get();
		if(patchEmp.getEmail() != null) {
			emp.setEmail(patchEmp.getEmail());
		}
		if(patchEmp.getFirstName() != null) {
			emp.setFirstName(patchEmp.getFirstName());
		}
		if(patchEmp.getLastName() != null) {
			emp.setLastName(patchEmp.getLastName());
		}
		return employeeRepo.save(emp);
	}
	
	@DeleteMapping(path="/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {		
		try {
			employeeRepo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
/*
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable("id") Long id) {
		return employeeRepo.findById(id).get();
	}
	*/
	
	@GetMapping(params= {"page","size"})
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Employee> findPaginatedEmployees(@RequestParam("page") int page, 
			@RequestParam("size") int size){
		Pageable pageAndSize = PageRequest.of(page, size);
		return employeeRepo.findAll(pageAndSize);
	}
}
