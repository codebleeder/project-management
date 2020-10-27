package com.jrp.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrp.pma.dao.IProjectRepository;
import com.jrp.pma.dto.IProjectCount;
import com.jrp.pma.dto.TimeChartData;
import com.jrp.pma.entities.Project;

@Service
public class ProjectService {
	@Autowired
	IProjectRepository projectRepo;
	
	public Project save(Project project) {
		return projectRepo.save(project);
	}
	
	public List<Project> getAll(){
		return projectRepo.findAll();
	}
	
	public List<IProjectCount> getProjectStatus(){
		return projectRepo.projectCounts();
	}
	
	public List<TimeChartData> getTimeData(){
		return projectRepo.getTimeData();
	}
}
