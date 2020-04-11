package com.deadlybeat.controller;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.deadlybeat.entity.Employee;
import com.deadlybeat.repository.EmployeeRepository;
import com.deadlybeat.service.ReportService;

import net.sf.jasperreports.engine.JRException;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepository repo;
	
	@Autowired
	private ReportService service;
	
	@GetMapping("/getEmployees")
	public List<Employee> getEmployees(){
		return repo.findAll();
	}
	
	@PostMapping("/addEmployee")
	public Employee saveEmployee(@RequestBody Employee emp) {
		return repo.save(emp);
	}
	
	@GetMapping("/report/{format}")
	public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
		return service.ExportReport(format);
	}
}
