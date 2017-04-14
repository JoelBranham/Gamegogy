package edu.jsu.mcis;

public class Student{
	
	private String id,first,last,email;
	
	public Student(String i,String fN,String lN, String e){
		id = i;
		first = fN;
		last = lN;
		email = e;
	}
	
	public String getId(){
		return id;
	}
	
	public String getFirst(){
		return first;
	}
	
	public String getLast(){
		return last;
	}
	
	public String getEmail(){
		return email;
	}
	
	public String toString(){
		return "[" + id + "] " + first + " " + last + " " + email + "@jsu.edu";
	}
	
}