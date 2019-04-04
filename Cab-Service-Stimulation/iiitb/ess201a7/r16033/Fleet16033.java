package iiitb.ess201a7.r16033;

import iiitb.ess201a7.a7base.*;
import java.util.ArrayList;

public class Fleet16033 extends Fleet 
{
	
	public Fleet16033(String colour) 
	{
		super(16033,colour);
	}

	int i=0;
	private ArrayList<Car> list_car=new ArrayList<>();

	@Override
	public void addCar(int speed) 
	{
		i=i+1;
		Car c=new Car16033(1603300+i,speed);
		// Location l=new Location(0,0);
		// c.setLocation(l);
		c.setStatus(1);
		list_car.add(c);
	}

	@Override
	public Car findNearestCar(Location loc) 
	{
		int k=-1;
		int index=k;
		double dist=0;
		double min_dist=1000000;
		for(Car item:list_car)
		{
			Location l=item.getLocation();
			float x1=l.getX();
			float y1=l.getY();
			float x2=loc.getX();
			float y2=loc.getY();
			dist=Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
			k=k+1;
			if(dist<min_dist && item.getStatus()==1)
			{
				// System.out.println("fleet nearest :"+item.getStatus());
				min_dist=dist;
				index=k;
			}
		}
		if(index==-1)
		{
			return null;
		}
		else
		{
			return list_car.get(index);
		}
		
	}
	
	@Override
	public  ArrayList<? extends Car> getCars()
	{
		return list_car;
	}
}
