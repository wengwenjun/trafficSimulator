package myproject.model;

import myproject.image.CarImageFactory;

import javax.swing.ImageIcon;

import myproject.parameters.Parameters;
import myproject.enums.Direction;
/**
 * The car class of the project
 * @author wenjun
 *
 */
final class CarObj implements Car{

	private double _maxVelocity;  	// The maximum velocity of the car (in meters/second)
	private double _brakeDistance; 	// If distance to nearest obstacle is <= brakeDistance,
									//   then the car will start to slow down (in meters)
	private double _stopDistance;	// If distance to nearest obstacle is == stopDistance,
	       							//   then the car will stop (in meters)
	private double _length;  		// Length of the car (in meters)
	private double[] _velocity;		// the velocity in pixel
	private double[] _position;		// the position of the screen
	private int _indexOfRoad;		// record the car in the position of road
	private Direction _direction;	// the direction of the car
	private  ImageIcon _image;		// the image object of the car
	private double _scale;			// be used to change the car length to the pixel in screen
	/**
	 * 
	 * @param maxVelocity the max velocity of a car
	 * @param brakeDistance the brake distance of a car
	 * @param stopDistance the stop distance of a car
	 * @param length the car's length in meters
	 * @param velocity the velocity of a car in pixels
	 * @param pos the position of a car
	 * @param direction the position of a car
	 */
	public CarObj(double maxVelocity,double brakeDistance,double stopDistance,
			double length,double[] velocity,double[] pos,Direction direction){
				_maxVelocity=maxVelocity;
				_brakeDistance=brakeDistance;
				_stopDistance=stopDistance;
				_length=length;
				_velocity=velocity;
				_position=new double[2];
				_direction=direction;
				
				_position[0]=pos[0];
				_position[1]=pos[1];
				
				if(_position[0]==0&&_direction.equals(Direction.WestToEast))
				{
					_position[0]=Parameters.screenX;
				}
				if(_position[1]==0&&_direction.equals(Direction.SouthToNorth))
				{
					_position[1]=Parameters.screenY;
				}
				
				_image=CarImageFactory.MakeCarImage(direction);
				_scale=1;
			 }
	@Override
	public double[] getPositon() {
		// TODO Auto-generated method stub
		 _position[0]+=_velocity[0];
		 _position[1]+=_velocity[1];
		return this._position;
	}

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		double width=0;
		switch (this._direction){
			case EastToWest:
			case WestToEast:
				width=this._length*3;
				break;
			case NorthToSouth:
			case SouthToNorth:
				width=this._image.getIconWidth();
				break;
			default:
				break;
		}
		return width;
	}

	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		double height=0;
		switch (this._direction){
			case EastToWest:
			case WestToEast:
				height=this._image.getIconHeight();
				break;
			case NorthToSouth:
			case SouthToNorth:
				height=this._length*2;
				break;
			default:
				break;
		}
		return height;
	}

	@Override
	public ImageIcon getImage() {
		// TODO Auto-generated method stub
		return this._image;
	}

	 public double getLength()
	 {
		 return this._length;
	 }
	 public double getMaxVelocity()
	 {
		 return this._maxVelocity;
	 }
	 public double getStopDistance()
	 {
		 return this._stopDistance;
	 }
	 public double getBrakeDistance()
	 {
		 return this._brakeDistance;
	 }
	 public Direction getDirection()
	 {
		 return this._direction;
	 }
	 public void setScale(double pixel,double meter)
	 {
		 _scale=pixel/meter;
	 }

	 public double[] getVelocity()
	 {
		 return this._velocity;
	 }
	 public void setIndexOfRoad(int index)
	 {
		 this._indexOfRoad=index;
	 }
	 public int getIndexOfRoad()
	 {
		 return this._indexOfRoad;
	 }
	 public void setVelocity(double x)
	 {
		 switch (_direction)
		 {
			case EastToWest:
				this._velocity[0]=x;
				break;
			case WestToEast:
				 this._velocity[0]=-1*x;
				 break;
			case NorthToSouth:
				this._velocity[1]=x;
				break;
			case SouthToNorth:
				 this._velocity[1]=-1*x;
				 break;
			default:
				break;
		 }
	 }
}
