package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class CourseTest{
	
	private Course course1;
	
	@Before
	public void setup(){
		course1 = new Student(123, 1942, 23, "Spring");
	}
	
	public void testStudentisInitalizedProperly(){
		assertEquals(123, course1.getID());

	}
}