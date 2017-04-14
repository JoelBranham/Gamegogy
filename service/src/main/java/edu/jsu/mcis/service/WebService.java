package edu.jsu.mcis.service;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.json.simple.*;

import edu.jsu.mcis.*;
@RestController

public class WebService {

	private final CSVReader reader = new CSVReader();

	@RequestMapping(value="/gamegogy/studentlist", method=RequestMethod.GET)
	public String[] getStudentList(){
		return reader.getStudentIDStrings();
	}
	
	@RequestMapping(value="/gamegogy/courselist", method=RequestMethod.GET)
	public String[] getCourseList(){
		return reader.getCourseIDStrings();
	}
	
	@RequestMapping(value="/gamegogy/student/{id}", method=RequestMethod.GET)
	public Student getStudentInfo(@PathVariable String id){
		return reader.getStudent(id);
	}
	
	@RequestMapping(value="/gamegogy/course/{id}", method=RequestMethod.GET)
	public Course getCourseInfo(@PathVariable String id){
		return reader.getCourse(id);
	}
	

}