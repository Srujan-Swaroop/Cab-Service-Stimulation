package iiitb.ess201a7.r16060;

import iiitb.ess201a7.a7base.*;

class Car16060 extends Car{

	private int stat = 1;
	private Location carLoc;
	private Trip trip;

	public Car16060(int fid, int speed) {
		super(fid, speed);
	}

	@Override
	public int distSqrd(Location loc) {
		return (int)(Math.pow((loc.getX()-carLoc.getX()), 2) + Math.pow((loc.getY()-carLoc.getY()), 2));
	}

	@Override
	public void setLocation(Location l) {
		// TODO Auto-generated method stub
		carLoc = l;
	}

	@Override
	public Location getLocation() {
		// TODO Auto-generated method stub
		return carLoc;
	}

	@Override
	public void setStatus(int s) {
		// TODO Auto-generated method stub
		stat = s;
	}

	@Override
	public int getStatus() {
		// TODO Auto-generated method stub
		return stat;
	}

	@Override
	public void assignTrip(Trip trip) {
		// TODO Auto-generated method stub
		stat = 2;
		this.trip = trip;
		
	}

	public Trip getTrip()
	{
		if(stat != 1)
		{
			return trip;
		}
		else
		{
			return null;
		}
	}

	@Override
	public Location getStart() {
		// TODO Auto-generated method stub
		if(stat != 1)
		{
			return trip.getStart();
		}
		else
		{
			return null;
		}
	}

	@Override
	public Location getDest() {
		// TODO Auto-generated method stub
		if(stat != 1)
		{
			return trip.getDest();
		}
		else
		{
			return null;
		}
	}

	@Override
	public void updateLocation(double deltaT) {
		// TODO Auto-generated method stub
		
		if(stat == 2){
			if(Math.pow((deltaT*maxSpeed), 2) >= distSqrd(trip.getStart())){
				stat = 3;
				carLoc = trip.getStart();
			}
			else{
				int x = (int) (carLoc.getX() + (deltaT*maxSpeed*((trip.getStart().getX()-carLoc.getX())/(Math.sqrt(distSqrd(trip.getStart()))))));
				int y = (int) (carLoc.getY() + (deltaT*maxSpeed*((trip.getStart().getY()-carLoc.getY())/(Math.sqrt(distSqrd(trip.getStart()))))));
				carLoc.set(x, y);
			}
		}
		if(stat == 3){
			if(Math.pow((deltaT*maxSpeed), 2) >= distSqrd(trip.getDest())){
				stat = 1;
				carLoc = trip.getDest();
			}
			else{
				int x = (int) (carLoc.getX() + (deltaT*maxSpeed*((trip.getDest().getX()-carLoc.getX())/(Math.sqrt(distSqrd(trip.getDest()))))));
				int y = (int) (carLoc.getY() + (deltaT*maxSpeed*((trip.getDest().getY()-carLoc.getY())/(Math.sqrt(distSqrd(trip.getDest()))))));
				carLoc.set(x, y);
			}
			
		}
	}
}
