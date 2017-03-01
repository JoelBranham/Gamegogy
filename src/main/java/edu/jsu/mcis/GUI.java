package edu.jsu.mcis;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;


public class GUI extends JFrame implements ActionListener{
	
	private Leaderboard leaderboard;
	
    private Database dataBase;
	private JLabel course, column, term, enrollment, id, name, email, score, line;
	private JLabel courseEnrollment, courseTerm, studentId, studentName, studentEmail, studentScore;
	private JComboBox courseComboBox, columnComboBox;

	private Course currentCourse;
	private Assignment currentAssignment;
	private Student currentStudent;

    public GUI(Database dataBase) throws IOException{
		
        this.dataBase = dataBase;
		setPreferredSize(new Dimension(500, 700));
		setLayout(null);
		setTitle("Gamegogy");
		
		JLabel course = new JLabel("Course");
		course.setBounds(50,10,50,32);
		add(course);
		
		courseComboBox = new JComboBox(dataBase.getCourseList().toArray());
		courseComboBox.setBounds(100,16,75,24);
		courseComboBox.addActionListener(this);
		courseComboBox.setName("courseComboBox");
		add(courseComboBox);
		
		JLabel column = new JLabel("Column");
		column.setBounds(250,10,50,32);
		add(column);
		
		columnComboBox = new JComboBox();
		columnComboBox.setBounds(300,16,110,24);
		columnComboBox.addActionListener(this);
		columnComboBox.setName("columnComboBox");
		add(columnComboBox);

		term = new JLabel("Term:");
		term.setBounds(50,180,50,32);
		add(term);
		
		courseTerm = new JLabel();
		courseTerm.setName("courseTerm");
		courseTerm.setBounds(90,180,150,32);
		add(courseTerm);
		
		enrollment = new JLabel("Enrollment:");
		enrollment.setBounds(250,180,100,32);
		add(enrollment);
		
		courseEnrollment = new JLabel();
		courseEnrollment.setName("courseEnrollment");
		courseEnrollment.setBounds(325,180,50,32);
		add(courseEnrollment);
		
		
		String s = "";
		for(int i = 0; i < 125; i++){
			s += "-";
		}
		line = new JLabel(s);
		line.setBounds(0,525,500,32);
		add(line);
		
		id = new JLabel("ID:");
		id.setBounds(50,565,50,25);
		add(id);
		
        studentId = new JLabel();
        studentId.setBounds(100,565,400,25);
		studentId.setName("studentId");
		add(studentId);
		
		name = new JLabel("Name:");
		name.setBounds(50,585,50,25);
		add(name);
		
		studentName = new JLabel();
        studentName.setBounds(100,585,500,25);
		studentName.setName("studentName");
		add(studentName);
					
		email = new JLabel("Email:");
		email.setBounds(50,605,50,25);
		add(email);
		
		studentEmail = new JLabel();
        studentEmail.setBounds(100,605,500,25);
		studentEmail.setName("studentEmail");
		add(studentEmail);
	
		score = new JLabel("Score:");
		score.setBounds(50,625,50,25);
		add(score);
		
		studentScore = new JLabel();
        studentScore.setBounds(100,625,150,25);
		studentScore.setName("studentScore");
		add(studentScore);
		
		updateAfterCourseChange();
		updateAfterAssignmentChange();
		
		leaderboard = new Leaderboard(400, 300, currentAssignment);
		leaderboard.setBounds(50, 200, 400, 300);
		add(leaderboard);
    }
	
	public void updateAfterCourseChange(){
		
		String currentCourseString = (String) courseComboBox.getSelectedItem();
		currentCourse = dataBase.getCourse(Integer.parseInt(currentCourseString));
		columnComboBox.setModel(new DefaultComboBoxModel(currentCourse.getAssignmentList().toArray()));
		updateAfterAssignmentChange();
	}
	
	public void updateAfterAssignmentChange(){
		
		String currentAssignmentString = (String) columnComboBox.getSelectedItem();
		currentAssignment = currentCourse.getAssignment(currentAssignmentString);
		courseTerm.setText(currentCourse.getTerm() + " " + currentCourse.getYear());
		courseEnrollment.setText(currentCourse.getSize() + "");
		currentStudent = dataBase.getStudent(currentAssignment.getTopStudentID());
		studentId.setText(currentStudent.getId() + "");
		studentName.setText(currentStudent.getFname() + " " + currentStudent.getLname());
		studentEmail.setText(currentStudent.getEmail() + "@jsu.edu");
		studentScore.setText(currentAssignment.getTopScore() + ".0");
	}
	
    public void actionPerformed(ActionEvent event) {
		if (courseComboBox == event.getSource()){
			updateAfterCourseChange();
		}
		else if (columnComboBox == event.getSource()){
			updateAfterAssignmentChange();
		}
		leaderboard.setAssignment(currentAssignment);
    }
}
