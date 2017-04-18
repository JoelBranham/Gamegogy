package edu.jsu.mcis.gamegogy;

import java.io.*;
import java.util.*;
import java.net.*;

public class Database{
    
	private Map<String, Student> studentMap;
	private Map<String, Course> courseMap;
	
	public Database(DataReader reader) throws CourseException{
		studentMap = new HashMap<String, Student>();
		courseMap = new HashMap<String, Course>();
		
		for (Student s: reader.getStudentList()){
			studentMap.put(s.getId(), s);
		}
		for (Course c: reader.getCourseList()){
			courseMap.put(c.getId(), c);
		}
	}
     
    public Course getCourse(String id){
		if (courseMap.containsKey(id)){
			return courseMap.get(id);
		}
		throw new CourseException();
	}
	
	public Student getStudent(String id){
		if (studentMap.containsKey(id)){
			return studentMap.get(id);
		}
		throw new StudentException();
	}
	
	public List<String> getCourseList(){ 
		ArrayList<String> sortedCourse = new ArrayList<>(courseMap.keySet());
		Collections.sort(sortedCourse);
		return sortedCourse;
	}
	
	public List<String> getStudentList(){  
		ArrayList<String> sortedStudent = new ArrayList<>(studentMap.keySet());
		Collections.sort(sortedStudent);
		return sortedStudent;
	}

}