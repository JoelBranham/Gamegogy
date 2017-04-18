package edu.jsu.mcis.gamegogy;

import org.junit.*;
import static org.junit.Assert.*;

public class StudentExceptionTest{
	
	@Test
	public void testStudentExceptionDoesThrowException(){
		boolean thrown = false;
		try{
			throw new StudentException();
		}
		catch(StudentException e){
			thrown = true;
		}
		assertTrue(thrown);
	}
	
}