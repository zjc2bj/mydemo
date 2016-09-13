package cn.zjc.demo;

import java.util.List;

class Object{
	public int i = 0;
	public void add(List<String> list,int i){
		i++;
		System.out.println("i="+i);
		list.add("bb");
	}
}
