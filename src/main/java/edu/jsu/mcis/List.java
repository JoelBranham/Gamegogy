package edu.jsu.mcis;
import java.io.*;
import java.util.*;


//import sun.java2d.pipe.BufferedRenderPipe;
  
public class List{
    public class Students{
        private String fName,lName,email;
        private int id;
        public Students(int i,String fN,String lN, String e)
        {
            this.id = i;
            this.fName = fN;
            this.lName = lN;
            this.email = e;
        }
        public int getId()
        {
            return this.id;
        }
        public String getFname()
        {
            return this.fName;
        }
        public String getLname()
        {
            return this.lName;
        }
        public String getEmail()
        {
            return this.email;
        }
    }
    public class Courses{
        private int id,year,size;
        private String term;
        public Courses(int i,int y,int s, String t)
        {
            this.id = i;
            this.year = y;
            this.size = s;
            this.term = t;
        }
        public int getId()
        {
            return this.id;
        }
        public int getYear()
        {
            return this.year;
        }
        public int getSize()
        {
            return this.size;
        }
        public String getTerm()
        {
            return this.term;
        }
    }
    public ArrayList<Students> studentList = new ArrayList<>();
    public ArrayList<Courses> courseList = new ArrayList<>();
    
    public List()
    {
        buildList();
        printStudents();
        //printCourses();
    }
    
    private void buildList()
    {
        String myline;
        try{
            BufferedReader in = new BufferedReader(new FileReader(new File("students.csv")));
            while ((myline = in.readLine())!=null)
            {
                if (myline.split(",")[0].length()<8)
                    myline = in.readLine();
               Students student = new Students(
                       Integer.parseInt(myline.split(",")[0].substring(1,5)),
                       myline.split(",")[1],myline.split(",")[2],myline.split(",")[3]);
               
               studentList.add(student);
            }
        }
        catch(IOException e) {e.printStackTrace();}
        //////////////////////////////////////////////////////////
        try{
            BufferedReader in2 = new BufferedReader(new FileReader(new File("courses.csv")));
            myline = in2.readLine();
            while ((myline = in2.readLine())!=null)
            {
                Courses course = new Courses(
                        Integer.parseInt(myline.split(",")[0].substring(1,myline.split(",")[0].length()-1)),
                        Integer.parseInt(myline.split(",")[2].substring(1,myline.split(",")[2].length()-1)),
                        Integer.parseInt(myline.split(",")[3].substring(1,myline.split(",")[3].length()-1)),
                        myline.split(",")[1]);
                courseList.add(course);
                
            }
        }
        catch(IOException e)
        {e.printStackTrace();}
        
    }
   
    public void printStudents()
    {
        for (int k=0;k<studentList.size();k++)
        {
            {System.out.println(studentList.get(k).getId() +"\t" +
                    studentList.get(k).getFname() + "\t" +
                    studentList.get(k).getLname() + "\t" +
                    studentList.get(k).getEmail());}
        }
    }
    public void printCourses()
    {
        for (int k=0;k<courseList.size();k++)
        {
            if (courseList.get(k).getTerm().length()<7)
            {
                System.out.println(courseList.get(k).getId() +"\t" +
                    courseList.get(k).getTerm() + "\t\t" +
                    courseList.get(k).getYear() + "\t" +
                    courseList.get(k).getSize());
            }
            else
                System.out.println(courseList.get(k).getId() +"\t" +
                    courseList.get(k).getTerm() + "\t" +
                    courseList.get(k).getYear() + "\t" +
                    courseList.get(k).getSize());
        }
    }
    public ArrayList<Students> getStudentList()
    {
        return studentList;
    }
    public ArrayList<Courses> getCourseList()
    {
        return courseList;
    }
}