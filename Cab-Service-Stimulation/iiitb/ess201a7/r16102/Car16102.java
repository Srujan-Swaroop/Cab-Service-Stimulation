package iiitb.ess201a7.r16102;

import iiitb.ess201a7.a7base.*;

import java.lang.*;


public class Car16102 extends Car {

	static int id=1;

	private Location location;
	private int status=1;
	private Trip trip;


	public Car16102(int speed)
	{
		super(161020+id,speed);
		id++;
	}

	@Override
	public int distSqrd(Location loc)
	{
		return (int) (Math.pow(loc.getX()-location.getX(),2)+ Math.pow(loc.getY()-location.getY(),2));
	}

	@Override
	public void setLocation(Location l)

	{
		// TODO Auto-generated method stub
		location=l;
	}

	@Override
	public Location getLocation()
	{
		// TODO Auto-generated method stub

		return location;
	}

	@Override
	public void setStatus(int s)
	{
		status=s;
		// TODO Auto-generated method stub

	}

	@Override
	public int getStatus() {
		// TODO Auto-generated method stub
		return status;
	}

	@Override
	public void assignTrip(Trip trip)
	{
		this.trip=trip;
		status=2;

	}
	@Override
	public Trip getTrip()
	{
		if(status!=1)
			{
			return trip;
		}
		return null;
	}
	@Override
	public Location getStart()
	 {
		if(status!=1) { return trip.getStart();}
		return null;
	}

	@Override
	public Location getDest()
	{
	 if(getStatus() !=1) { return trip.getDest();}
	 return null;
	}

	@Override
	public void updateLocation(double deltaT) {
		// TODO Auto-generated method stub
		if(status==2){
			if(Math.pow((deltaT*maxSpeed), 2)>=distSqrd(trip.getStart())){
				status = 3;
				location = trip.getStart();
			}
			else{
				int x = (int) (location.getX() + (deltaT*maxSpeed*((trip.getStart().getX()-location.getX())/(Math.sqrt(distSqrd(trip.getStart()))))));
				int y = (int) (location.getY() + (deltaT*maxSpeed*((trip.getStart().getY()-location.getY())/(Math.sqrt(distSqrd(trip.getStart()))))));
				location.set(x, y);
			}
		}
		else {
			if(status==3){
				if(Math.pow((deltaT*maxSpeed), 2)>=distSqrd(trip.getDest())){
					status = 1;
					location = trip.getDest();
			}
			else{
				int x = (int) (location.getX() + (deltaT*maxSpeed*((trip.getDest().getX()-location.getX())/(Math.sqrt(distSqrd(trip.getDest()))))));
				int y = (int) (location.getY() + (deltaT*maxSpeed*((trip.getDest().getY()-location.getY())/(Math.sqrt(distSqrd(trip.getDest()))))));
				location.set(x, y);
			}

		}
		}

	}


		// TODO Auto-generated method stub

	}
