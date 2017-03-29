package edu.jsu.mcis;

import java.util.*;

public class Course{

	private String id,year,size,term;
	private Map<String, Assignment> assignments;
	private ArrayList<String> assignmentList;

	public Course(String i,String y,String s, String t){
		assignments = new HashMap<String, Assignment>();
		assignmentList = new ArrayList<String>();
		id = i;
		year = y;
		size = s;
		term = t;
	}
	
	public String getId(){
		return id;
	}
	
	public String getYear(){
		return year;
	}
	
	public String getSize(){
		return size;
	}
	
	public String getTerm(){
		return term;
	}
	
	public String toString(){
		return "[" + id + "] " + term + " " + year + " (" + size + " students)";
	}
	
	public void addAssignment(Assignment a){
		assignments.put(a.getName(), a);
		assignmentList.add(a.getName());
	}
	
	public ArrayList<String> getAssignmentList(){
		return assignmentList;
	}
	
	public Assignment getAssignment(String name){ 
		if (assignments.containsKey(name)){
			return assignments.get(name);
		}
		throw new AssignmentException(); 
	}

}