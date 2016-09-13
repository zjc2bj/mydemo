package com.gd.xml;

public class ExceptionDemo {

	public void run(){
		try{
			int i = 1/0;
		}catch(Exception e){
			throw new RuntimeException("run 调用失败");
		}
	}
	
	public static void main(String[] args) {
		try{
			new ExceptionDemo().run();
		}catch(Exception e){
			throw new RuntimeException("测试失败");
		}
		//new ExceptionDemo().run();
	}
}
