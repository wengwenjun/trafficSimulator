package myproject.image;

import javax.swing.ImageIcon;

import myproject.enums.Direction;
/**
 * The factory of generate the image of a car
 * @author wenjun
 *
 */
public class CarImageFactory {
	public static ImageIcon MakeCarImage(Direction direction)
	{
		return new CarImage().CreateImage(direction);	
	}
}
