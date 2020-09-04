package com.jrp.pma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jrp.pma.dao.IEmployeeRepository;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.services.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	IEmployeeRepository employeeRepo;
	
	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping
	public String displayEmployees(Model model) {
		//model.addAttribute("employees", employeeRepo.findAll());
		model.addAttribute("employees", employeeService.getAll());
		return "employees/list-employees";
	}
	
	@RequestMapping("/new")
	public String displayEmployeeForm(Model model) {
		model.addAttribute("employee", new Employee());
		return "employees/new-employee";
	}
	@PostMapping("/save")
	public String createEmployee(Model model, Employee employee) {
		//employeeRepo.save(employee);
		employeeService.save(employee);
		return "redirect:/employees/new";
	}
}
