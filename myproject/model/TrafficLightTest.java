package myproject.model;

import static org.junit.Assert.*;

import java.util.Random;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import myproject.parameters.Parameters;

public class TrafficLightTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		double[] pos=new double[2];
		Random random=new Random();

		for(int i=0;i<100;i++)
		{
			pos[0]=random.nextDouble()*Parameters.screenX;
			pos[1]=random.nextDouble()*Parameters.screenY;
			TrafficLight tl=new TrafficLightObj(pos);
			Assert.assertTrue(tl.getPositon()[0]==pos[0]);
			Assert.assertTrue(tl.getPositon()[1]==pos[1]);
		}
	}

}
