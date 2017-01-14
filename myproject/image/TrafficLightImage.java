package myproject.image;

import java.util.Random;

import javax.swing.ImageIcon;

import myproject.enums.Direction;
/**
 * The class of trafic light
 * @author wenjun
 *
 */
final class TrafficLightImage implements Image{
	/**
	 * @param the direction of image
	 * @return the image of traffic light
	 */
	@Override
	public ImageIcon CreateImage(Direction direction) {
		// TODO Auto-generated method stub
		return new ImageIcon(getClass().getResource("/myproject/image/light-green.png"));
	}

}
