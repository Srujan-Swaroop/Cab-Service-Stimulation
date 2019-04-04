package iiitb.ess201a7.r16082;
import iiitb.ess201a7.a7base.*;
import java.util.*;
class Car16082 extends Car{
  private Location loc;
  private int status;
  private Trip trip;
  Car16082(int id,int speed){
    super(id,speed);
    loc = new Location(0,0);
    status = 1;
    trip = null;
  }
  public int distSqrd(Location l){
    return (loc.getX()-l.getX())*(loc.getX()-l.getX()) +
     (loc.getY()-l.getY())*(loc.getY()-l.getY());
  }
  public void setLocation(Location l){
    loc = l;
  }
  public Location getLocation(){
    return loc;
  }
  public void setStatus(int s){
    status = s;
  }
  public int getStatus(){
    return status;
  }
  public void assignTrip(Trip t){
    trip = t;
    if(t.getStart().getX()==loc.getX() && t.getStart().getY()==loc.getY()){
      status = 3;
    }
    else{
      status = 2;
    }
  }
  public Trip getTrip(){
    return trip;
  }
  public Location getStart(){
    if(trip==null){
      return null;
    }
    return trip.getStart();
  }
  public Location getDest(){
    if(trip==null){
      return null;
    }
    return trip.getDest();
  }
  private double getSlope(Location dest){
    int y2,y1,x2,x1;
    y2 = dest.getY();
    y1 = loc.getY();
    x2 = dest.getX();
    x1 = loc.getX();
    return (y2-y1)/(1.0*(x2-x1));
  }
  private void move(double dist,double deltaT,Location dest){
		if(loc.getX()==dest.getX()){
			if(loc.getY()<dest.getY()){
        loc.set(loc.getX(),loc.getY()+(int)dist);
      }
      else{
        loc.set(loc.getX(),loc.getY()-(int)dist);
      }
			return;
		}
    double slope = getSlope(dest);
    if(loc.getY()==dest.getY()){
      if(loc.getX()<dest.getX()){
        loc.set(loc.getX()+(int)dist,loc.getY());
      }
      else{
        loc.set(loc.getX()-(int)dist,loc.getY());
      }
    }
    else{
      double dx = Math.sqrt(1/(1+(slope*slope)))*super.getSpeed()*deltaT;
      double dy = Math.abs(dx*slope);
      if(loc.getX()<dest.getX() && loc.getY()<dest.getY()){
        loc.set(loc.getX()+(int)dx,loc.getY()+(int)dy);
      }
      else if(loc.getX()>dest.getX() && loc.getY()<dest.getY()){
        loc.set(loc.getX()-(int)dx,loc.getY()+(int)dy);
      }
      else if(loc.getX()>dest.getX() && loc.getY()>dest.getY()){
        loc.set(loc.getX()-(int)dx,loc.getY()-(int)dy);
      }
      else{
        loc.set(loc.getX()+(int)dx,loc.getY()-(int)dy);
      }
    }
  }
  public void updateLocation(double deltaT){
    if(trip!=null){
      if(status==2){
        Location dest = getStart();
        double dist = super.getSpeed()*deltaT;
				int Dist = (int)dist;
        if(distSqrd(dest)<=Dist*Dist){
          status = 3;
          loc.set(dest.getX(),dest.getY());
        }
        else{
          move(dist,deltaT,dest);
        }
      }
      else if(status==3){
        Location dest = getDest();
        double dist = super.getSpeed()*deltaT;
				int Dist = (int)dist;
        if(distSqrd(dest)<=Dist*Dist){
          status = 1;
          loc.set(dest.getX(),dest.getY());
        }
        else{
          move(dist,deltaT,dest);
        }
      }
    }
  }
}
