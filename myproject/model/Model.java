package myproject.model;

import javax.swing.ImageIcon;

public interface Model {
	/**
	 * 
	 * @return get the position in the screen
	 */
	public double[] getPositon();
	/**
	 * 
	 * @return get the pixel of the width from the object
	 */
	public double getWidth();
	/**
	 * 
	 * @return get the pixel of the height from the object
	 */
	public double getHeight(); 
	/**
	 * 
	 * @return get image from the item
	 */
	public ImageIcon getImage();
}
