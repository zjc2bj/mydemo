package com.gd.xml;

import java.util.ArrayList;
import java.util.List;

public class OverrideDemo {
	
	public void test(String id){
		System.out.println(id);
	}
	
	public void test(int i){
		System.out.println(i);
	}
	
	public void test(long lon){
		System.out.println(lon);
	}
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		for(int i=0;i<=23;i++){
			list.add(i<10?"0"+i+":05:00":i+":05:00");
			list.add(i<10?"0"+i+":20:00":i+":20:00");
			list.add(i<10?"0"+i+":35:00":i+":35:00");
			list.add(i<10?"0"+i+":50:00":i+":50:00");
		}
		System.out.println(list);
	}
	
}
