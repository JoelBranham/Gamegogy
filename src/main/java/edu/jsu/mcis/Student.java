package edu.jsu.mcis;

public class Student{
	
	private String fName,lName,email;
	private int id;
	
	public Student(int i,String fN,String lN, String e){
		id = i;
		fName = fN;
		lName = lN;
		email = e;
	}
	
	public int getId(){
		return id;
	}
	
	public String getFname(){
		return fName;
	}
	
	public String getLname(){
		return lName;
	}
	
	public String getEmail(){
		return email;
	}
	
	public String toString(){
		return "[" + id + "] " + fName + " " + lName + " " + email + "@jsu.edu";
	}
	
}