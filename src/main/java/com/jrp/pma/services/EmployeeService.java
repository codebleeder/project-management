package com.jrp.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jrp.pma.dao.IEmployeeRepository;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Employee;

@Service
public class EmployeeService {

	
	// @Autowired // Field injection
	IStaffRepository staffRepo;
	
	// constructor injection
	
	public EmployeeService(@Qualifier("staffRepositoryImpl2") IStaffRepository staffRepo) {
		super();
		this.staffRepo = staffRepo;
	}
	
	
	// setter injection
	/*
	@Autowired
	public void setEmpRepo(IEmployeeRepository empRepo) {
		this.empRepo = empRepo;
	}
	*/
	
	@Autowired
	IEmployeeRepository empRepo;
	
	public Employee save(Employee emp) {
		return empRepo.save(emp);
	}
	
	public Iterable<Employee> getAll(){
		return empRepo.findAll();
	}
	
	public List<EmployeeProject> employeeProjects(){
		return empRepo.employeeProjects();
	}
	
	public Employee findByEmployeeId(long id) {
		return empRepo.findByEmployeeId(id);
	}

	public void delete(Employee emp) {
		empRepo.delete(emp);
	}
}
