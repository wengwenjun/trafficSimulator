package myproject.model;

import myproject.enums.Direction;
/**
 * The car class of the project
 * @author wenjun
 *
 */
public interface Car extends Model{
	
	 public double getLength();
	 public double getMaxVelocity();
	 public double getStopDistance();
	 public double getBrakeDistance();
	 public Direction getDirection();
	 public void setScale(double pixel,double meter);
	 public double[] getVelocity();
	 public void setIndexOfRoad(int index);
	 public int getIndexOfRoad();
	 public void setVelocity(double x);
}
