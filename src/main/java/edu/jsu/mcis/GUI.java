package edu.jsu.mcis;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.util.*;
import java.net.MalformedURLException;


public class GUI extends JFrame implements ActionListener, Observer{
	
	private Leaderboard leaderboard;
	
    private Database dataBase;

	private JMenuBar bar;
    private JMenuItem menu;
    private JRadioButtonMenuItem offlineButton;
    private JRadioButtonMenuItem webButton;

	private JLabel course, column, term, enrollment, id, name, email, score, line, leader;
	private JLabel courseEnrollment, courseTerm, studentId, studentName, studentEmail, studentScore;
	private JComboBox<String> courseComboBox, columnComboBox;

	private Course currentCourse;
	private Assignment currentAssignment;
	private Student currentStudent;

	@SuppressWarnings("unchecked")
    public GUI(Database dataBase) throws IOException{

		getContentPane().setBackground(Color.black);
        this.dataBase = dataBase;
		setPreferredSize(new Dimension(500, 700));
		setLayout(null);
		setTitle("Gamegogy");
		
		
		JLabel course = new JLabel("Course");
		course.setFont(new Font("AR Julian", Font.PLAIN, 13));
		course.setForeground(Color.white);
		course.setBounds(50,10,50,32);
		add(course);
		
		Object[] objArray = dataBase.getCourseList().toArray();
		String[] courseStrings = new String[objArray.length];
		for (int i = 0; i < objArray.length; i++){
			courseStrings[i] = (String) objArray[i];
		}
		courseComboBox = new JComboBox<String>(courseStrings);
		courseComboBox.setBounds(100,16,75,24);
		courseComboBox.addActionListener(this);
		courseComboBox.setName("courseComboBox");
		add(courseComboBox);
		
		JLabel column = new JLabel("Column");
		column.setFont(new Font("AR Julian", Font.PLAIN, 13));
		column.setForeground(Color.white);
		column.setBounds(250,10,50,32);
		add(column);
		
		columnComboBox = new JComboBox<String>();
		columnComboBox.setBounds(300,16,110,24);
		columnComboBox.addActionListener(this);
		columnComboBox.setName("columnComboBox");
		add(columnComboBox);

		term = new JLabel("Term:");
		term.setFont(new Font("AR Julian", Font.PLAIN, 13));
		term.setForeground(Color.white);
		term.setBounds(50,180,50,32);
		add(term);
		
		courseTerm = new JLabel();
		courseTerm.setName("courseTerm");
		courseTerm.setFont(new Font("AR Julian", Font.PLAIN, 13));
		courseTerm.setForeground(Color.white);
		courseTerm.setBounds(90,180,150,32);
		add(courseTerm);
		
		enrollment = new JLabel("Enrollment:");
		enrollment.setFont(new Font("AR Julian", Font.PLAIN, 13));
		enrollment.setForeground(Color.white);
		enrollment.setBounds(250,180,100,32);
		add(enrollment);
		
		courseEnrollment = new JLabel();
		courseEnrollment.setName("courseEnrollment");
		courseEnrollment.setFont(new Font("AR Julian", Font.PLAIN, 13));
		courseEnrollment.setForeground(Color.white);
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
		id.setFont(new Font("AR Julian", Font.PLAIN, 13));
		id.setForeground(Color.white);
		id.setBounds(50,565,50,25);
		add(id);
		
        studentId = new JLabel();
        studentId.setBounds(100,565,400,25);
		studentId.setFont(new Font("AR Julian", Font.PLAIN, 13));
		studentId.setForeground(Color.white);
		studentId.setName("studentId");
		add(studentId);
		
		name = new JLabel("Name:");
		name.setFont(new Font("AR Julian", Font.PLAIN, 13));
		name.setForeground(Color.white);
		name.setBounds(50,585,50,25);
		add(name);
		
		studentName = new JLabel();
        studentName.setBounds(100,585,500,25);
		studentName.setName("studentName");
		studentName.setFont(new Font("AR Julian", Font.PLAIN, 13));
		studentName.setForeground(Color.white);
		add(studentName);
					
		email = new JLabel("Email:");
		email.setFont(new Font("AR Julian", Font.PLAIN, 13));
		email.setForeground(Color.white);
		email.setBounds(50,605,50,25);
		add(email);
		
		studentEmail = new JLabel();
		studentEmail.setFont(new Font("AR Julian", Font.PLAIN, 13));
		studentEmail.setForeground(Color.white);
        studentEmail.setBounds(100,605,500,25);
		studentEmail.setName("studentEmail");
		add(studentEmail);
	
		score = new JLabel("Score:");
		score.setFont(new Font("AR Julian", Font.PLAIN, 13));
		score.setForeground(Color.white);
		score.setBounds(50,625,50,25);
		add(score);
		
		studentScore = new JLabel();
        studentScore.setBounds(100,625,150,25);
		studentScore.setName("studentScore");
		studentScore.setFont(new Font("AR Julian", Font.PLAIN, 13));
		studentScore.setForeground(Color.white);
		add(studentScore);
		
		updateAfterCourseChange();
		updateAfterAssignmentChange();

		leaderboard = new Leaderboard(400, 300, currentAssignment);
		leaderboard.setBounds(50, 240, 400, 300);
		add(leaderboard);
		leaderboard.addAnObserver(this);


		bar = new JMenuBar();
        menu = new JMenu("Source");
        offlineButton = new JRadioButtonMenuItem("Resource File");
        webButton = new JRadioButtonMenuItem("Web Service");
        offlineButton.setSelected(true);
        webButton.setSelected(false);
		offlineButton.addActionListener(this);
        menu.add(offlineButton);
        webButton.addActionListener(this);
        menu.add(webButton);
        bar.add(menu);
		setJMenuBar(bar);

    }
	
	public void updateAfterCourseChange(){
		String currentCourseString = (String) courseComboBox.getSelectedItem();
		currentCourse = dataBase.getCourse(currentCourseString);
		Object[] obArray = currentCourse.getAssignmentList().toArray();
		String[] assignments = new String[obArray.length];
		for (int i = 0; i < obArray.length; i++){
			assignments[i] = (String) obArray[i];
		}
		columnComboBox.setModel(new DefaultComboBoxModel<String>(assignments));
		updateAfterAssignmentChange();
	}
	
	public void updateAfterAssignmentChange(){
		String currentAssignmentString = (String) columnComboBox.getSelectedItem();
		currentAssignment = currentCourse.getAssignment(currentAssignmentString);
		courseTerm.setText(currentCourse.getTerm() + " " + currentCourse.getYear());
		courseEnrollment.setText(currentCourse.getSize() + "");
		updateStudentInfo(currentAssignment.getTopStudentID(),currentAssignment.getTopScore());
	}
	
	private void updateStudentInfo(String id, int score){
		currentStudent = dataBase.getStudent(id);
		studentId.setText(currentStudent.getId() + "");
		studentName.setText(currentStudent.getFname() + " " + currentStudent.getLname());
		studentEmail.setText(currentStudent.getEmail() + "@jsu.edu");
		studentScore.setText(score + ".0");
	}
	
    public void update(Observable o, Object arg) {	
		Scanner s = new Scanner((String) arg).useDelimiter(":");
		updateStudentInfo(s.next(),s.nextInt());
	}
	
    public void actionPerformed(ActionEvent event) {
		if(event.getActionCommand().equals("Resource File")){
            webButton.setSelected(false);
            offlineButton.setSelected(true);
           	dataBase = new Database(new CSVReader());
           	setGUIIndex();
        }
		else if(event.getActionCommand().equals("Web Service")){
            webButton.setSelected(true);
            offlineButton.setSelected(false);
            try{
           		dataBase = new Database(new JSONReader("http://inspired.jsu.edu:7272/gamegogy"));
			}
			catch(IOException ex){
           		//JOptionPane.showMessageDialog(this,"Web Service Unavailable. Reverting to local Resource File.","Connection Error",JOptionPane.WARNING_MESSAGE);
           		dataBase = new Database(new CSVReader());
			}
			setGUIIndex();
        }
		else if (courseComboBox == event.getSource()){
			updateAfterCourseChange();
		}
		else if (columnComboBox == event.getSource()){
			updateAfterAssignmentChange();
		}
		leaderboard.setAssignment(currentAssignment);
    }

    public void setGUIIndex(){
	   courseComboBox.setSelectedIndex(0);
	   columnComboBox.setSelectedIndex(0);
	   updateAfterAssignmentChange();
	   updateAfterCourseChange();
    }
	
}
