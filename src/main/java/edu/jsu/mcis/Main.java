package edu.jsu.mcis;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;

public class Main {
	
    public static void main(String[] args) {
		
        Database database = new Database();
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
					System.out.println(Database.listToString(database.getCourseList()));
				}
				else if (args[0].equals("studentids")){
					System.out.println(Database.listToString(database.getStudentList()));
				}
		}
		else{
			try{
				JFrame win = new GUI(database);
				win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				win.setSize(new Dimension(500,700));
				win.setResizable(false);
				win.setVisible(true);
			}
			catch(IOException i){}
		}
		
    }
	
}

