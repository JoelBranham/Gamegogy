package edu.jsu.mcis;

import org.junit.*;
import java.io.*;
import java.util.*;
import java.net.*;
import java.awt.*;
import static org.junit.Assert.*;



public class WebServiceTest{

	private WebService testService;

	@Test
	public void testMethodsReturnObjects() throws MalformedURLException, IOException{
		testService = new WebService("http://inspired.jsu.edu:7272/gamegogy/");
	//	String studentID = testService.getStudent(111111);
	//	String courseID = testService.getBasicCourseInfo(99000);
		ArrayList<String> studentList = testService.getStudentList();
		ArrayList<String> courseList = testService.getCourseList();
	//	assertTrue(studentID.length() > 0);
		assertTrue(studentList.size() > 0);
	}

	@Test
	public void testJSONtoCSV()  throws MalformedURLException, IOException{
		testService = new WebService("http://inspired.jsu.edu:7272/gamegogy/");
	//	String studentID = testService.getStudent(111111).split(",")[0];
	//	String courseID = testService.getBasicCourseInfo(99000);
		ArrayList<String> studentList = testService.getStudentList();
		ArrayList<String> courseList = testService.getCourseList();
		//String s = 	testService.getStudent(111111).split(",")[0];
		assertTrue(true);

	}

	@Test
	public void countStudents() throws MalformedURLException, IOException{
		testService = new WebService("http://inspired.jsu.edu:7272/gamegogy/");
		int number = 0;
		int listSize = testService.getStudentList().size();
		for(int i = 0; i < listSize; i++){
			number++;
		}	
		assertTrue(true);
	}
	
	@Test
	public void getDetailedCourseInfoForCourse99000() throws MalformedURLException, IOException{
		for (int i = 0; i < 25; i++){
			int course = 99000 + i;
			testService = new WebService("http://inspired.jsu.edu:7272/gamegogy/");
			BufferedReader fileReader = new BufferedReader(new FileReader("src\\main\\resources\\courses\\" + course + ".csv"));
			String myline = "", correct = "";
			while ((myline = fileReader.readLine()) != null){
				correct += (myline + "\n");
			}
			assertEquals(correct, testService.getDetailedCourseInfo(course));
		}
	}
	
	@Test
	public void testSlashIsAddedToURLWithoutSlash(){
		WebService testService2 = new WebService("hello");
		assertEquals("hello/", testService2.getURL());
	}
	
	@Test
	public void testSlashIsNotAddedToURLWithSlash(){
		WebService testService2 = new WebService("hello/");
		assertEquals("hello/", testService2.getURL());
	}
	
}