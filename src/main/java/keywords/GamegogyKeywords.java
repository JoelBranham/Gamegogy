package keywords;

import edu.jsu.mcis.*;
import java.io.*;

public class GamegogyKeywords {
	
	private String output;
	private ByteArrayOutputStream out;
	
	public GamegogyKeywords(){
		out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
	}
    
	public void startGamegogyCLIWithArguments(String first, String second){
		String[] args = new String[2];
		args[0] = first;
		args[1] = second;
		Main.main(args);
		output = out.toString().trim();
	}
	
	public String getCommandLineOutput(){
		return output.trim();
	}
	
	public void startGamegogyCLIWithArguments(){
		String[] args = {};
		Main.main(args);
		output = out.toString().trim();
	}
	
}