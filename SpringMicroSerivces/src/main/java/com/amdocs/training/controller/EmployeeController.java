package com.amdocs.training.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amdocs.training.exception.ResourceNotFoundException;
import com.amdocs.training.model.Employee;
import com.amdocs.training.repository.EmployeeCRUDRepo;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	EmployeeCRUDRepo repo ;
	
	@PostMapping("/createEmploye")
	EntityModel<Employee> createEmployee(@RequestBody Employee emp) {
		
		Employee e = repo.save(emp);
		
		EntityModel<Employee> resource = EntityModel.of(e);
		WebMvcLinkBuilder linkto= linkTo(methodOn(this.getClass()).getEmployee());
		resource.add(linkto.withRel("create-employee"));
		return resource ;
	}
	
	@GetMapping("/getAllEmployee")
	List<Employee> getEmployee() {
		
		List<Employee> e = (List<Employee>) repo.findAll();
		return e ;
	}
	
	@GetMapping("/getAllEmployeeById/{id}")
	ResponseEntity<Employee> getEmployeeByID(@PathVariable(value = "id") int employeeId) throws ResourceNotFoundException {
		Employee employee = repo.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(employee);
	}
	
	@PostMapping("/updateEmployeeName/{id}/{name}")
	ResponseEntity<Employee> updateEmployee(@PathVariable int id, @PathVariable String name ) throws ResourceNotFoundException {
		
		Employee e = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));
		e.setName(name);
		final Employee updatedEmployee = repo.save(e);
		return ResponseEntity.ok().body(updatedEmployee);
	}
	
	@DeleteMapping("/deleteEmployeeById/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable int id) throws ResourceNotFoundException {
		Employee employee = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));

		repo.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	
	}
}
