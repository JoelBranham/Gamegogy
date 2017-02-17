package edu.jsu.mcis;

public class Course{

	private int id,year,size;
	private String term;

	public Course(int i,int y,int s, String t)
	{
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
	
}