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
		course1 = new Course("123", "1942", "23", "Spring");
		course2 = new Course("1", "2010", "50", "Fall");
	}
	
	@Test
	public void testCourseisInitalizedProperly()
	{
		assertEquals(0, course1.getAssignmentList().size());
		assertEquals("123", course1.getId());
		assertEquals("1942", course1.getYear());
		assertEquals("23", course1.getSize());
		assertEquals("Spring", course1.getTerm());
	}
	
	@Test
	public void testCourseisInitializedProperly2(){
		assertEquals(0, course2.getAssignmentList().size());
		assertEquals("1", course2.getId());
		assertEquals("2010", course2.getYear());
		assertEquals("50", course2.getSize());
		assertEquals("Fall", course2.getTerm());
	}
	
	@Test
	public void testToString()
	{
		assertEquals("[123] Spring 1942 (23 students)", course1.toString());
		assertEquals("[1] Fall 2010 (50 students)", course2.toString());
	}
	
	@Test
	public void testAddAssignment()
	{
		Assignment a = new Assignment("a");
		a.addStudentAndScore("1111", 100);
		a.addStudentAndScore("1234", 80);
		a.addStudentAndScore("4321", 99);
		course1.addAssignment(a);
		String id = course1.getAssignment("a").getTopStudentID();
		assertEquals("1111", id);
		int score = course1.getAssignment("a").getTopScore();
		assertEquals(100, score);
	}
	
	@Test
	public void testAssignmentsAreCorrectlyAddedToAssignmentList()
	{
		Assignment a = new Assignment("a");
		a.addStudentAndScore("1234", 90);
		Assignment b = new Assignment("b");
		b.addStudentAndScore("2222", 92);
		Assignment c = new Assignment("c");
		c.addStudentAndScore("4321", 89);
		course1.addAssignment(a);
		course1.addAssignment(b);
		course1.addAssignment(c);
		Assignment aTest = course1.getAssignment("a");
		Assignment bTest = course1.getAssignment("b");
		Assignment cTest = course1.getAssignment("c");
		assertEquals("1234", aTest.getTopStudentID());
		assertEquals("2222", bTest.getTopStudentID());
		assertEquals("4321", cTest.getTopStudentID());
		List<String> list = course1.getAssignmentList();
		for (int i = 0; i < list.size(); i++){
			String check = list.get(i);
			Assignment assignment = course1.getAssignment(check);
			assertEquals(assignment.getName(), check);
		}
	}
	
	@Test
	public void testGetAssignmentsReturnsArrayListofStrings()
	{
		Assignment a = new Assignment("a");
		a.addStudentAndScore("1234", 90);
		Assignment b = new Assignment("b");
		b.addStudentAndScore("2222", 92);
		Assignment c = new Assignment("c");
		c.addStudentAndScore("4321", 89);
		course1.addAssignment(a);
		course1.addAssignment(b);
		course1.addAssignment(c);
		List<String> assignments = course1.getAssignmentList();
		assertTrue(assignments.contains("a"));
		assertTrue(assignments.contains("b"));
		assertTrue(assignments.contains("c"));
		assertFalse(assignments.contains("d"));
	}
	
	@Test
	public void testGettingNonexistentAssignmentThrowsException()
	{
		boolean thrown = false;
		try{
			course1.getAssignment("Assignment X");
		}
		catch(AssignmentException a){
			thrown = true;
		}
		assertTrue(thrown);
	}
	
}