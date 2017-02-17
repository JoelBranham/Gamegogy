package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class CourseTest{
	
	private Course course1;
	private Course course2;
	
	@Before
	public void setup()
	{
		course1 = new Course(123, 1942, 23, "Spring");
		course2 = new Course(1, 2010, 50, "Fall");
	}
	
	@Test
	public void testStudentisInitalizedProperly()
	{
		assertEquals(123, course1.getId());
		assertEquals(1942, course1.getYear());
		assertEquals(23, course1.getSize());
		assertEquals("Spring", course1.getTerm());
	}
	
	@Test
	public void testStudentisInitializedProperly2(){
		assertEquals(1, course2.getId());
		assertEquals(2010, course2.getYear());
		assertEquals(50, course2.getSize());
		assertEquals("Fall", course2.getTerm());
	}
	
	@Test
	public void testToString()
	{
		assertEquals("[123] Spring 1942 (23 students)", course1.toString());
		assertEquals("[1] Fall 2010 (50 students)", course2.toString());
	}

}