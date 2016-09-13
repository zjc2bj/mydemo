package cn.zjc.demo;

import org.junit.Test;

public class FinalDemo {
	
	@Test
	public void testFinal(){
		run(new Object());
	}
	
	public void run(final Object obj){
		obj.i += 1; 
	}
}


