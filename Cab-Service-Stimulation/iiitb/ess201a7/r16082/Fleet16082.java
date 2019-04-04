package iiitb.ess201a7.r16082;
import java.util.* ;
import iiitb.ess201a7.a7base.*;
public class Fleet16082 extends Fleet{
  private int n;
  private ArrayList<Car> cars;
  public Fleet16082(String col){
    super(16082,col);
    cars = new ArrayList<Car>();
    n = 160820;
  }
  public void addCar(int speed){
    n++;
    Car temp = new Car16082(n,speed);
    cars.add(temp);
  }
  public Car findNearestCar(Location loc){
    Car ret = null;
    for(Car c:cars){
      if(c.getStatus()==1){
        if(ret==null){
          ret = c;
        }
        else if(ret.distSqrd(loc)>c.distSqrd(loc)){
          ret = c;
        }
      }
    }
    return ret;
  }
  public ArrayList<? extends Car> getCars(){
    return cars;
  }
}
