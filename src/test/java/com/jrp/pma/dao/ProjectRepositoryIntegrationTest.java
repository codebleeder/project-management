package com.jrp.pma.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jrp.pma.entities.Project;

@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@RunWith(SpringRunner.class)

@RunWith(SpringJUnit4ClassRunner.class)

@SqlGroup({
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:schema.sql", "classpath:data.sql"}),
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:drop.sql")
	})
public class ProjectRepositoryIntegrationTest {
	@Autowired
	IProjectRepository projectRepo;
	
	//@Test
	public void ifNewProjectSaved_thenSuccess() {
		Project newProject = new Project("new test project", "COMPLETE", "test description");
		projectRepo.save(newProject);
		assertEquals(5, projectRepo.findAll().size());
	}
}
