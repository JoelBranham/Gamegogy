package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class DatabaseTest{
	
	private Database d;
	
	@Before
	public void setup()
	{
		d = new Database();
		d.buildCourse("src\\test\\resources\\coursestest.csv");
		d.buildStudent("src\\test\\resources\\studentstest.csv");
	}
	
	@Test
	public void testCourses()
	{
		Course correctCourse1 = new Course(99000 , 2013, 11, "Spring");
		Course check = d.getCourse(99000);
		assertEquals(correctCourse1.getId(), check.getId());
		assertEquals(correctCourse1.getYear(), check.getYear());
		assertEquals(correctCourse1.getSize(), check.getSize());
		assertEquals(correctCourse1.getTerm(), check.getTerm());
	}
	
	@Test
	public void testStudents()
	{
		Student correctStudent = new Student(420000, "Snoop", "Dogg", "SnoopDogg");
		Student check = d.getStudent(420000);
		assertEquals(correctStudent.getId(), check.getId());
		assertEquals(correctStudent.getFname(), check.getFname());
		assertEquals(correctStudent.getLname(), check.getLname());
		assertEquals(correctStudent.getEmail(), check.getEmail());
	}
	
	@Test
	public void testInvalidKeyThrowsStudentException()
	{
		boolean thrown = false;
		try{
			d.getStudent(01);
		}
		catch(StudentException s){
			thrown = true;
		}
		
		assertTrue(thrown);
	}
	
	@Test
	public void testInvalidKeyThrowsCourseException()
	{
		boolean thrown = false;
		try{
			d.getCourse(02);
		}
		catch(CourseException c){
			thrown = true;
		}
		assertTrue(thrown);
	}
	
	@Test
	public void testAddCourseInfo1()
	{
		d.addCourseInfo("src\\test\\resources\\coursestest");
		Course course1 = d.getCourse(99000);
		assertEquals("Spring", course1.getTerm());
		assertEquals(2013, course1.getYear());
		assertEquals(11, course1.getSize());
		
		Assignment total = course1.getAssignment("Total");
		assertEquals(111318, total.getTopStudentID());
		assertEquals(925, total.getTopScore());
		
		Assignment assignment1 = course1.getAssignment("Assignment 1");
		assertEquals(111318, assignment1.getTopStudentID());
		assertEquals(65, assignment1.getTopScore());
		
		Assignment assignment2 = course1.getAssignment("Assignment 2");
		assertEquals(111318, assignment2.getTopStudentID());
		assertEquals(58, assignment2.getTopScore());
		
		Assignment assignment3 = course1.getAssignment("Assignment 3");
		assertEquals(111383, assignment3.getTopStudentID());
		assertEquals(59, assignment3.getTopScore());
	}
	
	@Test
	public void testAddCourseInfo2()
	{
		d.addCourseInfo("src\\test\\resources\\coursestest");
		Course course2 = d.getCourse(99001);
		assertEquals("Fall", course2.getTerm());
		assertEquals(1944, course2.getYear());
		assertEquals(10, course2.getSize());
		
		Assignment total = course2.getAssignment("Total");
		assertEquals(111143, total.getTopStudentID());
		assertEquals(886, total.getTopScore());
	}
	
}