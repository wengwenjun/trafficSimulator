package myproject.model;

import myproject.enums.Direction;
/**
 * The factory of making different direction of road
 * @author wenjun
 *
 */
public class RoadFactory {
	public static Road newEastToWestRoad(double screenX,double screenY,
			double[] position,int trafficLight)
	{
		return new RoadObj(screenX,screenY,position,Direction.EastToWest,trafficLight);
		
	}
	public static Road newWestToEastRoad(double screenX,double screenY,
			double[] position,int trafficLight)
	{
		return new RoadObj(screenX,screenY,position,Direction.WestToEast,trafficLight);
		
	}
	public static Road newNorthToSouthRoad(double screenX,double screenY,
			double[] position,int trafficLight)
	{
		return new RoadObj(screenX,screenY,position,Direction.NorthToSouth,trafficLight);
		
	}
	public static Road newSouthToNorthRoad(double screenX,double screenY,
			double[] position,int trafficLight)
	{
		return new RoadObj(screenX,screenY,position,Direction.SouthToNorth,trafficLight);
		
	}
}
