package keywords;

import edu.jsu.mcis.*;
import java.io.*;
import java.net.MalformedURLException;

public class GamegogyKeywords {
	
	private String output;
	private ByteArrayOutputStream out;
	
	public void startGamegogyCLIWithArguments() throws MalformedURLException, IOException{
		out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		String[] args = {};
		Main.main(args);
		output = out.toString().trim();
	}
	
	public void startGamegogyCLIWithArguments(String first)throws MalformedURLException, IOException{
		out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		String[] args = new String[1];
		args[0] = first;
		Main.main(args);
		output = out.toString().trim();
	}

	public void startGamegogyCLIWithArguments(String first, String second)throws MalformedURLException, IOException{
		out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		String[] args = new String[2];
		args[0] = first;
		args[1] = second;
		Main.main(args);
		output = out.toString().trim();
	}
	
	public String getCommandLineOutput(){
		return output.trim();
	}
	
}