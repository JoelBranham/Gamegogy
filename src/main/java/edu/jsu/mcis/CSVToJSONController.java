package edu.jsu.mcis;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class CSVToJSONController {
	
	/*
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
	*/

	@RequestMapping("/gamegogy/studentlist")
	public List<String> getStudentList(){
		return CSVToJSONConverter.getStudentList();
	}
	
	@RequestMapping("/gamegogy/courselist")
	public List<String> getCourseList(){
		return CSVToJSONConverter.getCourseList();
	}
	
	@RequestMapping("/gamegogy/student")
	public String getStudentInfo(@RequestParam(value="id") String id){
		return CSVToJSONConverter.getStudentInfo(id);
	}
	
	@RequestMapping("/gamegogy/course")
	public String getCourseInfo(@RequestParam(value="id") String id){
		return CSVToJSONConverter.getCourseInfo(id);
	}
	

}