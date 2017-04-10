package edu.jsu.mcis;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;

public class Leaderboard extends JPanel implements MouseListener{
	
	private LeaderBar[] bars;
	private Assignment assignment;
	private int numBars, myWidth, myHeight;
	private LeaderboardObserver leaderboardObserver;
	private float fontSize;
	
	public Leaderboard(int width, int height, Assignment assignment){
		myWidth = width;
		myHeight = height;
		leaderboardObserver = new LeaderboardObserver();
		bars = new LeaderBar[numBars];
		setAssignment(assignment);
		addMouseListener(this);
		setLayout(null);
		bars[0].setColor(Color.red);
		repaint();
	}


	public void setAssignment(Assignment assignment){
		for (int i=0; i<bars.length; i++){
			bars[i].setLabelText("");
		}
		
	
		this.assignment = assignment;
		numBars = assignment.getScores().size();
		bars = new LeaderBar[numBars];
		for (int i = 0; i < bars.length; i++){
			bars[i] = new LeaderBar(calculateScaleFactor(i), myWidth * 2/3, myHeight / numBars / 2);
			bars[i].offsetPoints(0, (i + 1) * myHeight / (numBars + 1));
			bars[i].setLabelBounds(myWidth - 75, (i + 1) * myHeight / (numBars + 1), 30, myHeight / numBars / 2);
			bars[i].setLabelText(assignment.getScores().get(i)+ "");
			fontSize = 140.0f/(float) numBars;
			if(numBars < 10){
				fontSize = 15.0f;
			}
			bars[i].getLabel().setFont(bars[i].getLabel().getFont().deriveFont(fontSize));
			add(bars[i].getLabel());
		}
		bars[0].setColor(Color.red);
		repaint();
	}
	
	private double calculateScaleFactor(int index){
		return (double)(assignment.getScores().get(index)) / (double) (assignment.getTopScore());
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		for (int i = 0; i < bars.length; i++){
			g2d.setColor(bars[i].getColor());
			g2d.draw(bars[i].getShape());
			g2d.fill(bars[i].getShape()); 	
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
				leaderboardObserver.update(assignment.getStudents().get(i), assignment.getScores().get(i));
				bars[i].setColor(Color.red);
			}
			else {
				bars[i].setColor(Color.black);
			}
		}
		repaint();
	}
	
	public void addAnObserver(Observer observer){
		leaderboardObserver.addObserver(observer);
	}
	
	public void mousePressed(MouseEvent event) {}
	public void mouseReleased(MouseEvent event) {}
	public void mouseEntered(MouseEvent event) {}
	public void mouseExited(MouseEvent event) {}
	
}
