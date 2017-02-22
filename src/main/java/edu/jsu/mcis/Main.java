package edu.jsu.mcis;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args)  {
	try {
            GUI gui = new GUI();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        Database dataBase = new Database();
		
        if (args.length == 2){
			if(args[0].equals("course"))
			{
				System.out.println(dataBase.getCourse(Integer.parseInt(args[1])));
			} 
			else if(args[0].equals("student"))
			{
				System.out.println(dataBase.getStudent(Integer.parseInt(args[1])).toString());
			}
			else{
				System.out.println("");
			}
		}
		else if (args.length == 1){
			
				if (args[0].equals("courseids"))
				{
					System.out.println(dataBase.getCourseIds());
				}
				else if (args[0].equals("studentids"))
				{
					System.out.println(dataBase.getStudentIds());
				}
			
		}
		else{
			System.out.println("");
			//System.exit(0);
		}
    }
	
}

