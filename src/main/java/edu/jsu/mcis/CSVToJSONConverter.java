package edu.jsu.mcis;

import java.util.*;

public class CSVToJSONConverter{
/*
    private final long id;
    private final String content;

    public CSVToJSONConverter(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
	*/
//	private String nothing;
//	public CSVToJSONConverter(String content){
//		this.nothing = content;
//	}
	
	public static List<String> getCourseList(){
		List<String> list = new ArrayList<String>();
		list.add("99000");
		list.add("99001");
		return list;
	}
	public static List<String> getStudentList(){
		List<String> list = new ArrayList<String>();
		list.add("111111");
		list.add("111112");
		return list;
	}
	/*
	public static String getStudentInfo(String id){
		return id;
	}
	*/
	
}