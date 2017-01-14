package myproject.model;

import java.util.Random;

import myproject.image.TrafficLightImageFactory;

import javax.swing.ImageIcon;

import myproject.parameters.Parameters;
import myproject.enums.LightColor;
/**
 * The model of traffic light
 * @author wenjun
 *
 */
final class TrafficLightObj implements TrafficLight{
	private LightColor _color;			//the color of the trafficLight
	private ImageIcon _image;			//the image of the trafficLight
	private double[] _position;			//the position of the position
	private double _greenTime;			//the green time of trafficLight
	private double _yellowTime;			//the yellow time of trafficLight
	private double _time;				//the time of counting
	/**
	 * 
	 * @param pos The position of traffic light
	 */
	public TrafficLightObj(double[] pos)
	{
		_image=TrafficLightImageFactory.MakeTrafficLightImage();
		_color=LightColor.GREEN;
		_position=pos;
		Random random=new Random();
		
		_greenTime=random.nextDouble()*(Parameters.greenLightMax-Parameters.greenLightMin)+Parameters.greenLightMin;
		_yellowTime=random.nextDouble()*(Parameters.yellowLightMax-Parameters.yellowLightMin)+Parameters.yellowLightMin;

	}
	
	
	@Override
	public double[] getPositon() {
		// TODO Auto-generated method stub
		_time+=myproject.parameters.Parameters.timeStep/1000.0;
		String source=null;
		if(_time-2*(this._greenTime+this._yellowTime)>0)
		{
			_time=0;
			source="/myproject/image/light-green.png";
			_color=LightColor.GREEN;
		}
		else if(_time-2*this._greenTime-this._yellowTime>0)
		{
			source="/myproject/image/light-yellow.png";
			_color=LightColor.YELLOW;
		}
		else if(_time-(this._greenTime+this._yellowTime)>0)
		{
			source="/myproject/image/light-red.png";
			_color=LightColor.RED;
		}
		else if(_time-this._greenTime>0)
		{
			source="/myproject/image/light-yellow.png";
			_color=LightColor.YELLOW;
		}
		else
		{
			source="/myproject/image/light-green.png";
			_color=LightColor.GREEN;
		}
		_image=new ImageIcon(getClass().getResource(source));
		return _position;
	}

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return _image.getIconWidth();
	}

	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return _image.getIconHeight();
	}

	@Override
	public ImageIcon getImage() {
		// TODO Auto-generated method stub
		return this._image;
	}
	public LightColor getColor()
	{
		return this._color;
	}

}
