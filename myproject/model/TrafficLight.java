package myproject.model;

import myproject.enums.LightColor;
/**
 * The model of traffic light
 * @author wenjun
 *
 */
public interface TrafficLight extends Model{	
	public LightColor getColor();
}
