package edu.jsu.mcis;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;

public class Leaderboard extends JPanel implements MouseListener{
	
	private LeaderBar[] bars;
	private int topScore;
	private ArrayList<Integer> scores;
	
	public Leaderboard(int width, int height, Assignment assignment){
		topScore = assignment.getTopScore();
		scores = assignment.getScores();
		int numBars = assignment.getScores().size();
		bars = new LeaderBar[numBars];
		for (int i = 0; i < bars.length; i++){
			double scaleFactor = calculateScaleFactor(i);
			bars[i] = new LeaderBar(scaleFactor, width * 2/3, height / numBars / 2);
			bars[i].setPoints(0, (i + 1) * height / (numBars + 1));
		}
	}
	
	private double calculateScaleFactor(int index){
		double topScore = (double) this.topScore;
		double currentScore = (double) scores.get(index);
		double percentage = scores.get(index) / topScore;
		System.out.println(percentage);
		return percentage;
		/*
		System.out.println(percentage);
		return  scores.get(index) / topScore * 100.0;
		*/
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
	
	public void mouseClicked(MouseEvent event) { 
	/*
		Shape shape =  whatever.getShape();
		if(shape.contains(event.getX(), event.getY())){
			notifyObservers();
		}
		repaint(shape);
		*/
	}
	public void mousePressed(MouseEvent event) {}
	public void mouseReleased(MouseEvent event) {}
	public void mouseEntered(MouseEvent event) {}
	public void mouseExited(MouseEvent event) {}
	
	
}