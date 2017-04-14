package edu.jsu.mcis;

import java.util.*;

public class CourseGrades{
	
	private List<String> colHeaders;
	private List<String> rowHeaders;
	private List<List<Float>> data;
	
	public CourseGrades(){
		colHeaders = new ArrayList<String>();
		rowHeaders = new ArrayList<String>();
		data = new ArrayList<List<Float>>();
	}

	public void addAssignment(String assignment){
		colHeaders.add(assignment);
	}
	
	public void addData(List<Float> scores){
		data.add(scores);
	}
	
	public void setStudents(List<String> studentIds){
		this.rowHeaders = studentIds;
	}
	
	public List<String> getColHeaders(){
		return colHeaders;
	}
	
	public List<String> getRowHeaders(){
		return rowHeaders;
	}
	
	public List<List<Float>> getData(){
		return data;
	}
}