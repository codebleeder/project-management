package com.jrp.pma.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dao.IEmployeeRepository;
import com.jrp.pma.dao.IProjectRepository;
import com.jrp.pma.dto.IProjectCount;
import com.jrp.pma.services.EmployeeService;
import com.jrp.pma.services.ProjectService;

@Controller
public class HomeController {

	@Value("${version}")
	private String ver;
	
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
	
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		//List<IProjectCount> projectData = projectRepo.projectCounts();
		List<IProjectCount> projectData = projectService.getProjectStatus();
		model.addAttribute("projectCounts", projectData);
		
		//model.addAttribute("employeeProjects", employeeRepo.employeeProjects());
		model.addAttribute("employeeProjects", employeeService.employeeProjects());
		model.addAttribute("versionNumber", ver);
		//Map<String, Object> map = new HashMap<>();
		ObjectMapper om = new ObjectMapper();
		String jsonString = om.writeValueAsString(projectData);
		model.addAttribute("projectStatusCnt", jsonString);
		
		return "main/home";
	}
}
