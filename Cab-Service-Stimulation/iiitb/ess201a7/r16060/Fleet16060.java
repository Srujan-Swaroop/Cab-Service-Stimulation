package iiitb.ess201a7.r16060;

import iiitb.ess201a7.a7base.*;
import java.util.*;


public class Fleet16060 extends Fleet {

	private ArrayList<Car16060> carsList = new ArrayList<Car16060>();
	private int carId = 1606000;
	//private int fids = 16060;
	//private int x = 0, y = 0;
	
	
	public Fleet16060(String colour) {
		super(16060, colour);
	}

	@Override
	public void addCar(int speed) {
		Car16060 c = new Car16060(carId, speed);
		carsList.add(c);
		carId = carId + 1;
		//c.setLocation(new Location(x, y));
		//x+=30;
		//y+=50;
	}
	
	@Override
	public Car findNearestCar(Location loc) {
		int minDist = Integer.MAX_VALUE;
		int count = 0;
		int minInd = 0;
		for(Car c: carsList)
		{
			if(c.getStatus() == 1)
			{	
				//Location carLoc = c.getLocation();
				int carLocDist;
				carLocDist = c.distSqrd(loc);//sqrt(pow((carLoc.getX - loc.getX), 2) + pow((carLoc.getY - loc.getY), 2);
				if(carLocDist < minDist)
				{
					minDist = carLocDist;
					minInd = count;
				}
			}
			count+=1;
		}
		return carsList.get(minInd);
	}/*
	@Override
	public Car findNearestCar(Location loc) {
		Car min = null;
		double min_distance = Integer.MAX_VALUE;
		for(Car c : carsList){
			if(c.getStatus()==1){
				if(min_distance>c.distSqrd(loc)){
					min = c;
					min_distance = c.distSqrd(loc);
				}
			}
		}
		return min;
	}*/

	public ArrayList<? extends Car> getCars()
	{
		return carsList;
	}
}
