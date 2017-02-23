package edu.jsu.mcis;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;


public class GUI extends JPanel implements ActionListener{
    private Database dataBase;
//    private JLabel[] labels;
	private JLabel course, column, term, enrollment, id, name, email, score;
    private JLabel courseEnrollment, courseTerm, studentId, studentName, studentEmail, studentScore;
    private JComboBox courseComboBox, columnComboBox;
//    private final int numOfLabels = 20;
//   private final int numOfCombos = 3;

	private JPanel comboBoxPanel;
	private JPanel space;
	private JPanel termEnrollment;
	private JPanel space2;
	private JPanel idPanel;
	private JPanel namePanel;
	private JPanel emailPanel;
	private JPanel scorePanel;

	private Course currentCourse;
	private Assignment currentAssignment;
	private Student currentStudent;

    public GUI(Database dataBase) throws IOException{		
        this.dataBase = dataBase;
		setPreferredSize(new Dimension(700, 700));
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        BufferedImage img = ImageIO.read(new File("src\\main\\resources\\board.jpg"));
        BufferedImage img2 = ImageIO.read(new File("src\\main\\resources\\black.png"));
        JLabel bg = new JLabel(new ImageIcon(img));
        //bg.setBounds(0,100,700,700);
        JLabel bg1 = new JLabel(new ImageIcon(img2));
        //bg1.setBounds(175,70,100,24);
		
		comboBoxPanel = new JPanel(new GridLayout(0,4));
		comboBoxPanel.setMaximumSize(new Dimension(450, 50));
		
		JLabel course = new JLabel("Course");
		comboBoxPanel.add(course);
		
		courseComboBox = new JComboBox(dataBase.getCourseList().toArray());
		//courseComboBox.setBounds(175,28,100,24);
		//courseComboBox.setSize(new Dimension(100,100));
		courseComboBox.addActionListener(this);
		courseComboBox.setName("courseComboBox");
		comboBoxPanel.add(courseComboBox);
		
		JLabel column = new JLabel("Column");
		comboBoxPanel.add(column);
		
		columnComboBox = new JComboBox();
		//columnComboBox.setSize(new Dimension(200,200));
		columnComboBox.addActionListener(this);
		columnComboBox.setName("columnComboBox");
		//columnComboBox.setBounds(525,28,100,24);
		comboBoxPanel.add(columnComboBox);

		add(comboBoxPanel);
		
		
		
		space = new JPanel();
		space.setPreferredSize(new Dimension(1,300));
		space.setMaximumSize(new Dimension(1,300));
		add(space);
		
		
		termEnrollment = new JPanel(new GridLayout(0,4));
		termEnrollment.setPreferredSize(new Dimension(450,50));
		termEnrollment.setMaximumSize(new Dimension(450,50));
		
		term = new JLabel("Term: ");
		termEnrollment.add(term);
		
		courseTerm = new JLabel();
		courseTerm.setName("courseTerm");
		//courseTerm.setBounds(175,200,150,32);
		termEnrollment.add(courseTerm);
		
		enrollment = new JLabel("Enrollment: ");
		termEnrollment.add(enrollment);
		
		courseEnrollment = new JLabel();
		courseEnrollment.setName("courseEnrollment");
		//courseEnrollment.setBounds(575,200,200,32);
		termEnrollment.add(courseEnrollment);
		
		add(termEnrollment);
		
		
		
		space2 = new JPanel();
		space2.setPreferredSize(new Dimension(1,200));
		space2.setMaximumSize(new Dimension(1,200));
		add(space2);
		
		
		
		idPanel = new JPanel();
		id = new JLabel("ID: ");
		idPanel.add(id);
		
        studentId = new JLabel();
        //studentId.setBounds(175,480,400,32);
		studentId.setName("studentId");
		idPanel.add(studentId);
		
		add(idPanel);
		
		
		
		namePanel = new JPanel();
		
		name = new JLabel("Name: ");
		namePanel.add(name);
		
		studentName = new JLabel();
        //studentName.setBounds(175,520,400,32);
		studentName.setName("studentName");
		namePanel.add(studentName);
		
		add(namePanel);
		
		
		
		emailPanel = new JPanel();
		
		email = new JLabel("Email: ");
		emailPanel.add(email);
		
		studentEmail = new JLabel();
        //studentName.setBounds(175,560,400,32);
		studentEmail.setName("studentEmail");
		emailPanel.add(studentEmail);
		
		add(emailPanel);
		
		
		scorePanel = new JPanel();
		
		score = new JLabel("Score: ");
		scorePanel.add(score);
		
		studentScore = new JLabel();
        //studentScore.setBounds(175,520,400,32);
		studentScore.setName("studentScore");
		scorePanel.add(studentScore);
		
		add(scorePanel);
		
		
		
		updateAfterCourseChange();
		updateAfterAssignmentChange();
		
		
		/*
        labels = new JLabel[numOfLabels];
        for (int k=0;k<labels.length;k++)
        {
            labels[k] = new JLabel();
            frame.add(labels[k]);
            if (k>2)
            labels[k].setForeground(java.awt.Color.white);
            labels[k].setFont(new Font("Serif", Font.BOLD,20));
        }
        frame.add(bg);
       // frame.add(bg1);
        
       
        labels[1].setText("Course");
        labels[1].setBounds(100,20,70,32);
        labels[2].setText("Column");
        labels[2].setBounds(450,20,70,32);
        labels[3].setText("Term:");
        labels[3].setBounds(100,200,70,32);
        labels[4].setText("Enrollment:");
        labels[4].setBounds(450,200,200,32);
        labels[5].setText("ID:");
        labels[5].setBounds(100,480,70,32);
        labels[6].setText("Name:");
        labels[6].setBounds(100,520,70,32);
        labels[7].setText("Email:");
        labels[7].setBounds(100,560,70,32);
        labels[8].setText("Score:");
        labels[8].setBounds(100,600,70,32);
		*/
        /*
		String assignmentName = (String) columnComboBox.getSelectedItem();
        //labels[9].setText("111169");
        //labels[9].setBounds(175,480,400,32);
        studentName = new JLabel("Otis Pate");//dataBase.getstudent(columnComboBox.getSelectedItem().getTopStudentID()).getName();
        studentName.setBounds(175,520,400,32);
        //labels[10].setText("Otis Pate");
        //labels[10].setBounds(175,520,400,32);
        labels[11].setText("opate@jsu.edu");//dataBase.getstudent(columnComboBox.getSelectedItem().getTopStudentID()).getEmail();
        labels[11].setBounds(175,560,400,32);
        studentScore = new JLabel("99");//columnComboBox.getSelectedItem().getstudent()
        studentScore.setBounds(175,520,400,32);
       //labels[12].setText("99");
        //labels[12].setBounds(175,600,400,32);
        courseTerm = new JLabel("Summer");//courseComboBox.getSelectedItem().getTerm()
        //labels[13].setText("Summer");
        //labels[13].setBounds(175,200,150,32);
        courseEnrollment.setText("15");//courseComboBox.getSelectedItem().getSize()
        courseEnrollment.setBounds(575,200,200,32);
        
        
        /*
        combos = new JComboBox[numOfCombos];
        combos[0] = new JComboBox(dataBase.getCourseList().toArray());
        combos[0].addActionListener((ActionListener) this);
        combos[0].setBounds(175,28,100,24);
       
        frame.add(combos[0]);
        combos[1] = new JComboBox();
        combos[1].addActionListener((ActionListener) this);
        combos[1].setBounds(525,28,100,24);
        combos[0].updateUI();
        frame.add(combos[1]);
        */

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
    }
}
