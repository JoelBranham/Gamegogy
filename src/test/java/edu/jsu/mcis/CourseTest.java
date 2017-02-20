package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

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
		assertEquals(0, course1.getAssignments().size());
		assertEquals(123, course1.getId());
		assertEquals(1942, course1.getYear());
		assertEquals(23, course1.getSize());
		assertEquals("Spring", course1.getTerm());
	}
	
	@Test
	public void testStudentisInitializedProperly2(){
		assertEquals(0, course2.getAssignments().size());
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
	
	@Test
	public void testAddAssignment(){
		Assignment a = new Assignment();
		a.addStudentAndScore(1111, 100);
		a.addStudentAndScore(1234, 80);
		a.addStudentAndScore(4321, 99);
		course1.addAssignment(a);
		ArrayList<Assignment> assignments = course1.getAssignments();
		int id = assignments.get(0).getTopStudent();
		assertEquals(1111, id);
		int score = assignments.get(0).getTopScore();
		assertEquals(100, score);
	}
	
	@Test
	public void testAssignmentsAreCorrectlyAddedToAssignmentList(){
		Assignment a = new Assignment();
		a.addStudentAndScore(1234, 90);
		Assignment b = new Assignment();
		b.addStudentAndScore(2222, 92);
		Assignment c = new Assignment();
		c.addStudentAndScore(4321, 89);
		course1.addAssignment(a);
		course1.addAssignment(b);
		course1.addAssignment(c);
		Assignment aTest = course1.getAssignments().get(0);
		Assignment bTest = course1.getAssignments().get(1);
		Assignment cTest = course1.getAssignments().get(2);
		assertEquals(1234, aTest.getTopStudent());
		assertEquals(90, aTest.getTopScore());
		assertEquals(2222, bTest.getTopStudent());
		assertEquals(92, bTest.getTopScore());
		assertEquals(4321, cTest.getTopStudent());
		assertEquals(89, cTest.getTopScore());
	}
	
	
	
	

}