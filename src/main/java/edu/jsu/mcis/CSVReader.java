package edu.jsu.mcis;

import java.io.*;
import java.util.*;

public class CSVReader implements DataReader{
	
	private List<Student> studentList;
	private List<Course> courseList;
	
	public CSVReader(){
		studentList = new ArrayList<Student>();
		courseList = new ArrayList<Course>();

		buildStudent("src\\main\\resources\\students.csv");
		buildCourse("src\\main\\resources\\courses.csv");
		addCourseInfo("src\\main\\resources\\courses");
		
	}
	public CSVReader(String courseFileName, String courseFolderName, String studentFileName){
		studentList = new ArrayList<Student>();
		courseList = new ArrayList<Course>();
		buildCourse(courseFileName);
		addCourseInfo(courseFolderName);
		buildStudent(studentFileName);
    
	}
	
	private void buildCourse(String fileName){
  		String myline;
    	try{
			BufferedReader in = new BufferedReader(new FileReader(new File(fileName)));
            myline = in.readLine();
            while ((myline = in.readLine())!=null){
				String[] course = myline.split(",");
				String id = (course[0].substring(1,course[0].length()-1));
				String year = (course[2].substring(1,course[2].length()-1));
				String size = (course[3].substring(1,course[3].length()-1));
				String term = course[1].substring(1, course[1].length()-1);
				courseList.add(new Course(id, year, size, term));	
            }			
        }
        catch(IOException e) {e.printStackTrace();}		
    }
	
	private void addCourseInfo(String folderName){
		final File folder = new File(folderName);
		for (final File fileEntry : folder.listFiles()){
			String courseID = (fileEntry.getName().substring(0,fileEntry.getName().length()-4));
			try{
				Course c = getCourse(courseID);
				BufferedReader in = new BufferedReader(new FileReader(fileEntry));
				String[] assignments = in.readLine().split(",");
				List<String> lines = new ArrayList<String>();
				String myline;
				while ((myline = in.readLine())!=null){
					lines.add(myline);
				}
				for (int i = 1; i < assignments.length; i++){
					String assignmentName = assignments[i].substring(1, assignments[i].length()-1);
					Assignment a = new Assignment(assignmentName);
					for (int j = 0; j < lines.size(); j++){
						String[] row = lines.get(j).split(",");
						String studentId = (row[0].substring(1,row[0].length()-1));
						int score = Integer.parseInt(row[i].substring(1,row[i].length()-1));
						a.addStudentAndScore(studentId, score);
					}
					c.addAssignment(a);
				}
				for (int i = 0; i < courseList.size(); i++){
					if (courseList.get(i).getId() == courseID){
						courseList.remove(i);
						courseList.add(c);
					} 
				}
			}
			catch(IOException e) {e.printStackTrace();}
		}
	}
	
	private void buildStudent(String fileName){
    	String myline;
        try{
            BufferedReader in = new BufferedReader(new FileReader(new File(fileName)));
			in.readLine();
            while ((myline = in.readLine())!=null){
				String[] student = myline.split(",");
				String id = (student[0].substring(1,student[0].length()-1));
				String first = student[1].substring(1, student[1].length() - 1);
				String last = student[2].substring(1, student[2].length() - 1);
				String email = student[3].substring(1, student[3].length() - 1);
				studentList.add(new Student(id, first, last, email));
            }
        }
        catch(IOException e) {e.printStackTrace();}
    }
	
	 private Course getCourse(String id){
		for (Course c: courseList){
			if (c.getId().equals(id)){
				return c;
			}
		}
		throw new CourseException();
    }
	
	public List<Course> getCourseList(){
		return courseList;
	}
	
	public List<Student> getStudentList(){  
		return studentList;
	}
	
}