# Project Management
A CRUD application written in Spring boot framework.

## Content
1. Specifications
2. Libraries, frameworks, tools and platforms used

## Specifications

### Home dashboard
* Shows summary of projects by status and employees with project count:
![Home1](screenshots/page1_home.png)

### Projects
* Users can create/edit projects. 
* Each project should be assigned one of the following status: Not Started, In Progress, Completed
* Each project has fields: Name, Status, Description, associated list of employees, project start and end dates:

![Create/Edit Project](screenshots/create_edit_project.png)

### Employee
* Users can create/edit employee
* Each employee has the following fields: First name, Last name, and email

![Create/Edit Employee](screenshots/create_edit_employee.png)

* Pages to display Project and Employee lists:

![Employee list](screenshots/employee_list.png)
![Project list](screenshots/project_list.png)

## Libraries, frameworks, tools and platforms used
* Backend: Spring-boot, Spring data JPA
* Frontend: Thymeleaf, Bootstrap, JQuery, chart.js, Google charts
* Database: PostgreSQL
* Platforms deployed on: containerized using Docker
* Created and connected a DB instance on Amazon RDS