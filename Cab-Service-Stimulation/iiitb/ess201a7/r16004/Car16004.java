package iiitb.ess201a7.r16004;

import iiitb.ess201a7.a7base.*;
import java.util.*;
import java.lang.*;

public class Car16004 extends Car
{
	private Location location_a, startPt, destPt;
	private int status;
	private Trip T;
	public Car16004(int fid, int speed)
	{
		// this.fid = fid;
		// this.maxspeed = speed;
		super(fid, speed);
		status = Idle;
		location_a = new Location(30, 30);
	}

	//Sets the location according to the given argument
	@Override
	public void setLocation(Location l)
	{
		location_a = l;
	}

//returns the squared value of distance.
	@Override
	public int distSqrd(Location l)
	{
		int x2 = location_a.getX();
		int y2 = location_a.getY();
		int x1 = l.getX();
		int y1 = l.getY();
		int dist = (int)(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
		return dist;
	}


//Returns the coordinates of the requested location.
	@Override
	public Location getLocation()
	{
		return location_a;
	}

//Sets the current status of the car. ex Idle or Booked or OnTrip
	@Override
	public void setStatus(int s)
	{
		status = s;
	}

//Returns the current status of the car.
	@Override
	public int getStatus()
	{
		return status;
	}

//If the car is idle, then it is assigned a new trip on request.
	@Override
	public void assignTrip(Trip trip)
	{
		// if(status == Idle)
			startPt = trip.getStart();
			destPt = trip.getDest();
			this.setStatus(Booked);
			System.out.println("Assign");
	}


	@Override
	public Trip getTrip()
	{
		return T;
	}

//If the car is Idle, then return the car status as null.
//When it reaches the start location, return the startPt coordinates
	@Override
	public Location getStart()
	{
		if(status == Idle)
			return null;
		else
			return startPt;
	}

//If the car is Idle, then return the location as null.
//When it reaches the destination location, return the destPt coordinates
	@Override
	public Location getDest()
	{
		if(status == Idle)
			return null;
		else
			return destPt;
	}

//Updates the location after a time interval of deltaT,
//i.e very small time interval.
	@Override
	public void updateLocation(double deltaT)
	{
		double newX;
		double newY;
		int speed = this.getSpeed();

		if(status == OnTrip)
		{
			double distance = speed * deltaT;
			double hypot = destPt.getY() - location_a.getY();
			double base = destPt.getX() - location_a.getX();
			double dist = Math.sqrt(this.distSqrd(destPt));

			if(dist <= distance)
			{
				// newX = startPt.getX();
				// newY = startPt.getY();
				newX = destPt.getX();
				newY = destPt.getY();

				location_a.set((int)newX, (int)newY);
				this.setStatus(Idle);
			}
			else
			{
				newX = location_a.getX() + (distance * base)/dist;
				newY = location_a.getY() + (distance * hypot)/dist;
				location_a.set((int)newX, (int)newY);
			}

		}
		else if(status == Booked)
		{
			double distance = speed * deltaT;
			double base = startPt.getX() - location_a.getX();;
			double hypot = startPt.getY() - location_a.getY();
			double dist = Math.sqrt(distSqrd(startPt));

			if(dist <= distance)
			{
				newX = startPt.getX();
				newY = startPt.getY();
				// newX = destPt.getX();
				// newY = destPt.getY();

				location_a.set((int)newX, (int)newY);
				setStatus(OnTrip);
			}
			else
			{
				newY = location_a.getY() + (distance * hypot)/dist;
				newX = location_a.getX() + (distance * base)/dist;
				location_a.set((int)newX, (int)newY);
			}
		}
	}
}
