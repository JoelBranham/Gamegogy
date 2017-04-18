package edu.jsu.mcis.gamegogy;

import org.junit.*;
import java.io.*;
import java.util.*;
import java.net.*;
import java.awt.*;
import static org.junit.Assert.*;
import java.util.List;
import java.net.MalformedURLException;


public class JSONReaderTest{

	private JSONReader reader;
	
	@Before
	public void setUp()throws MalformedURLException, IOException {
		/*
		String[] arr = new String[1];
		arr[0] = "http://localhost:8080/gamegogy/";
		Main.main(arr);
		reader = new JSONReader("http://localhost:8080/gamegogy/");
		*/
		reader = new JSONReader("http://inspired.jsu.edu:7272/gamegogy/");
	}
	
	@Test
	public void testGetURL()  throws MalformedURLException, IOException{
		assertEquals("http://inspired.jsu.edu:7272/gamegogy/", reader.getURL() );
	}
	
	@Test
	public void testCourseListSizeIsCorrect() throws MalformedURLException, IOException{
		assertEquals(25, reader.getCourseList().size());
	}

	@Test
	public void testStudentListSizeIsCorrect() throws MalformedURLException, IOException{
		assertEquals(300, reader.getStudentList().size());
	}
	
	@Test
	public void testFirstStudent() throws MalformedURLException, IOException{
		assertEquals("Jerrod", reader.getStudentList().get(0).getFirst());
	}
	
	@Test
	public void testGetCourseStringIsCorrect() throws MalformedURLException, IOException{
		assertEquals("99000", reader.getCourseString().get(0));
		assertEquals("99024", reader.getCourseString().get(24));
		assertEquals(25, reader.getCourseString().size());
	}
	
	@Test
	public void testCourseInfoLoadedCorrectly() throws MalformedURLException, IOException{
		Course check = reader.getCourseList().get(0);
		assertEquals("99000", check.getId());
		assertEquals("Spring", check.getTerm());
		assertEquals("2013", check.getYear());
		assertEquals(11, check.getSize());
		Assignment aCheck = check.getAssignment("Total");
		assertEquals("111318", aCheck.getTopStudentID());
		assertEquals(925, aCheck.getTopScore());
	}
	
	@Test
	public void testSlashIsAddedToURLWithoutSlash()throws MalformedURLException, IOException{
		JSONReader reader2 = new JSONReader("hello");
		assertEquals("hello/", reader2.getURL());
	}
	
	@Test
	public void testSlashIsNotAddedToURLWithSlash()throws MalformedURLException, IOException{
		JSONReader reader2 = new JSONReader("hello/");
		assertEquals("hello/", reader2.getURL());
	}
}