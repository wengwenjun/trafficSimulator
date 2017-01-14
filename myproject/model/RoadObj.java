package myproject.model;

import myproject.image.RoadImageFactory;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

import myproject.parameters.Parameters;
import myproject.enums.Direction;

final class RoadObj implements Road{
	private Direction _direction;				//the direction of the road
	private double[] _position;					//the position of the road
	private double _screenX;					//the screen X, it's used to draw the road
	private double _screenY;					//the screen Y, it's used to draw the road
	private  ImageIcon _image;					//the Image of the road
	private ArrayList<Double> _roadList;			//the length of each road and intersection 
	private ArrayList<Double> _roadPixelList;	//the pixels ot each road and intersection
	private int _trafficLight;					//the number of traffic lights in each road
	private double _generateTime;				//the car generate time
	private int _generateCount;
	/**
	 * 
	 * @param screenX the screen resolution of width
	 * @param screenY the screen resolution of height
	 * @param position the position of a road
	 * @param direction the direction of a road
	 * @param trafficLight the number of the traffic light in the road
	 */
	public RoadObj(double screenX,double screenY,double[] position,
			Direction direction,int trafficLight)
	{
		_screenX=screenX;
		_screenY=screenY;
		_position=new double[2];
		_position[0]=position[0];
		_position[1]=position[1];
		_direction=direction;
		_trafficLight=trafficLight;
		_image=RoadImageFactory.MakeRoadImage(_direction);
		_roadList=new ArrayList<>();
		_roadPixelList=new ArrayList<>();
		double perRoadPixel;
		if(_direction.equals(Direction.EastToWest)||
				_direction.equals(Direction.WestToEast))
		{
			perRoadPixel=(screenX-Parameters.picturePixel*trafficLight)/(trafficLight+1);
		}
		else
		{
			perRoadPixel=(screenY-Parameters.picturePixel*trafficLight)/(trafficLight+1);
		}
		//add the road into the list
		Random rd=new Random();
		for(int i=0;i<trafficLight;i++)
		{
			_roadList.add(rd.nextDouble()*(Parameters.segmentLengthMax-Parameters.segmentLengthMin)+
					(Parameters.segmentLengthMin));
			_roadList.add(rd.nextDouble()*(Parameters.intersectionLengthMax-Parameters.intersectionLengthMin)+
					Parameters.intersectionLengthMin);
			_roadPixelList.add(perRoadPixel);
			_roadPixelList.add(Parameters.picturePixel);
		}
		_roadList.add(rd.nextDouble()*(myproject.parameters.Parameters.segmentLengthMax-myproject.parameters.Parameters.segmentLengthMin)+
				(myproject.parameters.Parameters.segmentLengthMin));
		_roadPixelList.add(perRoadPixel);
		
		_generateTime=rd.nextDouble()*(Parameters.carGeneratorDelayMax
				-Parameters.carGeneratorDelayMin)+Parameters.carGeneratorDelayMin;
		_generateCount=0;
		
	}
	
	
	@Override
	public double[] getPositon() {
		// TODO Auto-generated method stub
		return this._position;
	}

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return this._screenX;
	}

	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return this._screenY;
	}

	@Override
	public ImageIcon getImage() {
		// TODO Auto-generated method stub
		return this._image;
	}
	public Direction getDirection()
	{
		return this._direction;
	}
	
	public int getRoadIndex(double x)
	{
		int index=0;
		double sum=_roadPixelList.get(index);
		if(_direction.equals(Direction.EastToWest)||
				_direction.equals(Direction.WestToEast))
		{
			if(_screenX<x)
				return _roadPixelList.size()-1;
			else if(x<0)
				return 0;
		}
		else 
		{
			if(_screenY<x)
				return _roadPixelList.size()-1;
			else if(x<0)
				return 0;
		}
		
		while(x-sum>0)
		{
			index++;
			sum+=_roadPixelList.get(index);
		}
		return index;
	}
	/**
	 * 
	 * @return the length list in each road 
	 */
	public ArrayList<Double> getRoadList()
	{
		return this._roadList;
	}
	/**
	 * 
	 * @return the pixel list of the road
	 */
	public ArrayList<Double> getRoadPixelList()
	{
		return this._roadPixelList;
	}
	public int getTrafficLight()
	{
		return this._trafficLight;
	}
	public double getGenerateTime()
	{
		return this._generateTime;
	}
	public double getGenerateCount()
	{
		return this._generateCount;
	}
	public void addGenerateCount()
	{
		this._generateCount++;
	}
	
}
