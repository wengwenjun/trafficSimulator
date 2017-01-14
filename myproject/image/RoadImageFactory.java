package myproject.image;

import javax.swing.ImageIcon;

import myproject.enums.Direction;
/**
 * The factory to make a image of road
 * @author wenjun
 *
 */
public class RoadImageFactory {
	/**
	 * used to make image of road
	 * @param direction
	 * @return ImageIcon of road
	 */
	public static ImageIcon MakeRoadImage(Direction direction)
	{
		return new RoadImage().CreateImage(direction);	
	}
}
