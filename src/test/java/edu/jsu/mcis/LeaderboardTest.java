package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;

public class LeaderboardTest{
	
	@Test
	public void testGetShapes(){
		Assignment assignmentA = new Assignment("nope");
		assignmentA.addStudentAndScore("11111", 987);
		
		Leaderboard testLeaderboard = new Leaderboard(30,30, assignmentA);
		
		Shape[] shapes = new Shape[1];
		Point[] points = new Point[4];
		points[0] = new Point(0,0);
		points[1] = new Point(20,0);
		points[2] = new Point(20,15);
		points[3] = new Point(0, 15);
		
		int[] x = new int[points.length];
		int[] y = new int[points.length];
		for(int i = 0; i < points.length; i++) {
			x[i] = points[i].x;
			y[i] = points[i].y;
		}
		Shape shape = new Polygon(x, y, points.length);
		shapes[0] = shape;
		assertEquals((int)shape.getBounds().getX(), (int)testLeaderboard.getShapes()[0].getBounds().getX());
	}
}