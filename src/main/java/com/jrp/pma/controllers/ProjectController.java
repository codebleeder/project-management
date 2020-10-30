package com.jrp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dto.TimeChartData;
import com.jrp.pma.entities.Employee;
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
	
	@GetMapping("/timelines")
	public String displayProjectTimelines(Model model) throws JsonProcessingException {
		List<TimeChartData> timeLineData = projectService.getTimeData();
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(timeLineData);
		model.addAttribute("projectTimeList", jsonString);
		System.out.println(jsonString);
		return "projects/project-timelines";
	}
	
	@GetMapping("/update")
	public String displayUpdateProject(@RequestParam("id") long id, Model model) {
		Project project = projectService.findByProjectId(id);
		model.addAttribute("project", project);
		return "projects/new-project";
	}
	@GetMapping("/delete")
	public String deleteProject(@RequestParam("id") long id, Model model) {
		Project project = projectService.findByProjectId(id);
		projectService.delete(project);
		return "redirect:/projects";
	}
}
