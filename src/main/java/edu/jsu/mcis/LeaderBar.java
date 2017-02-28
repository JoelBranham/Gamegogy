package edu.jsu.mcis;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;

public class LeaderBar{

	private Point[] points;
	private Shape shape;
	
	public LeaderBar(double scaleFactor, int width, int height){
		points = new Point[4];
		points[0] = new Point(0,0);
		points[1] = new Point((int) (width * scaleFactor), 0);
		points[2] = new Point((int) (width * scaleFactor), height);
		points[3] = new Point(0, height);
		updateShape();
	}
	
	public Shape getShape(){
		return shape;
	}
	
	public Point[] getPoints(){
		return points;
	}
	
	private void updateShape(){
		int[] x = new int[points.length];
		int[] y = new int[points.length];
		for(int i = 0; i < points.length; i++) {
			x[i] = points[i].x;
			y[i] = points[i].y;
		}
		shape = new Polygon(x, y, points.length);
	}
	
	public void offsetPoints(int widthOffset, int heightOffset){
		for (int i = 0; i < points.length; i++){
			points[i].setLocation(widthOffset + points[i].x, heightOffset + points[i].y);
		}
		updateShape();
	}

}