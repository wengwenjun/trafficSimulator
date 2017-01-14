package myproject.image;

import javax.swing.ImageIcon;

import myproject.enums.Direction;
/**
 * The factory of traffic light
 * @author wenjun
 *
 */
public class TrafficLightImageFactory {
	/**
	 * 
	 * @return a image of a traffic light 
	 */
	public static ImageIcon MakeTrafficLightImage()
	{
		Direction direction=Direction.EastToWest;
		return new TrafficLightImage().CreateImage(direction);	
	}
	
}
