package edu.jsu.mcis.gamegogy;

import java.util.*;

public class LeaderboardObserver extends Observable{
	public LeaderboardObserver(){}
	
	public void update(String studentID, int score){
		setChanged();
		notifyObservers(studentID + ":" + score);
	}
}