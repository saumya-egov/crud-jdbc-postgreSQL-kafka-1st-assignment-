package com.newproject.Controller;

import java.sql.SQLException;
import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import com.newproject.Dao.StudentDao;
import com.newproject.model.Student;

@RestController
public class StudentControl {

	@Autowired
	private StudentDao dao;
	@Autowired
	private KafkaTemplate<String,String> kafkatemp;
	private static final String TOPIC="test";
	@GetMapping("/getStudents")
	public List<Student> getStudents() throws JSONException, SQLException {
		return dao.findAll();
	}
	
	@GetMapping("/insertstudent")
	public String  insert()
	{
		return dao.saveRecord("choti","28");
	}
	@GetMapping("/searchrecord")
	public List<Student>  searchrecord() throws JSONException, SQLException
	{
		return dao.searchrecord("ABV");
	}
	@GetMapping("/updatestudent")
	public String  updateRecords()
	{
		return dao.updateRecord("ABV","kriti");
	}
	@GetMapping("/deletestudent")
	public String  deleteRecords()
	{
		return dao.deleteRecord("ABV");
	}
	@GetMapping("/kafka/{message}")
	public String  post(@PathVariable("message") final String message)
	{
		kafkatemp.send(TOPIC,message);
		return "successfully posted to kafka";
	}


}
