package edu.jsu.mcis;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;

public class Leaderboard extends JPanel implements MouseListener{
	
	private LeaderBar[] bars;
	private Assignment assignment;
	private ArrayList<Integer> scores;
	private ArrayList<String> studentIDs;
	private int numBars, myWidth, myHeight;
	private LeaderboardObserver leaderboardObserver;
	
	public Leaderboard(int width, int height, Assignment assignment){
		myWidth = width;
		myHeight = height;
		leaderboardObserver = new LeaderboardObserver();
		setAssignment(assignment);
		addMouseListener(this);
	}
	public void setAssignment(Assignment assignment){
		this.assignment = assignment;
		studentIDs = assignment.getStudents();
		scores = assignment.getScores();
		numBars = assignment.getScores().size();
		bars = new LeaderBar[numBars];
		for (int i = 0; i < bars.length; i++){
			bars[i] = new LeaderBar(calculateScaleFactor(i), myWidth * 2/3, myHeight / numBars / 2);
			bars[i].offsetPoints(0, (i + 1) * myHeight / (numBars + 1));
			repaint(bars[i].getShape().getBounds());
		}
	}
	
	private double calculateScaleFactor(int index){
		return (double)(scores.get(index)) / (double) (assignment.getTopScore());
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		for (int i = 0; i < bars.length; i++){
			Shape shape = bars[i].getShape();
			g2d.setColor(Color.black);
			g2d.draw(shape);
			g2d.fill(shape); 	
		}
	}
	
	public Shape[] getShapes(){
		Shape[] shapes = new Shape[bars.length];
		for (int i = 0; i < bars.length; i++){
			shapes[i] = bars[i].getShape();
		}
		return shapes;
	}
	
	public void mouseClicked(MouseEvent event) { 
		for (int i = 0; i < bars.length; i++){
			if (bars[i].getShape().getBounds().contains(event.getX(), event.getY())){
				leaderboardObserver.update(studentIDs.get(i), scores.get(i));
			}
		}
	}
	
	public void addAnObserver(Observer observer){
		leaderboardObserver.addObserver(observer);
	}
	
	public void mousePressed(MouseEvent event) {}
	public void mouseReleased(MouseEvent event) {}
	public void mouseEntered(MouseEvent event) {}
	public void mouseExited(MouseEvent event) {}
	
	
}