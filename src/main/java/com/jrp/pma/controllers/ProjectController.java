package com.jrp.pma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jrp.pma.entities.Project;
import com.jrp.pma.services.EmployeeService;
import com.jrp.pma.services.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	/*
	@Autowired
	IProjectRepository projectRepo;
	
	@Autowired
	IEmployeeRepository employeeRepo;
	*/
	@Autowired
	ProjectService projectService;
	
	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping
	public String displayProjects(Model model) {
		//model.addAttribute("projects", projectRepo.findAll());
		model.addAttribute("projects", projectService.getAll());
		return "projects/list-projects";
	}
	
	@RequestMapping("/new")
	public String displayProjectForm(Model model) {
		model.addAttribute("project", new Project());
		//model.addAttribute("allEmployees", employeeRepo.findAll());
		model.addAttribute("allEmployees", employeeService.getAll());
		return "projects/new-project";
	}
	
	@PostMapping("/save")
	public String createProject(Project project, Model model) {
		// save to db
		//projectRepo.save(project);
		projectService.save(project);
		
		return "redirect:/projects";
	}
}
