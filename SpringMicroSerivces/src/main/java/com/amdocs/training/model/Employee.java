package com.amdocs.training.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
	int id ;
    @Column(name = "age", nullable = false)
	int age ;
    @Column(name = "name", nullable = false)
	String name;
	@Override
	public String toString() {
		return "Employee [age=" + age + ", name=" + name + "]";
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
