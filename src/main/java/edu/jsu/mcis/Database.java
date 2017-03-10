package edu.jsu.mcis;

import java.io.*;
import java.util.*;
import java.net.*;

public class Database{
    
	private HashMap<String, Course> courseMap; 
	private HashMap<String, Student> studentMap; 
	private WebService service;
	private boolean online;
	
    public Database(){
    	this(false,null);
    }
    
    public Database(boolean online, WebService service){
		try{
			this.online = online;
			this.service = service;
			courseMap = new HashMap<String, Course>();
			studentMap = new HashMap<String, Student>();
	
			if(online){
				buildOnlineCourse();
				addOnlineCourseInfo();
				buildOnlineStudent();
			}
			else{
				buildCourse("src\\main\\resources\\courses.csv");
				addCourseInfo("src\\main\\resources\\courses");
				buildStudent("src\\main\\resources\\students.csv");
			}
		}
		catch(MalformedURLException ex){ex.printStackTrace();}
		catch(IOException e){e.printStackTrace();}
    }
	
    public Database(String courseFileName, String courseFolderName, String studentFileName){
		courseMap = new HashMap<String, Course>();
		studentMap = new HashMap<String, Student>();
		buildCourse(courseFileName);
		addCourseInfo(courseFolderName);
		buildStudent(studentFileName);
    }
	
    private void buildCourse(String fileName){
  		String myline;
    	try{
			BufferedReader in = null;
			in = new BufferedReader(new FileReader(new File(fileName)));

            myline = in.readLine();
            while ((myline = in.readLine())!=null){
				String[] course = myline.split(",");
				String id = (course[0].substring(1,course[0].length()-1));
				String year = (course[2].substring(1,course[2].length()-1));
				String size = (course[3].substring(1,course[3].length()-1));
				String term = course[1].substring(1, course[1].length()-1);
				courseMap.put(id, new Course(id, year, size, term));		
            }			
        }
        catch(IOException e) {e.printStackTrace();}		
    }
	
	private void buildOnlineCourse(){
  		String myline;
    	try{
			BufferedReader in = null;
			in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(service.getCourseListCSV().getBytes())));
            myline = in.readLine();
            while ((myline = in.readLine())!=null){
				String[] course = myline.split(",");
				String id = (course[0].substring(1,course[0].length()-1));
				String year = (course[2].substring(1,course[2].length()-1));
				String size = (course[3].substring(1,course[3].length()-1));
				String term = course[1].substring(1, course[1].length()-1);
				courseMap.put(id, new Course(id, year, size, term));		
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
				ArrayList<String> lines = new ArrayList<String>();
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
				courseMap.remove(courseID);
				courseMap.put(courseID, c);
			}
			catch(IOException e) {e.printStackTrace();}
		}
	}
	
	private void addOnlineCourseInfo() throws MalformedURLException, IOException{
		for (String s: service.getCourseList()){
			String courseID = s.substring(1,s.length()-1);
			try{
				Course c = getCourse(courseID);
				int courseIDNum = Integer.parseInt(courseID);
				BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(service.getDetailedCourseInfo(courseIDNum).getBytes())));
				String[] assignments = in.readLine().split(",");
				ArrayList<String> lines = new ArrayList<String>();
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
				courseMap.remove(courseID);
				courseMap.put(courseID, c);
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
				studentMap.put(id, new Student(id, first, last, email));	
            }
        }
        catch(IOException e) {e.printStackTrace();}
    }
	
	private void buildOnlineStudent(){
    	String myline;
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(service.getStudentListCSV().getBytes())));
			String check = in.readLine();
            while ((myline = in.readLine())!=null){
				String[] student = myline.split(",");
				String id = (student[0].substring(1,student[0].length()-1));
				String first = student[1].substring(1, student[1].length() - 1);
				String last = student[2].substring(1, student[2].length() - 1);
				String email = student[3].substring(1, student[3].length() - 1);
				studentMap.put(id, new Student(id, first, last, email));
            }
        }
        catch(IOException e) {e.printStackTrace();}
    }
  
    public Course getCourse(String id){
		if (courseMap.containsKey(id)){
			return courseMap.get(id);
		}
		throw new CourseException();
    }
	
    public Student getStudent(String id){
		if (studentMap.containsKey(id)){
			return studentMap.get(id);
		}
		throw new StudentException();
    }
	
	@SuppressWarnings("unchecked")
	public ArrayList<String> getCourseList(){
		ArrayList<String> sortedCourse = new ArrayList(courseMap.keySet());
		Collections.sort(sortedCourse);
		return sortedCourse;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<String> getStudentList(){
		ArrayList<String> sortedStudent = new ArrayList(studentMap.keySet());
		Collections.sort(sortedStudent);
		return sortedStudent;
	}
	
	public boolean isOnline(){
		return online;
	}

}
