package edu.jsu.mcis.gamegogy;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;

public class AssignmentTest{
	
	private Assignment a;
	
	@Before
	public void setup(){
		a = new Assignment("a");
	}
	
	@Test
	public void testAssignmentInitializedProperly(){
		assertEquals("a",a.getName());
		boolean thrown = false;
		try{
			a.getTopStudentID();
		}
		catch(AssignmentException a){
			thrown = true;
		}
		assertTrue(thrown);
		thrown = false;
		try{
			a.getTopScore();
		}
		catch(AssignmentException a){
			thrown = true;
		}
		assertTrue(thrown);
	}
	
	@Test
	public void testAddStudentAndScore(){
		a.addStudentAndScore("1111", 100);
		assertEquals(a.getTopStudentID(), "1111");
		assertEquals(a.getTopScore(), 100);
	}
	
	@Test
	public void testCorrectTopStudentAndScore(){
		a.addStudentAndScore("1111", 100);
		a.addStudentAndScore("1234", 99);
		a.addStudentAndScore("4321", 30);
		assertEquals(a.getTopStudentID(), "1111");
		assertEquals(a.getTopScore(), 100);
	}

}