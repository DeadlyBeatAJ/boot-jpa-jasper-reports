package com.deadlybeat.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.deadlybeat.entity.Employee;
import com.deadlybeat.repository.EmployeeRepository;

import net.bytebuddy.implementation.bind.MethodDelegationBinder.ParameterBinding.Anonymous;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportService {

	@Autowired
	private EmployeeRepository repository;
	
	public String ExportReport(String reportFormat)throws FileNotFoundException, JRException {
		
		List<Employee> employees=repository.findAll();
		
		//load file 
		String resourceLocation="classpath:employees.jrxml";
		File file=ResourceUtils.getFile(resourceLocation);
		//File file= ResourceUtils.getFile("classpath:employees.jrxml");
		
		//Load file
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		
		//using the datasource we can fill the report in Jasper
		JRBeanCollectionDataSource datasource= new JRBeanCollectionDataSource(employees);
		
		Map<String, Object> parameters= new HashMap<>();
		parameters.put("createdBy", "DeadlyBeat");
		
		JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport, parameters, datasource);
		
		String path= "C:\\Users\\akajadha\\Desktop\\Reports";
		//now check the rReportFormat i/p aif its pdf or html or anything and proceed
		
		
		
		/*
		 * if(reportFormat.equalsIgnoreCase("html")) {
		 * JasperExportManager.exportReportToHtmlFile(jasperPrint,path+
		 * "\\employees.html"); }
		 */
		
		int random = new Random().nextInt();
		
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\employees"+random+".html");
		}
		
		if(reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, path+"\\employees"+random+".pdf");
		}
		
		return "report generated in path:"+path;
	}
	
}
