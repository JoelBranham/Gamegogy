package edu.jsu.mcis;

import java.util.*;

public class Course{

	private String id,year,size,term;
	private Map<String, Assignment> assignments;
	private List<String> assignmentList;

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
	
	public List<String> getAssignmentList(){
		return assignmentList;
	}
	
	public List<String> getScoresForStudent(String id){
		List<String> list = new ArrayList<>();
		for (int i = 0; i < assignmentList.size(); i++){
			Assignment a = getAssignment(assignmentList.get(i).substring(1,assignmentList.get(i).length() - 1));
			List<String> students = a.getStudents();
			List<Integer> scores = a.getScores();
			for (int j = 0; j < students.size(); j++){
				if (students.get(j).equals(id) || students.get(j).equals(id.substring(1,id.length()-1))){
					list.add(scores.get(j) + ".0");
				}
			}
		}
		return list;
	}
	
	public Assignment getAssignment(String name){ 
		if (assignments.containsKey(name)){
			return assignments.get(name);
		}
		throw new AssignmentException(); 
	}

}