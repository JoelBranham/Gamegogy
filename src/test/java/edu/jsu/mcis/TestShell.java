package edu.jsu.mcis;

import org.junit.*;

import com.sun.javafx.collections.ArrayListenerHelper;


import java.io.*;   
import static org.junit.Assert.*;
import java.util.*;


  
public class TestShell {
    List myFile;
    @Before
    public void setUp(){
        myFile = new List();
    }
    @Test
    public final void testStudentArrayisNotEmpty()
    {
        assertTrue(myFile.getStudentList().size()>0);
    }
    @Test
    public final void testCourseArrayisNotEmpty()
    {
        assertTrue(myFile.getCourseList().size()>0);
    }
	
}







