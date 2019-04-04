package iiitb.ess201a7.r16121;

import iiitb.ess201a7.a7base.*;
import java.lang.*;
/*
Check with car class and check for base variables..

*/
public class Car16121 extends Car {
        private Location locs;
        private int status=1;
        private Trip trip1;
	public Car16121(int id,int speed) {
		super(id,speed);
            //    locs=new Location(0,0);
	}


  @Override
  public int distSqrd(Location l){
    {
      int x2 = locs.getX();
      int y2 = locs.getY();
      int x1 = l.getX();
      int y1 = l.getY();
      int dist = (int)(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
      return dist;
    }
  }
	@Override
	public void setLocation(Location l) {
		locs=l;

	}

	@Override
	public Location getLocation() {
		return this.locs;
	}

	@Override
	public void setStatus(int s) {
	status = s;
	}

	@Override
	public int getStatus() {
            return this.status;
	}

	@Override
	public void assignTrip(Trip trip) {
		this.trip1=trip;
                this.status=2;

	}

	@Override
	public Location getStart() {
		return this.trip1.getStart();
	}

	@Override
	public Location getDest() {
		return this.trip1.getDest();
	}

	@Override
	public void updateLocation(double deltaT) {
	double remaining_time=0,dx,dy,dist;
            if(status==2)
                {
                    dy=(trip1.getStart().getY()-locs.getY());
                    dx=(trip1.getStart().getX()-locs.getX());
                    dist=Math.pow(dy,2)+Math.pow(dx,2);
                    dist=Math.pow(dist,0.5);
                    if(dist>(this.maxSpeed*deltaT))
                    {
                     locs.set(locs.getX()+ (int)(dx*this.maxSpeed*deltaT/dist) ,locs.getY()+(int)(dy*this.maxSpeed*deltaT/dist));
                    }
                    else
                    {
                        remaining_time=0.5-(dist/this.maxSpeed);
                        locs.set(locs.getX()+(int)(dx),locs.getY()+(int)(dy));
                        status=3;
                    }
                }
            if(status==3)
            {
                if(remaining_time==0)
                {
                    dy=(trip1.getDest().getY()-locs.getY());
                    dx=(trip1.getDest().getX()-locs.getX());
                    dist=Math.pow(dy,2)+Math.pow(dx,2);
                    dist=Math.pow(dist,0.5);
                    if(dist>(this.maxSpeed*deltaT))
                    {
                     locs.set(locs.getX()+ (int)(dx*this.maxSpeed*deltaT/dist) ,locs.getY()+(int)(dy*this.maxSpeed*deltaT/dist));
                    }
                    else
                    {
                        locs.set(locs.getX()+(int)(dx),locs.getY()+(int)(dy));
                        status=1;
                    }
                }
                else if(remaining_time>0)
                {
                    dy=(trip1.getDest().getY()-locs.getY());
                    dx=(trip1.getDest().getX()-locs.getX());
                    dist=Math.pow(dy,2)+Math.pow(dx,2);
                    dist=Math.pow(dist,0.5);
                    if(dist>(this.maxSpeed*remaining_time))
                    {
                     locs.set(locs.getX()+ (int)(dx*this.maxSpeed*deltaT/dist) ,locs.getY()+(int)(dy*this.maxSpeed*deltaT/dist));
                    }
                    else
                    {
                        //remaining_time=0.5-(dist/this.maxSpeed);
                        locs.set(locs.getX()+(int)(dx),locs.getY()+(int)(dy));
                        status=1;
                    }
                }
            }
                }

    @Override
    public Trip getTrip() {
        return trip1;
    }




}
