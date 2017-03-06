package edu.jsu.mcis;

import org.junit.*;
import java.io.*;
import java.util.*;
import java.net.*;
import java.awt.*;
import static org.junit.Assert.*;



public class webServiceTest{

	private webService testService = new webService();

	@Test
	public void testMethodsReturnObjects() throws MalformedURLException, IOException{

		String studentID = testService.getStudent(111111);
		String courseID = testService.getCourse(99000);
		String studentList = testService.getStudentList();
		String courseList = testService.getCourseList();

		assertTrue(studentID.length() > 0);
		assertTrue(courseID.length() > 0);
		assertTrue(studentList.length() > 0);
		assertTrue(courseList.length() > 0);

	}

	@Test
	public void testJSONtoCSV()  throws MalformedURLException, IOException{

		String studentID = testService.getStudent(111111).split(",")[0];
		String courseID = testService.getCourse(99000);
		String studentList = testService.getStudentList();
		String courseList = testService.getCourseList();
		String s = 	testService.getStudent(111111).split(",")[0];
		assertEquals("111111",studentID);
		assertTrue(true);

		//convert to CSV here

	}
}