package myproject.model;

import java.util.Random;

import myproject.parameters.Parameters;
import myproject.enums.Direction;
/**
 * The factory of making a random car
 * @author wenjun
 *
 */
public class CarFactory {
	private CarFactory(){}
	/**
	 * The factory of making a new car
	 * @param position the position of the car will appear in the road
	 * @param direction the direction of the car
	 * @return a new car
	 */
	public static CarObj newCar(double[] position,Direction direction)
	{
		Random random=new Random();
		double[] velocity=new double[2];
		velocity[0]=0;
		velocity[1]=0;
		return new CarObj(random.nextDouble()*(Parameters.maxVelocityMax-Parameters.maxVelocityMin)+Parameters.maxVelocityMin,
				random.nextDouble()*(Parameters.brakeDistanceMax-Parameters.brakeDistanceMin)+Parameters.brakeDistanceMin,
				random.nextDouble()*(Parameters.stopDiatanceMax-Parameters.stopDiatanceMin)+Parameters.stopDiatanceMin,
				random.nextDouble()*(Parameters.lengthMax-Parameters.lengthMin)+Parameters.lengthMin,
				velocity,position,direction);
	}
}
