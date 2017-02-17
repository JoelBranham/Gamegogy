package edu.jsu.mcis;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Database dataBase = new Database();
        if (args.length == 2){
			if(args[0].equals("Course"))
			{
				System.out.println(dataBase.getCourse(Integer.parseInt(args[1])));
			} 
			else if(args[0].equals("Student"))
			{
				System.out.println(dataBase.getStudent(Integer.parseInt(args[1])).toString());
			}
			else{
				System.out.println("");
			}
		}
		else{
			System.out.println("");
			//System.exit(0);
		}
    }
}

