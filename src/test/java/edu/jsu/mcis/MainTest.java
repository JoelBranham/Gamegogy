package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;

public class MainTest{

	private ByteArrayOutputStream out;
	private String[] validCourseArgs = {"Course", "99000"};
	private String[] invalidCourseArgs = {"Course", "00"};
	private String[] validStudentArgs = {"Student", "111111"};
	private String[] invalidStudentArgs = {"Student", "111500"};
	private String[] invalidFirstArg = {"Happy", "111111"};
	private String[] emptyArgs = {};
	private String[] oneArg = {"Course"};
	private String[] threeArgs = {"Student", "111111", "Course"};
	
	@Before
	public void setup(){
		out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
	}
	
	@Test
	public void testCorrectStudentArgsYieldCorrectOutput(){
		Main.main(validStudentArgs);
		assertEquals("[111111] Jerrod Shields jshields@jsu.edu", out.toString().trim());
	}

	@Test
	public void testInvalidStudentArgsThrowsStudentException(){
		boolean thrown = false;
		try{
				Main.main(invalidStudentArgs);
		}
		catch(StudentException s){
			thrown = true;
		}
		assertTrue(thrown);
	}

	@Test
	public void testCorrectCourseArgsYieldCorrectOutput(){
		Main.main(validCourseArgs);
		assertEquals("[99000] Spring 2013 (11 students)", out.toString().trim());
	}
	
	@Test
	public void testInvalidCourseArgsThrowsCourseException(){
		boolean thrown = false;
		try{
				Main.main(invalidCourseArgs);
		}
		catch(CourseException s){
			thrown = true;
		}
		assertTrue(thrown);
	}
	/*
	@Test
	public void testInvalidFirstArgumentCausesProgramToExit(){
		boolean thrown = false;
	//	try{
				Main.main(invalidFirstArg);
	//	}
	//	catch(ExitException e){
	//		thrown = true;
	//	}
		assertTrue(thrown);
	}
	
	@Test
	public void testEmptyArgsCausesProgramToExit(){
		boolean thrown = false;
	//	try{
				Main.main(emptyArgs);
	//	}
	//	catch(ExitException e){
	//		thrown = true;
	//	}
		assertTrue(thrown);
	}
	
	@Test
	public void testFewerThanTwoArgumentsCausesProgramToExit(){
		boolean thrown = false;
	//	try{
				Main.main(oneArg);
	//	}
	//	catch(ExitException e){
	//		thrown = true;
	//	}
		assertTrue(thrown);
	}

	@Test
	public void testThreeArgumentsCausesProgramToExit(){
		boolean thrown = false;
	//	try{
				Main.main(threeArgs);
	//	}
	//	catch(ExitException e){
	//		thrown = true;
	//	}
		assertTrue(thrown);
	}
	*/
	
}