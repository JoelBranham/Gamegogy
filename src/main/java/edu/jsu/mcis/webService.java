package edu.jsu.mcis;
import java.io.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
//import org.json.simple.*;
//import org.json.simple.parser.*;

public class webService {
    /*
    private URL url;
    private URLConnection con;
    private InputStream is;
    private String id;
    */
    public webService() 
    {
        try {
            //System.out.println(getStudent(111111));
            System.out.println(getCourse(99000));
            //System.out.println(getStudentList());
            //System.out.println(getCourseList());
        } catch (IOException ex) {
            Logger.getLogger(webService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public String getStudent(int id) throws MalformedURLException, IOException 
    {
        String ID = String.valueOf(id);
        String myLine = null;
        URL url = new URL("http://inspired.jsu.edu:7272/gamegogy/student/" + ID);
        URLConnection con = url.openConnection();
        InputStream is = con.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        
        String returnString = br.readLine();
        String studentID = returnString.split("id\":\"")[1].split("\"")[0];
        String studentFname = returnString.split("first\":\"")[1].split("\"")[0];
        String studentLname = returnString.split("last\":\"")[1].split("\"")[0];
        String studentEmail = returnString.split("email\":\"")[1].split("\"")[0];
        returnString = studentID + "," + studentFname + "," + studentLname + "," + studentEmail;
        return returnString;
    }
     public String getCourse(int id) throws MalformedURLException, IOException 
    {
        String ID = String.valueOf(id);
        String myLine = null;
        URL url = new URL("http://inspired.jsu.edu:7272/gamegogy/course/" + ID);
        URLConnection con = url.openConnection();
        InputStream is = con.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        return br.readLine();
    }
     public String getStudentList() throws MalformedURLException, IOException 
    {
        String myLine,list = null;
        URL url = new URL("http://inspired.jsu.edu:7272/gamegogy/studentlist/");
        URLConnection con = url.openConnection();
        InputStream is = con.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((myLine = br.readLine())!=null)
            list += myLine;
        
        return list;
    }
     public String getCourseList() throws MalformedURLException, IOException 
    {
        String myLine,line = null;
        URL url = new URL("http://inspired.jsu.edu:7272/gamegogy/courselist/");
        URLConnection con = url.openConnection();
        InputStream is = con.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        
        while ((myLine = br.readLine())!=null)
            line += myLine;
        return line;
    }
    
}
