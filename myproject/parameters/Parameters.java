package myproject.parameters;
/**
 * store the parameters of the project
 * @author wenjun
 *
 */
public class Parameters {

	
	public static int timeStep=10;//in million seconds
	public static double runTime=10;//in seconds
	public static int roadColumn=2;
	public static int roadRow=2;
	
	public static double carGeneratorDelayMin=2.0;
	public static double carGeneratorDelayMax=15.0;

	public static double segmentLengthMin=200;
	public static double segmentLengthMax=300;
	public static double intersectionLengthMin=10;
	public static double intersectionLengthMax=25;
	public static double lengthMin=10;
	public static double lengthMax=15;
		
	public static double maxVelocityMin=10;
	public static double maxVelocityMax=30;
	public static double stopDiatanceMin=0.5;
	public static double stopDiatanceMax=5.0;
	public static double brakeDistanceMin=9;
	public static double brakeDistanceMax=10;
	
	public static double greenLightMin=30.0;
	public static double greenLightMax=180.0;
	public static double yellowLightMin=4.0;
	public static double yellowLightMax=5.0;
	
	public static int screenX=1024;
	public static int screenY=768;
	public static double picturePixel=33;
	public static double timeOut=40;//in secounds
	public static String trafficPattern="simple";//alternating
	
	
	
}
