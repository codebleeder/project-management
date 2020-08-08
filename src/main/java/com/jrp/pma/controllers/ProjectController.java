package com.jrp.pma.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jrp.pma.dao.IEmployeeRepository;
import com.jrp.pma.dao.IProjectRepository;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	@Autowired
	IProjectRepository projectRepo;
	
	@Autowired
	IEmployeeRepository employeeRepo;
	
	@RequestMapping
	public String displayProjects(Model model) {
		model.addAttribute("projects", projectRepo.findAll());
		return "projects/list-projects";
	}
	
	@RequestMapping("/new")
	public String displayProjectForm(Model model) {
		model.addAttribute("project", new Project());
		model.addAttribute("allEmployees", employeeRepo.findAll());
		return "projects/new-project";
	}
	
	@PostMapping("/save")
	public String createProject(Project project, Model model) {
		// save to db
		projectRepo.save(project);
		
		return "redirect:/projects";
	}
}
