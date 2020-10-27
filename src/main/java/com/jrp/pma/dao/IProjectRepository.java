package com.jrp.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jrp.pma.dto.IProjectCount;
import com.jrp.pma.dto.TimeChartData;
import com.jrp.pma.entities.Project;

public interface IProjectRepository extends CrudRepository<Project, Long>{
	@Override
	public List<Project> findAll();
	
	@Query(nativeQuery = true, value = "select p.stage as stage, count(p.stage) as count from project p " + 
			"group by p.stage ")
	public List<IProjectCount> projectCounts();
	
	@Query(nativeQuery=true, value="select name as projectName, start_date as startDate, end_date as endDate"
			+ " from project where start_date is not null")
	public List<TimeChartData> getTimeData();
}
