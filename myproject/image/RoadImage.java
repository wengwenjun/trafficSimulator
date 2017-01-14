package myproject.image;

import java.util.Random;

import javax.swing.ImageIcon;

import myproject.enums.Direction;
/**
 * Road images
 * @author wenjun
 *
 */
final class RoadImage implements Image{
	/**
	 * @param the direction of road
	 * @return the ImageIcon of road
	 */
	public ImageIcon CreateImage(Direction direction) {
		// TODO Auto-generated method stub
		String source = null;
		switch (direction) {
		case EastToWest:
		case WestToEast:
			source="ew";
			break;
		case SouthToNorth:
		case NorthToSouth:
			source="ns";
			break;
		default:
			break;
		}
		return new ImageIcon(getClass().getResource("/myproject/image/road"+source+".png"));
		
	}

}
