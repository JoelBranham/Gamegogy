package edu.jsu.mcis.gamegogy;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class CSVReaderTest{
	private CSVReader reader;
	
	@Before
	public void setup(){
		reader = new CSVReader("src\\test\\resources\\coursestest.csv", "src\\test\\resources\\coursestest",
		"src\\test\\resources\\studentstest.csv");
	}
	
	@Test
	public void testConstructor(){
		assertEquals(3, reader.getCourseIDStrings().length);
		assertEquals(13, reader.getStudentIDStrings().length);
	}
	
	@Test
	public void testGetInvalidCourseThrowsException(){
		boolean thrown = false;
		try{
			reader.getCourse("111");
		}
		catch (CourseException c){
			thrown = true;
		}
		assertTrue(thrown);
	}
	
	@Test
	public void testGetStudent(){
		Student s = new Student("420000","Snoop","Dogg","SnoopDogg");
		Student s2 = null;
		try{
			s2 = reader.getStudent("420000");
		}
		catch(StudentException se){}
		assertEquals(s.getId(), s2.getId());
	}
	
	@Test
	public void testGetInvalidStudentThrowsException(){
		boolean thrown = false;
		try{
			Student s = reader.getStudent("123");
		}
		catch(StudentException se){thrown = true;}
		assertTrue(thrown);
	}
}