package edu.jsu.mcis;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.net.MalformedURLException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class Main {
	
    public static void main(String[] args) throws MalformedURLException, IOException {
		
        Database database = new Database(new CSVReader());
		boolean useGUI = false;
		
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
				SpringApplicationBuilder builder = new SpringApplicationBuilder(Main.class);
				builder.headless(false);
				ConfigurableApplicationContext context = builder.run(args);
				database = new Database(new JSONReader("http://localhost:8080/gamegogy/"));
				useGUI = true;
			}
		}
		else if (args.length == 0){
			useGUI = true;
		}
		if(useGUI){
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
	
	public static String listToString(List<String> list){
		String string = "";
		for (String s: list){
			string += (s + "\n");
		}
		return string;
	}
	
}

