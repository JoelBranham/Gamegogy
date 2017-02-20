package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;

public class AssignmentTest{
	
	private Assignment a;
	
	@Before
	public void setup(){
		a = new Assignment();
	}
	
	@Test
	public void testAddStudentAndScore(){
		a.addStudentAndScore(1111, 100);
		assertEquals(a.getTopStudent(), 1111);
		assertEquals(a.getTopScore(), 100);
	}
	
	@Test
	public void testCorrectTopStudentAndScore(){
		a.addStudentAndScore(1111, 100);
		a.addStudentAndScore(1234, 99);
		a.addStudentAndScore(4321, 30);
		assertEquals(a.getTopStudent(), 1111);
		assertEquals(a.getTopScore(), 100);
	}
	
	//test when student and score empty (throw exception?)
	
	
	
}