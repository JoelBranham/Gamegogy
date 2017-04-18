package edu.jsu.mcis.gamegogy;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;

public class LeaderboardObserver extends Observable{
	public LeaderboardObserver(){}
	
	public void update(String studentID, int score){
		setChanged();
		notifyObservers(studentID + ":" + score);
	}
}