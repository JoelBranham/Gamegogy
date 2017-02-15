package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class StudentTest{
	
	private Student student1;
	
	@Before
	public void setup(){
		student1 = new Student(123, "first", "last", "first.last@email.com");
	}
	
	public void testStudentisInitalizedProperly(){
		assertEquals(123, student1.getID();
		assertEquals("first", student1.getFname());
		assertEquals("last", student1.getLname());
		assertEquals("first.last@email.com", student1.getEmail());
	}
}