package com.deadlybeat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deadlybeat.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
