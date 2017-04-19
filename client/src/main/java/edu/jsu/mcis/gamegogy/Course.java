package edu.jsu.mcis.gamegogy;

import java.util.*;

public class Course{

	private String id,term,year;
	private int size;
	private Map<String, Assignment> assignment; 
	private CourseGrades grades;

	public Course(String i, String y, int s, String t){
		id = i;
		term = t;
		year = y;
		size = s;
		assignment = new HashMap<String, Assignment>();
		grades = new CourseGrades();
	}
	
	public void addAssignment(Assignment a){
		assignment.put(a.getName(), a);
		grades.addAssignment(a.getName());
		grades.setStudents(a.getUnsortedStudents());
	}

	public void addData(List<Float> list){
		grades.addData(list);
	}
	
	public String getId(){
		return id;
	}

	public String getTerm(){
		return term;
	}
	
	public String getYear(){
		return year;
	}
	
	public int getSize(){
		return size;
	}
	
	public String toString(){
		return "[" + id + "] " + term + " " + year + " (" + size + " students)";
	}
	
	public CourseGrades getGrades(){
		return grades;
	}
	
	public Assignment getAssignment(String name){ 
		if (assignment.containsKey(name)){
			return assignment.get(name);
		}
		throw new AssignmentException(); 
	}

}