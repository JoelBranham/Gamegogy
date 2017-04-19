package edu.jsu.mcis.gamegogy;

import java.util.List;


public interface DataReader{

	public List<Course> getCourseList();
	
	public List<Student> getStudentList();

}