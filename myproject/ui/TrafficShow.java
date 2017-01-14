package myproject.ui;
import java.awt.*; 
import java.awt.event.*; 
import java.util.ArrayList;
import java.util.Iterator;


import javax.swing.JPanel;
import javax.swing.Timer;


import myproject.enums.Direction;
import myproject.enums.LightColor;
import myproject.model.Car;
import myproject.model.Model;
import myproject.model.Road;
import myproject.model.RoadFactory;
import myproject.model.TrafficLight;
import myproject.model.TrafficLightFactory;
import myproject.parameters.Parameters;
import myproject.model.CarFactory;;
/**
 * the Traffic simulator of the project
 * @author wenjun
 *
 */
public class TrafficShow extends JPanel{
	public static TrafficShow singleton;
	private Timer timer;
	private ArrayList<ArrayList<Car>> cars;
	private ArrayList<Road> road;
	private ArrayList<TrafficLight> light;
		
	private int screenX=Parameters.screenX;//screen resolution
	private int screenY=Parameters.screenY;
	private int roadColumn=Parameters.roadColumn;
	private int roadRow=Parameters.roadRow;
	private double time=0;//time count in million seconds
	
	/**
	 * init everything make the road, traffic light, and cars
	 * 
	 */
	public TrafficShow()
	{
		super();
		if(singleton==null)
			singleton=this;

		timer=new Timer(0,new TrafficListener());
		cars=new ArrayList<ArrayList<Car>>();
		road=new ArrayList<Road>();
		light=new ArrayList<TrafficLight>();
		//Add the road
		//from east to west
		double[] pos=new double[2];
		pos[0]=0;
		for(int i=0;i<roadRow;i++)
		{
			pos[1]=screenY/(1+roadRow)*(1+i);
			if(Parameters.trafficPattern.equals("simple"))
			{
				
				road.add(RoadFactory.newEastToWestRoad(screenX, Parameters.picturePixel,
						pos, roadColumn));
			}
			else
			{
				if((i+1)%2==0)
				{
					road.add(RoadFactory.newEastToWestRoad(screenX, Parameters.picturePixel,
							pos, roadColumn));
				}
				else
				{
					road.add(RoadFactory.newWestToEastRoad(screenX, Parameters.picturePixel,
							pos, roadColumn));
				}
			}

		}
		//from north to south
		pos[1]=0;
		for(int j=0;j<roadColumn;j++)
		{
			pos[0]=screenX/(1+roadColumn)*(1+j);
			if(Parameters.trafficPattern.equals("simple"))
			{
				road.add(RoadFactory.newNorthToSouthRoad(Parameters.picturePixel, screenY,
					pos, roadRow));
			}
			else
			{
				if((j+1)%2==0)
				{
					road.add(RoadFactory.newNorthToSouthRoad(Parameters.picturePixel, screenY,
							pos, roadRow));
				}
				else
				{
					road.add(RoadFactory.newSouthToNorthRoad(Parameters.picturePixel, screenY,
							pos, roadRow));
				}
			}
		}
		
		//Add Trafficlight
		for(int i=1;i<=roadColumn;i++)
		{
			for(int j=1;j<=roadRow;j++)
			{
				double[] trafficPos=new double[2];
				trafficPos[0]=screenX/(roadColumn+1)*i;
				trafficPos[1]=screenY/(roadRow+1)*j;
				light.add(TrafficLightFactory.newTrafficLight(trafficPos));
			}
		}
		
		//add car
		for(int i=0;i<roadColumn+roadRow;i++)
		{		
			cars.add(new ArrayList<Car>());
		}
		
		this.setSize(screenX,screenY);
	    this.setPreferredSize(new Dimension(screenX,screenY));
	    timer.start();
	}
	/**
	 * paint everything in the road
	 * @param array
	 * @param ts
	 * @param g
	 */
	private void paintArrayList(ArrayList array,TrafficShow ts,Graphics g)
	{
		if(array.size()!=0)
		{
			Iterator<Model> it=array.iterator();
			while(it.hasNext())
			{
				Model x=it.next();
				double[] pos=x.getPositon();
				if(pos[0]<0||pos[0]>screenX||pos[1]<0||pos[1]>screenY)
				{
					array.remove(it);
				}
				else
				{
					g.drawImage(x.getImage().getImage(),(int)pos[0], (int)pos[1], 
							(int)x.getWidth(),(int)x.getHeight(),ts);
				}
			}
		}
		
	}
	/**
	 * change the speed of car
	 * @param carList
	 */
	public void changeSpeed(ArrayList<Car> carList)
	{
		Car frontCar=null;
		Iterator<Car> itCar=carList.iterator();
		while(itCar.hasNext())
		{
			Car car=itCar.next(); 
			double[] pos=car.getPositon();
			double[] velocity=car.getVelocity();
			int i;
			if(car.getDirection().equals(Direction.EastToWest)||
					car.getDirection().equals(Direction.WestToEast))//means the car go for east to west
			{
				i=0;//the velocity in x direction would be useful
			}
			else
			{
				i=1;
			}
			Iterator<Road> itRoad=road.iterator();
			Road compRoad = null;
			while(itRoad.hasNext())//find the road
			{
				compRoad=itRoad.next();
				if(!car.getDirection().equals(compRoad.getDirection()))
				{
					continue;
				}
				if(pos[1-i]==compRoad.getPositon()[1-i])
				{
					break;
				}
					
			}
			int index=compRoad.getRoadIndex(pos[i]);
			car.setIndexOfRoad(index%2==0?index:index-1);

			car.setScale(compRoad.getRoadPixelList().get(index),
					compRoad.getRoadList().get(index));

			double distanceToObstacle=15;
			double realVelocity=10;
			
			if(frontCar!=null&&frontCar.getIndexOfRoad()==car.getIndexOfRoad())
			{
				//two cars in the same road
				//System.out.println(index);				
				distanceToObstacle=compRoad.getRoadList().get(index)*
						(frontCar.getPositon()[i]-car.getPositon()[i])
						/compRoad.getRoadPixelList().get(index)
						-car.getLength();
			}
			else
			{
				TrafficLight tl = null;
				int x;
				for(x=0;x<this.roadColumn*this.roadRow;x++)
				{					
					if(car.getPositon()[i]<light.get(x).getPositon()[i]&&
							car.getPositon()[1-i]==light.get(x).getPositon()[1-i])
					{
						break;
					}				
				}
				if(x<this.roadColumn*this.roadRow)
				{
					tl=light.get(x);
				
					
					LightColor compColor=LightColor.GREEN;
					if(i==0)
					{
						compColor=LightColor.GREEN;
					}
					else
					{
						compColor=LightColor.RED;
					}
					if(tl.getColor()!=compColor)//not accessible
					{
						distanceToObstacle=compRoad.getRoadList().get(index)*
								(tl.getPositon()[i]-car.getPositon()[i])
								/compRoad.getRoadPixelList().get(index)
								-car.getLength()*1.5;
					}
					else					
					{
						distanceToObstacle=100;
					}
				}
				else
				{
					distanceToObstacle=100;
				}
			}
			
			double changeVelocity=0;

			realVelocity=(car.getMaxVelocity()/(car.getBrakeDistance()-car.getStopDistance()))
					*(distanceToObstacle-car.getStopDistance());
			realVelocity=Math.max(0.0, realVelocity);
			realVelocity=Math.min(car.getMaxVelocity(), realVelocity);
			//change the velocity into pixel
			if(realVelocity!=0)
			{
				changeVelocity=compRoad.getRoadPixelList().get(index)/
						(compRoad.getRoadList().get(index)/realVelocity)*
						Parameters.timeStep/1000.0;
			}
			else
			{
				changeVelocity=0;
			}

			
			//change the get max velocity to the  
			car.setVelocity(changeVelocity);
			frontCar=car;
			
		}
	}
	/**
	 * generate the cars in the road
	 * @param car
	 * @param i
	 */
	public void generateCar(ArrayList<Car> car,int i)
	{
		Direction direction=Direction.EastToWest;
		if(i-roadRow<0)
		{
			if(Parameters.trafficPattern.equals("simple"))
			{
				direction=Direction.EastToWest;
			}
			else
			{
				if(i%2==0)
				{
					direction=Direction.EastToWest;
				}
				else
				{
					direction=Direction.WestToEast;
				}
			}
		}
		else
		{
			if(Parameters.trafficPattern.equals("simple"))
			{
				direction=Direction.NorthToSouth;
			}
			else
			{
				if((i-roadRow)%2==0)
				{
					direction=Direction.NorthToSouth;
				}
				else
				{
					direction=Direction.SouthToNorth;
				}
			}
		}
		double[] temp=road.get(i).getPositon();
		
		car.add(CarFactory.newCar(temp, direction));
	}
	
	@Override
    public   void  paintComponent(Graphics page) {			
       super.paintComponent(page);

       paintArrayList(road,this,page);
       
       for(int i=0;i<roadColumn+roadRow;i++)
       {
    	   if(time/1000/(road.get(i).getGenerateTime())>= road.get(i).getGenerateCount())
    	   {
    		   road.get(i).addGenerateCount();
    		   generateCar(cars.get(i),i);    		   
    	   }
       }
       
       Iterator<ArrayList<Car>> it=cars.iterator();

       while(it.hasNext())
       {
    	   ArrayList<Car> car=it.next();
    	   paintArrayList(car,this,page);
    	   changeSpeed(car);
    	   
       }
       
       paintArrayList(light,this,page);
       try {
		Thread.sleep(Parameters.timeStep);
		time+=Parameters.timeStep;
       } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}
	private   class  TrafficListener  implements  ActionListener {
		public   void  actionPerformed(ActionEvent e) {
			 repaint();
		}
	}

}
