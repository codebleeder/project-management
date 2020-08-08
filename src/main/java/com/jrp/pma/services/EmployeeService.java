package com.jrp.pma.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
}
