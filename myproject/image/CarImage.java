package myproject.image;

import java.util.Random;

import javax.swing.ImageIcon;

import myproject.enums.Direction;
/**
 * Create the image for car
 * @author wenjun
 *
 */
final class CarImage implements Image{
	/**
	 * depends on the direction of the car
	 * the method can generate different car
	 * @param the direction of cars
	 * @return ImageIcon of cars
	 */
	@Override
	public ImageIcon CreateImage(Direction direction) {
		// TODO Auto-generated method stub
		String source = null;
		Random random=new Random();
		switch (direction) {
		case EastToWest:
		case WestToEast:
			source="/myproject/image/car1-"+(random.nextInt(4)+1)+".png";
			break;
		case NorthToSouth:
		case SouthToNorth:
			source="/myproject/image/car2-"+(random.nextInt(4)+1)+".png";
			break;
		default:
			break;
		}
		return new ImageIcon(getClass().getResource(source));
	}
}
