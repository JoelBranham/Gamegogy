package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class StudentTest{
	
	private Student student1;
	private Student student2;
	
	@Before
	public void setup()
	{
		student1 = new Student("123", "First", "Last", "First.Last");
		student2 = new Student("654321", "Jim", "Joe", "Jim.Joe");
	}
	
	@Test
	public void testStudentIsInitalizedProperly()
	{
		assertEquals("123", student1.getId());
		assertEquals("First", student1.getFname());
		assertEquals("Last", student1.getLname());
		assertEquals("First.Last", student1.getEmail());
	}
	
	@Test
	public void testStudentIsInitializedProperly2(){
		assertEquals("654321", student2.getId());
		assertEquals("Jim", student2.getFname());
		assertEquals("Joe", student2.getLname());
		assertEquals("Jim.Joe", student2.getEmail());
	}
	
	@Test
	public void testToString()
	{
		assertEquals("[123] First Last First.Last@jsu.edu", student1.toString());
		assertEquals("[654321] Jim Joe Jim.Joe@jsu.edu", student2.toString());
	}
	
}