package iiitb.ess201a7.r16102;

import java.lang.*;
import java.util.*;

import iiitb.ess201a7.a7base.*;

public class Fleet16102 extends Fleet {

	double min=999999;
	static int id=16102;
	private ArrayList<Car> cars =new ArrayList<Car>();

	public Fleet16102(String colour)
	{

		super(id,colour);
	}

	@Override
	public void addCar(int speed)
	{

		cars.add(new Car16102(speed));
		
	}

	@Override
	public Car findNearestCar(Location loc)
	{
		Car temp = null;
		double min=Double.MAX_VALUE;
		for(Car c:cars)
		{
            if(c.getStatus()==1){if(Distance(c.getLocation(),loc)<min){min=Distance(c.getLocation(),loc);temp=c;}}
		}

		return temp;

	}
	@Override
	public ArrayList<Car> getCars()
	{
		return cars;
	}

	private double Distance(Location l1, Location l2)
	{
		return Math.sqrt(Math.pow(l1.getX()-l2.getX(),2)+ Math.pow(l1.getY()-l2.getY(),2));
	}
}
