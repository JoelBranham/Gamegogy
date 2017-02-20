package edu.jsu.mcis;

import java.util.*;

public class Course{

	private int id,year,size;
	private String term;
	private ArrayList<Assignment> assignments;

	public Course(int i,int y,int s, String t)
	{
		assignments = new ArrayList<>();
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
		assignments.add(a);
	}
	
	public ArrayList<Assignment> getAssignments()
	{
		return assignments;
	}
	
}