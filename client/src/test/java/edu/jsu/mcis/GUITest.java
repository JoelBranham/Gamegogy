package edu.jsu.mcis.gamegogy;

import org.junit.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import static org.junit.Assert.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;


public class GUITest{

	@Test
	public void testWindowOpensOnRuntime() throws MalformedURLException, IOException{
		
		Database testDatabase = new Database(new JSONReader("http://inspired.jsu.edu:7272/gamegogy/"));
		boolean windowOpened = false;

		try{
			JFrame testWindow = new GUI(testDatabase);
			testWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			testWindow.setSize(new Dimension(300,300));
			testWindow.setResizable(false);
			testWindow.setVisible(true);
			windowOpened = true;
		}
		catch(IOException e){}

		assertTrue(windowOpened);
	

}

@Test
public void testHelpUrlExsist()
{
	HttpURLConnection huc;
	int value = 0;
	try{	
		String address = "src\\main\\resources\\Gamegogy help\\Publication1.htm";
	URL url = new URL(address);
	huc =  (HttpURLConnection)  url.openConnection();
    huc.setRequestMethod("GET"); 
    huc.connect(); 
    value =  huc.getResponseCode();


	assertEquals(HttpURLConnection.HTTP_OK, huc.getResponseCode());
	}
	catch (Exception e)
	{

	}
	 	
}	
	
	
}
