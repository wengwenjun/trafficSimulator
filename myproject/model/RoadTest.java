package myproject.model;

import static org.junit.Assert.*;

import java.util.Random;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import myproject.parameters.Parameters;
import myproject.enums.Direction;

public class RoadTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		double[] position=new double[2];
		Random random=new Random();
		for(int i=0;i<100;i++)
		{
			int trafficLight=random.nextInt()%10;
			Road rd=new RoadObj(Parameters.screenX,Parameters.screenY,
					position,Direction.WestToEast,trafficLight);
			Assert.assertTrue(trafficLight==rd.getTrafficLight());;
		}
	}

}
