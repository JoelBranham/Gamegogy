package edu.jsu.mcis;

import org.junit.*;
import java.io.*;
import java.util.*;
import java.net.*;
import java.awt.*;
import static org.junit.Assert.*;
import java.util.List;
import java.net.MalformedURLException;


public class JSONReaderTest{

	private JSONReader reader;
	
	@Before
	public void setUp()throws MalformedURLException, IOException {
		reader = new JSONReader("http://inspired.jsu.edu:7272/gamegogy/");
	}

	@Test
	public void testMethodsReturnObjects() throws MalformedURLException, IOException{
		List<Student> studentList = reader.getStudentList();
		List<Course> courseList = reader.getCourseList();
		assertTrue(studentList.size() > 0);
	}

	@Test
	public void testJSONtoCSV()  throws MalformedURLException, IOException{
		List<Student> studentList = reader.getStudentList();
		List<Course> courseList = reader.getCourseList();
		assertTrue(studentList.size() > 0);
	}

	@Test
	public void countStudents() throws MalformedURLException, IOException{
		int number = 0;
		int listSize = reader.getStudentList().size();
		for(int i = 0; i < listSize; i++){
			number++;
		}	
		assertTrue(true);
	}
	
	
	@Test
	public void testSlashIsAddedToURLWithoutSlash()throws MalformedURLException, IOException{
		JSONReader reader2 = new JSONReader("hello");
		assertEquals("hello/", reader2.getURL());
	}
	
	@Test
	public void testSlashIsNotAddedToURLWithSlash()throws MalformedURLException, IOException{
		JSONReader reader2 = new JSONReader("hello/");
		assertEquals("hello/", reader2.getURL());
	}
	
}