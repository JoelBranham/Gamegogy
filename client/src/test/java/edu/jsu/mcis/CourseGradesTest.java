package edu.jsu.mcis.gamegogy;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class CourseGradesTest{
	private CourseGrades c;
	@Before
	public void setup(){
		c = new CourseGrades();
	}
	
	@Test
	public void testConstructor(){
		assertEquals(0, c.getColHeaders().size());
		assertEquals(0, c.getRowHeaders().size());
		assertEquals(0, c.getData().size());
	}
}