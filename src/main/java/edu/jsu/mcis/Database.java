package edu.jsu.mcis;

import java.io.*;
import java.util.*;

  
public class Database{
    
	private Map<Integer, Course> courseMap;
	private Map<Integer, Student> studentMap; 
	private ArrayList <String> courseIds = new ArrayList<String>();
	private ArrayList <String> studentIds = new ArrayList<String>();
    
    public Database()
    {
        buildCourse("src\\main\\resources\\courses.csv");
		addCourseInfo("src\\main\\resources\\courses");
        buildStudent("src\\main\\resources\\students.csv");
    }
    
    public void buildCourse(String fileName)
    {
		courseMap = new HashMap<Integer, Course>();
  		String myline;
    	try{
        	BufferedReader in = new BufferedReader(new FileReader(new File(fileName)));
            myline = in.readLine();
            while ((myline = in.readLine())!=null)
            {
				String[] course = myline.split(",");
				int id = Integer.parseInt(course[0].substring(1,course[0].length()-1));
				int year = Integer.parseInt(course[2].substring(1,course[2].length()-1));
				int size = Integer.parseInt(course[3].substring(1,course[3].length()-1));
				String term = course[1].substring(1, course[1].length()-1);
				courseMap.put(id, new Course(id, year, size, term));
				
				String ids = String.valueOf(id);
				courseIds.add(ids);
				
            }
        }
        catch(IOException e) {e.printStackTrace();}
		
    }
	
	public void addCourseInfo(String folderName)
	{
		final File folder = new File(folderName);
		for (final File fileEntry : folder.listFiles()) {
			int courseID = Integer.parseInt(fileEntry.getName().substring(0,fileEntry.getName().length()-4));
			try{
				Course c = getCourse(courseID);
				BufferedReader in = new BufferedReader(new FileReader(fileEntry));
				String[] assignments = in.readLine().split(",");
				ArrayList<String> lines = new ArrayList<String>();
				String myline;
				while ((myline = in.readLine())!=null)
				{
					lines.add(myline);
				}
				for (int i = 1; i < assignments.length; i++){
					String assignmentName = assignments[i].substring(1, assignments[i].length()-1);
					Assignment a = new Assignment(assignmentName);
					for (int j = 0; j < lines.size(); j++){
						String[] row = lines.get(j).split(",");
						int studentId = Integer.parseInt(row[0].substring(1,row[0].length()-1));
						int score = Integer.parseInt(row[i].substring(1,row[i].length()-1));
						a.addStudentAndScore(studentId, score);
					}
					c.addAssignment(a);
				}
				courseMap.remove(courseID);
				courseMap.put(courseID, c);
			}
			catch(IOException e) {e.printStackTrace();}
		}
	}
	
    public void buildStudent(String fileName)
	{
		studentMap = new HashMap<Integer, Student>();
    	String myline;
        try{
            BufferedReader in = new BufferedReader(new FileReader(new File(fileName)));
			in.readLine();
            while ((myline = in.readLine())!=null)
            {
				String[] student = myline.split(",");
				int id = Integer.parseInt(student[0].substring(1,student[0].length()-1));
				String first = student[1].substring(1, student[1].length() - 1);
				String last = student[2].substring(1, student[2].length() - 1);
				String email = student[3].substring(1, student[3].length() - 1);
				studentMap.put(id, new Student(id, first, last, email));
				
				String ids = String.valueOf(id);
				studentIds.add(ids);
            }
        }
        catch(IOException e) {e.printStackTrace();}
    }
  
    public Course getCourse(int id)
    {
		if(courseMap.containsKey(id)){
			return courseMap.get(id);
		}
		throw new CourseException();
    }
	
    public Student getStudent(int id)
    {
		if(studentMap.containsKey(id)){
			return studentMap.get(id);
		}
		throw new StudentException();
    }
	
	public ArrayList<Integer> getCourses()
	{
		ArrayList<Integer> courseList = new ArrayList<>();
		courseList.addAll(courseMap.keySet());
		return courseList;		
	}
	
	public String getCourseIds()
	{
		String courses="";
		for (int k=0; k<courseIds.size(); k++){
			String course=(courseIds.get(k));
			courses = courses+course +"\n";	
		}
		return courses;
	}
	
	public String getStudentIds()
	{
		String students = "";
		for (int h=0; h<studentIds.size(); h++){
			String student=(studentIds.get(h));
			students = students+student + "\n";	
		}
		return students;
	}
}
