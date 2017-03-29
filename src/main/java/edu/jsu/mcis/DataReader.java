package edu.jsu.mcis;

import java.io.*;
import java.util.*;
import java.awt.*;
import java.util.List;


public interface DataReader{

	public List<Course> getCourseList();
	
	public List<Student> getStudentList();

}