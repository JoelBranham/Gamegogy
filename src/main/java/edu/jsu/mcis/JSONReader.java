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

public class JSONReader implements DataReader{
	
	private String URLName;
	private List<Student> studentList;
	private List<Course> courseList;
	private BufferedReader br;
	private InputStream is;
	private URLConnection con;
	
    public JSONReader(String URL) throws MalformedURLException, IOException {
		studentList = new ArrayList<Student>();
		courseList = new ArrayList<Course>();
		
		if(URL.charAt(URL.length()-1) != '/'){
			URL += "/";
		}
		URLName = URL;
		
		buildOnlineCourse();
		addOnlineCourseInfo();
		buildOnlineStudent();
    }
	
	private void buildOnlineCourse(){
  		String myline;
    	try{
			BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(getCourseListCSV().getBytes())));
            myline = in.readLine();
            while ((myline = in.readLine())!=null){
				String[] course = myline.split(",");
				String id = (course[0].substring(1,course[0].length()-1));
				String year = (course[2].substring(1,course[2].length()-1));
				String size = (course[3].substring(1,course[3].length()-1));
				String term = course[1].substring(1, course[1].length()-1);
				courseList.add(new Course(id, year, Integer.valueOf(size), term));		
            }			
        }
        catch(IOException e) {e.printStackTrace();}		
    }
	
	private void addOnlineCourseInfo() throws MalformedURLException, IOException{
		for (String s: getCourseString()){
			String courseID = s;
			try{
				Course c = null;
				for(Course d: courseList){
					if(d.getId() == courseID){
						c = d;
					}
				}
				int courseIDNum = Integer.parseInt(courseID);
				String full = getDetailedCourseInfo(courseIDNum);  
				BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(full.getBytes())));
				String[] assignments = in.readLine().split(",");
				List<String> lines = new ArrayList<String>();
				String myline;
				while ((myline = in.readLine())!=null){
					lines.add(myline);
				}
				for (int i = 1; i < assignments.length; i++){
					String assignmentName = assignments[i].substring(1, assignments[i].length()-1);
					Assignment a = new Assignment(assignmentName);
					for (int j = 0; j < lines.size(); j++){
						String[] row = lines.get(j).split(",");
						String studentId = (row[0].substring(1,row[0].length()-1));
						int score = Integer.parseInt(row[i].substring(1,row[i].length()-1));
						a.addStudentAndScore(studentId, score);
					}
					c.addAssignment(a);
				}
				for (int i = 0; i < courseList.size(); i++){
					if (courseList.get(i).getId() == courseID){
						courseList.remove(i);
						courseList.add(c);
					} 
				}
			}
			catch(IOException e) {e.printStackTrace();}
		}
	}
	
	private void buildOnlineStudent(){
    	String myline;
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(getStudentListCSV().getBytes())));
			String check = in.readLine();
            while ((myline = in.readLine())!=null){
				String[] student = myline.split(",");
				String id = (student[0].substring(1,student[0].length()-1));
				String first = student[1].substring(1, student[1].length() - 1);
				String last = student[2].substring(1, student[2].length() - 1);
				String email = student[3].substring(1, student[3].length() - 1);
				studentList.add(new Student(id,  first, last, email));
            }
        }
        catch(IOException e) {e.printStackTrace();}
    }
	
	private void openConnection(String URLExtension) throws MalformedURLException, IOException{
        URL url = new URL(URLName + URLExtension);
        con = url.openConnection();
        is = con.getInputStream();
        br = new BufferedReader(new InputStreamReader(is));	
	}
	
    private String getStudentFromJSON(int id) throws MalformedURLException, IOException{
        String ID = String.valueOf(id);
		openConnection("student/" + ID);
        
        String returnString = br.readLine();
        String studentID = returnString.split("id\":\"")[1].split("\"")[0];
        String studentFname = returnString.split("first\":\"")[1].split("\"")[0];
        String studentLname = returnString.split("last\":\"")[1].split("\"")[0];
        String studentEmail = returnString.split("email\":\"")[1].split("\"")[0];
        returnString = "\"" + studentID + "\",\"" + studentFname + "\",\"" + studentLname + "\",\"" + studentEmail + "\"\n";
        return returnString;
    }
	
     private String getBasicCourseInfo(int id) throws MalformedURLException, IOException {

        openConnection("course/" + String.valueOf(id));
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
	
    private String getDetailedCourseInfo(int id) throws MalformedURLException, IOException {

        openConnection("course/" + String.valueOf(id));
		StringBuffer jsonContents = new StringBuffer();
		
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while((line = reader.readLine()) != null) {
                jsonContents.append(line + '\n');
            }
        }
		catch(IOException i){throw new IOException();}

		try{
			String returnString = "\"ID\",";
			
			JSONParser parser = new JSONParser();
			JSONObject jobject = (JSONObject) parser.parse(jsonContents.toString());
			
			jobject = (JSONObject) parser.parse("" + jobject.get("grades"));
			
			String colHeadersString = "" + jobject.get("colHeaders");
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

     private List<String> getStudentListJSON() throws MalformedURLException, IOException{
        List<String> list = new ArrayList<>();
        String myLine = "";
        openConnection("studentlist");

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
	
     private List<String> getCourseListJSON() throws MalformedURLException, IOException {
        List<String> list = new ArrayList<>();
        openConnection("courselist");

        String coursesLine = br.readLine();
		String[] courses = coursesLine.split(",");
		courses[0] = courses[0].substring(1);
		courses[courses.length - 1] = courses[courses.length - 1].substring(0, courses[courses.length - 1].length() - 1);
		for (String s: courses){
			list.add(s);
		}
        return list;
    }
	
	private String getStudentListCSV() throws MalformedURLException, IOException{
		String s = "\"ID\",\"First\",\"Last\",\"Email\"\n";
		List<String> list = getStudentListJSON();
		for (int i = 0; i < list.size(); i++){
			String studentInfo = list.get(i).substring(1,list.get(i).length()-1);
			s += getStudentFromJSON(Integer.parseInt(studentInfo));
		}
		return s;
	}
	
	private String getCourseListCSV() throws MalformedURLException, IOException{
		String s = "\"ID\",\"Term\",\"Year\",\"Size\"\n";
		List<String> list = getCourseListJSON();
		for (int i = 0; i < list.size(); i++){
			String courseInfo = list.get(i).substring(1,list.get(i).length()-1);
			s += getBasicCourseInfo(Integer.parseInt(courseInfo));
		}
		return s;
	}
	
	public List<Student> getStudentList(){
		return studentList;
	}
	
	public List<Course> getCourseList(){
		return courseList;
	}
    
	public String getURL(){
		return URLName;
	}
	
	public List<String> getCourseString(){
		List<String> courseStrings = new ArrayList<>();
		for(Course c: courseList){
			courseStrings.add(c.getId());
		}
		return courseStrings;
	}
	
}
