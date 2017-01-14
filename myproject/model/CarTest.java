package myproject.model;

import static org.junit.Assert.*;

import java.util.Random;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import myproject.enums.Direction;
import myproject.parameters.Parameters;

public class CarTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Random random=new Random();
		double[] velocity=new double[2];
		velocity[0]=0;
		velocity[1]=0;
		double []position=new double[2];
		position[0]=random.nextDouble();
		position[1]=random.nextDouble();
		for(int i=0;i<1000;i++)
		{
			CarObj temp= new CarObj(random.nextDouble()*(Parameters.maxVelocityMax-Parameters.maxVelocityMin)+Parameters.maxVelocityMin,
					random.nextDouble()*(Parameters.brakeDistanceMax-Parameters.brakeDistanceMin)+Parameters.brakeDistanceMin,
					random.nextDouble()*(Parameters.stopDiatanceMax-Parameters.stopDiatanceMin)+Parameters.stopDiatanceMin,
					random.nextDouble()*(Parameters.lengthMax-Parameters.lengthMin)+Parameters.lengthMin,
					velocity,position,Direction.EastToWest);
			Assert.assertTrue(temp.getBrakeDistance()>Parameters.brakeDistanceMin);
			Assert.assertTrue(temp.getBrakeDistance()<Parameters.brakeDistanceMax);
			Assert.assertTrue(temp.getMaxVelocity()>Parameters.maxVelocityMin);
			Assert.assertTrue(temp.getMaxVelocity()<Parameters.maxVelocityMax);
			Assert.assertTrue(temp.getStopDistance()>Parameters.stopDiatanceMin);
			Assert.assertTrue(temp.getStopDistance()<Parameters.stopDiatanceMax);

			
		}
	}
}
