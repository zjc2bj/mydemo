package cn.zjc.demo;

import java.util.ArrayList;
import java.util.List;

public class LocalVariableDemo {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		Integer i = new Integer(100);
		list.add("aa");
		
		new Object().add(list, i);
		System.out.println(list);
		System.out.println(i);
	}
}


