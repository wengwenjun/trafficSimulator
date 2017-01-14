package myproject.model;
/**
 * The factory of making a traffic light
 * @author wenjun
 *
 */
public class TrafficLightFactory {
	public static TrafficLight newTrafficLight(double[] pos)
	{
		return new TrafficLightObj(pos);
	}
}
