package cn.zjc.demo;

import java.util.Calendar;
import java.util.Date;

public class CalenderDemo {
	
	public static void main(String[] args) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(new Date());
		
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2013, 9, 3, 13, 52, 0);
		
		System.out.println(cal2.compareTo(cal1));
	}
}
