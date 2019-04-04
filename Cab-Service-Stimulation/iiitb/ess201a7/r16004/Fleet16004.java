package iiitb.ess201a7.r16004;

import java.util.*;
import iiitb.ess201a7.a7base.*;
import java.lang.*;

public class Fleet16004 extends Fleet
{
	private ArrayList<Car> carList;
	private int cnt;
	public Fleet16004(String colour)
	{
		super(16004, colour);
		carList = new ArrayList<Car>();
		cnt = 0;
	}

	@Override
	public void addCar(int speed)
	{
		cnt++;
		String id = String.valueOf(this.getId()) + String.valueOf(cnt);
		int car_Id = Integer.parseInt(id);
		Car car_i = new Car16004(car_Id, speed);
		carList.add(car_i);
	}

	@Override
	public Car findNearestCar(Location loc)
	{
		double min = 100000;
		int a = 97644;

		for(int i = 0; i < cnt; i++)
		{
			Car car_i = carList.get(i);

			if(car_i.getStatus() == 1)
			{
				Location location_a = car_i.getLocation();
				int x2 = loc.getX();
				int y2 = loc.getY();
				int x1 = location_a.getX();
				int y1 = location_a.getY();
				double distance = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
				if(distance < min)
				{
					min = distance;
					a = i;
				}
			}
		}

		if(min != 100000 && a != 97644)
		{
			return carList.get(a);
		}
		return null;
	}

	@Override
	public ArrayList<? extends Car> getCars()
	{
		// System.out.println(carList.size());
		return carList;
	}
}
