package edu.jsu.mcis.gamegogy.service;

import org.springframework.web.bind.annotation.*;
import javax.annotation.PostConstruct;

import edu.jsu.mcis.gamegogy.*;

@RestController
public class WebService {
    private CSVReader source;
    
    @PostConstruct
    public void setup() {
        source = new CSVReader();
    }

	@RequestMapping(value="/gamegogy/studentlist", method=RequestMethod.GET)
	public String[] getStudentList(){
		return source.getStudentIDStrings();
	}
	
	@RequestMapping(value="/gamegogy/courselist", method=RequestMethod.GET)
	public String[] getCourseList(){
		return source.getCourseIDStrings();
	}
	
	@RequestMapping(value="/gamegogy/student/{id}", method=RequestMethod.GET)
	public Student getStudentInfo(@PathVariable String id){
		Student s = new Student("","","","");
		try{
			s = source.getStudent(id);
		}
		catch(StudentException se){}
		return s;
	}
	
	@RequestMapping(value="/gamegogy/course/{id}", method=RequestMethod.GET)
	public Course getCourseInfo(@PathVariable String id){
		Course c = new Course("","",-1,"");
		try{
			c = source.getCourse(id);
		}
		catch(CourseException co){}
		return c;
	}
	

}