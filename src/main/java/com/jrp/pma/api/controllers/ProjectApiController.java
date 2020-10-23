package com.jrp.pma.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jrp.pma.dao.IProjectRepository;
import com.jrp.pma.entities.Project;

@RestController
@RequestMapping("/app-api/projects")
public class ProjectApiController {
	@Autowired
	IProjectRepository projectRepo;
	
	@GetMapping("")
	public Iterable<Project> getEmployees(){
		return projectRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Project getEmployeeById(@PathVariable("id") Long id) {
		return projectRepo.findById(id).get();
	}
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Project create(@RequestBody @Valid Project project) {
		return projectRepo.save(project);
	}
	
	@PutMapping(consumes = "application/json", path="/{id}")
	@ResponseStatus(code = HttpStatus.OK)	
	public Project update(@RequestBody @Valid Project project){	
		return projectRepo.save(project);
	}
	
	@PatchMapping(consumes = "application/json", path="/{id}")
	@ResponseStatus(code = HttpStatus.OK)	
	public Project updatePartial(@RequestBody @Valid Project patchProj, @PathVariable("id") long id){
		Project proj = projectRepo.findById(id).get();
		if(patchProj.getName() != null) {
			proj.setName(patchProj.getName());
		}
		if(patchProj.getStage() != null) {
			proj.setStage(patchProj.getStage());
		}
		if(patchProj.getDescription() != null) {
			proj.setDescription(patchProj.getDescription());
		}
		return projectRepo.save(proj);
	}
	
	@DeleteMapping(path="/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {		
		try {
			projectRepo.deleteById(id);
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
}
