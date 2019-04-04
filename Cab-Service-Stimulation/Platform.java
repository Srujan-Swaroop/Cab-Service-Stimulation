import java.util.ArrayList;

import iiitb.ess201a7.a7base.*;


public class Platform 
{

// all the methods in this class need to be implemented
	ArrayList<Fleet> list_fleet=new ArrayList<>();
	public Platform() 
	{

	}

	public void addFleet(Fleet f) 
	{
		list_fleet.add(f);

	}

	// for a request defined as a Trip, find the best car by checking each of its fleets
	// and assigns the car to this trip
	public Car assignCar(Trip trip) 
	{
		double min=10000000;
		int j=0;
		double dist=0;
		for(int i=0;i<list_fleet.size();i++)
		{
			Car c=list_fleet.get(i).findNearestCar(trip.getStart() );
			if(c!=null)
			{
				Location l=c.getLocation();
				float x1=l.getX();
				float y1=l.getY();
				float x2=trip.getStart() .getX();
				float y2=trip.getStart() .getY();
				dist=Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
				if(dist<min && c.getStatus()==1)
				{
					min=dist;
					j=i;
				}

			}
		}
		if(list_fleet.get(j).findNearestCar(trip.getStart())!=null)
		{
			list_fleet.get(j).findNearestCar(trip.getStart()).assignTrip(trip);   
			return list_fleet.get(j).findNearestCar(trip.getStart());
		}
		else
		{
			System.out.println("NO CAR AVAILABLE ");
			return null;
		}
   
	}

	// returns list of all cars (in all the fleets) managed by this platform
	public ArrayList<Car> findCars() 
	{
		ArrayList<Car> l=new ArrayList<>();
		for(Fleet item:list_fleet)
		{
			l.addAll(item.getCars());
		}
		return l;

	}
}
