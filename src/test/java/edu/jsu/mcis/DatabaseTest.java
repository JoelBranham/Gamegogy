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
	
}