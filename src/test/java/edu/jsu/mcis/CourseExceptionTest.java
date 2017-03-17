package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class CourseExceptionTest{
	
	@Test
	public void testCoursetExceptionDoesThrowException(){
		boolean thrown = false;
		try{
			throw new CourseException();
		}
		catch(CourseException e){
			thrown = true;
		}
		assertTrue(thrown);
	}
	
}