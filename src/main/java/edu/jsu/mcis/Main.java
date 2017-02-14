package edu.jsu.mcis;
import java.io.*;
import java.util.*;


import sun.java2d.pipe.BufferedRenderPipe;
  

public class Main {
    
    private static ArrayList<String> studentList;
    private static ArrayList<String> courseList;
    public static void main(String[] args) {
        studentList = new ArrayList<>();
        courseList = new ArrayList<>();
        populateStudents();
        populateCourses();
        
        
        
    }

    public static void populateStudents() {
        studentList = new ArrayList<>();
        String myline;
        
        try{
            BufferedReader in = new BufferedReader(new FileReader(new File("students.csv")));
            while ((myline = in.readLine())!=null)
            {
                studentList.add(myline);
            }
        }
        catch(IOException e) {e.printStackTrace();}

    }
    public static void populateCourses() {
        String myline;
        try{
            BufferedReader in = new BufferedReader(new FileReader(new File("courses.csv")));
            while ((myline = in.readLine())!=null)
            {
                courseList.add(myline);
            }
        }
        catch(IOException e)
        {e.printStackTrace();}

    }
    public ArrayList<String> getList(String nameOfList)
    {
        ArrayList<String> returnList = new ArrayList<>();
        if (nameOfList.equals("Students"))
            returnList = studentList;
        else if (nameOfList.equals("Courses"))
            returnList = courseList;
        
        return returnList;
    }
    public static void printList(String nameOfList)
    {
        ArrayList<String> printList = new ArrayList<>();
        if (nameOfList.equals("Students"))
            printList = studentList;
        else if (nameOfList.equals("Courses"))
            printList = courseList;
       
        for (int k=0;k<printList.size();k++)
            System.out.println(printList.get(k));
    }
    
    

}