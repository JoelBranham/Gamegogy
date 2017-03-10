package edu.jsu.mcis;
import java.io.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class WebService {
	
	private String URLName;
	
    public WebService(String URLName) {
		if(URLName.charAt(URLName.length()-1) != '/'){
			URLName += "/";
		}
		this.URLName = URLName;
    }
	
    private String getStudent(int id) throws MalformedURLException, IOException{
        String ID = String.valueOf(id);
        String myLine = null;
        URL url = new URL(URLName + "student/" + ID);
        URLConnection con = url.openConnection();
        InputStream is = con.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        
        String returnString = br.readLine();
        String studentID = returnString.split("id\":\"")[1].split("\"")[0];
        String studentFname = returnString.split("first\":\"")[1].split("\"")[0];
        String studentLname = returnString.split("last\":\"")[1].split("\"")[0];
        String studentEmail = returnString.split("email\":\"")[1].split("\"")[0];
        returnString = "\"" + studentID + "\",\"" + studentFname + "\",\"" + studentLname + "\",\"" + studentEmail + "\"\n";
        return returnString;
    }
	
     private String getBasicCourseInfo(int id) throws MalformedURLException, IOException {

        URL url = new URL(URLName + "course/" + String.valueOf(id));
        URLConnection con = url.openConnection();
        InputStream is = con.getInputStream();
		StringBuffer jsonContents = new StringBuffer();
		
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while((line = reader.readLine()) != null) {
                jsonContents.append(line + '\n');
            }
        }
		catch(IOException i){}
		
		try{
			JSONParser parser = new JSONParser();
			JSONObject jobject = (JSONObject) parser.parse(jsonContents.toString());
	
			Object idObject = jobject.get("id");
			Object courseSize =  jobject.get("size");
			Object year = jobject.get("year");
			Object term = jobject.get("term");
			
			return "\"" + idObject + "\",\"" + term + "\",\"" + year + "\",\"" + courseSize + "\"\n";
		}
		
		catch(ParseException p){return "";}
    }
	
     public String getDetailedCourseInfo(int id) throws MalformedURLException, IOException {

        URL url = new URL(URLName + "course/" + String.valueOf(id));
        URLConnection con = url.openConnection();
        InputStream is = con.getInputStream();
		StringBuffer jsonContents = new StringBuffer();
		
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while((line = reader.readLine()) != null) {
                jsonContents.append(line + '\n');
            }
        }
		catch(IOException i){}
		
		try{
			String returnString = "\"ID\",";
			
			JSONParser parser = new JSONParser();
			JSONObject jobject = (JSONObject) parser.parse(jsonContents.toString());

			jobject = (JSONObject) parser.parse("" + jobject.get("grades"));
			
			String colHeadersString = (String) "" + jobject.get("colHeaders");
			colHeadersString = colHeadersString.substring(1,colHeadersString.length() - 1);
			returnString += (colHeadersString + "\n");
			
			String rowHeadersString = (String) "" + jobject.get("rowHeaders");
			String[] rowHeaders = rowHeadersString.split(",");
			rowHeaders[0] = rowHeaders[0].substring(1);
			rowHeaders[rowHeaders.length - 1] = rowHeaders[rowHeaders.length - 1].substring(0, rowHeaders[rowHeaders.length - 1].length() - 1);
			
			Object[] dataArray = ((JSONArray) jobject.get("data")).toArray();
			String s = "";
			for (int i = 0; i < dataArray.length; i++){
				s += (String) "" + dataArray[i];
			}
			String[] data = s.split("]");
			for (int i = 0; i < data.length; i++){
				data[i] = data[i].substring(1);
			}
			
			for (int i = 0; i < data.length; i++){
				String[] parts = data[i].split(",");
				String newScoreLine = "";
				String newScore = parts[0].substring(0,parts[0].length()-2);
				newScoreLine += ("\"" + newScore + "\"");
				for (int j = 1; j < parts.length; j++){
					newScore = parts[j].substring(0,parts[j].length()-2);
					newScoreLine += ("," + "\"" + newScore + "\"");
				}
				data[i] = newScoreLine;
			}
			
			for (int i = 0; i < rowHeaders.length; i++){
				returnString += rowHeaders[i] + "," + data[i] + "\n";
			}
			
			return returnString;
		}
		catch(ParseException p){ return "";}
    }

     public ArrayList<String> getStudentList() throws MalformedURLException, IOException{
        ArrayList<String> list = new ArrayList<>();
        String myLine = "";
        URL url = new URL(URLName + "studentlist/");
        URLConnection con = url.openConnection();
        InputStream is = con.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((myLine = br.readLine())!=null){
            String[] students = myLine.split(",");
			students[0] = students[0].substring(1);
			students[students.length-1] = students[students.length - 1].substring(0, students[students.length-1].length()-1);
			for (String s: students){
				list.add(s);
			}
        }
        return list;
    }
	
     public ArrayList<String> getCourseList() throws MalformedURLException, IOException 
    {
        ArrayList<String> list = new ArrayList<>();
        URL url = new URL(URLName + "courselist/");
        URLConnection con = url.openConnection();
        InputStream is = con.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String coursesLine = br.readLine();
		String[] courses = coursesLine.split(",");
		courses[0] = courses[0].substring(1);
		courses[courses.length - 1] = courses[courses.length - 1].substring(0, courses[courses.length - 1].length() - 1);
		for (String s: courses){
			list.add(s);
		}
        return list;
    }
	
	public String getStudentListCSV() throws MalformedURLException, IOException{
		String s = "\"ID\",\"Term\",\"Year\",\"Size\"\n";
		ArrayList<String> list = getStudentList();
		for (int i = 0; i < list.size(); i++){
			String studentInfo = list.get(i).substring(1,list.get(i).length()-1);
			s += getStudent(Integer.parseInt(studentInfo));
		}
		return s;
	}
	
	public String getCourseListCSV() throws MalformedURLException, IOException{
		String s = "\"ID\",\"Term\",\"Year\",\"Size\"\n";
		ArrayList<String> list = getCourseList();
		for (int i = 0; i < list.size(); i++){
			String courseInfo = list.get(i).substring(1,list.get(i).length()-1);
			s += getBasicCourseInfo(Integer.parseInt(courseInfo));
		}
		return s;
	}
    
	public String getURL(){
		return URLName;
	}
}
