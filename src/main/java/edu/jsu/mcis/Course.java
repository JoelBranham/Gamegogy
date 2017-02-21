package edu.jsu.mcis;

import java.util.*;

public class Course{

	private int id,year,size;
	private String term;
	private Map<String, Assignment> assignments;

	public Course(int i,int y,int s, String t)
	{
		assignments = new HashMap<String, Assignment>();
		id = i;
		year = y;
		size = s;
		term = t;
	}
	
	public int getId()
	{
		return id;
	}
	
	public int getYear()
	{
		return year;
	}
	
	public int getSize()
	{
		return size;
	}
	
	public String getTerm()
	{
		return term;
	}
	
	public String toString()
	{
		return "[" + id + "] " + term + " " + year + " (" + size + " students)";
	}
	
	public void addAssignment(Assignment a)
	{
		assignments.put(a.getName(), a);
	}
	
	public ArrayList<String> getAssignmentList()
	{
		ArrayList<String> assignmentList = new ArrayList<>();
		assignmentList.addAll(assignments.keySet());
		return assignmentList;
	}
	
	public Assignment getAssignment(String name){ 
		if (assignments.containsKey(name)){
			return assignments.get(name);
		}
		throw new AssignmentException(); 
	}

}