package edu.jsu.mcis.gamegogy;

import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.net.MalformedURLException;

public class Main {
	
    public static void main(String[] args) throws IOException {
		
        Database database = new Database(new CSVReader());
		JFrame win = null;
		
        if (args.length == 2){
			if(args[0].equals("course")){
				System.out.println(database.getCourse(args[1]));
			} 
			else if(args[0].equals("student")){
				System.out.println(database.getStudent(args[1]));
			}
		}
		else if (args.length == 1){
			if (args[0].equals("courseids")){
				System.out.println(listToString(database.getCourseList()));
			}
			else if (args[0].equals("studentids")){
				System.out.println(listToString(database.getStudentList()));
			}
			else{
				win = new GUI(args[0]);
			}
		}
		else if (args.length == 0){
			win = new GUI();
		}
		if (win != null){
			win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			win.setSize(new Dimension(500,700));
			win.setResizable(false);
			win.setVisible(true);
		}
    }
	
	public static String listToString(List<String> list){
		String string = "";
		for (String s: list){
			string += (s + "\n");
		}
		return string;
	}
	
}

