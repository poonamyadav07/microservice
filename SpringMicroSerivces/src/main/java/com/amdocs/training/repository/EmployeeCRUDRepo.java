package com.amdocs.training.repository;

import org.springframework.data.repository.CrudRepository;

import com.amdocs.training.model.Employee;

public interface EmployeeCRUDRepo extends CrudRepository<Employee, Integer>{

}
