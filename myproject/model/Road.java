package myproject.model;

import java.util.ArrayList;

import myproject.enums.Direction;

public interface Road extends Model{
	
	public Direction getDirection();
	public int getRoadIndex(double x);
	public ArrayList<Double> getRoadList();
	public ArrayList<Double> getRoadPixelList();	
	public int getTrafficLight();	
	public double getGenerateTime();

	public double getGenerateCount();
	public void addGenerateCount();
	
	
}
