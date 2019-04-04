package iiitb.ess201a7.r16121;

import iiitb.ess201a7.a7base.*;
import java.util.ArrayList;

public class Fleet16121 extends Fleet {
    int idno;
    ArrayList<Car16121>fleetofc=new ArrayList();
    	public Fleet16121(String colour) {
            super(16121,colour);
            idno=1;
        }

	@Override
	public void addCar(int speed) {
            fleetofc.add(new Car16121((super.getId()*100)+idno,speed));
            idno+=1;

	}


	@Override
	public Car findNearestCar(Location loc) {
		int mindistance=Integer.MAX_VALUE;
                Car nearest=null;
                for(int i=0;i<fleetofc.size();i++)
                {
                    if((fleetofc.get(i).getStatus()==1)&&dist(fleetofc.get(i),loc)<mindistance)
                    {
                     nearest=fleetofc.get(i);
                     mindistance=dist(fleetofc.get(i),loc);
                    }

                }
        return nearest;
	}
    @Override
   public ArrayList<Car16121> getCars() {
        return  fleetofc;
    }
   private int dist(Car c,Location lo)
   {
       return (int) Math.sqrt((c.getLocation().getX()-lo.getX())*(c.getLocation().getX()-lo.getX()) +(c.getLocation().getY()-lo.getY())*(c.getLocation().getY()-lo.getY()) );
   }
}
