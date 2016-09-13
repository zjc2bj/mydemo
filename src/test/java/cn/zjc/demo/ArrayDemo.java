package cn.zjc.demo;

public class ArrayDemo {
	public static void main(String[] args) {
		int a[][]=new int[][]{{1,2},{2,3,4,5}};
		//1,2
		//2,3,4,5
		System.out.println(a[0].length);
		System.out.println(a[1].length);
		//a[0][0]=a[0][1]=a[0][2]=a[0][3]=a[1][0]=a[1][1]=a[1][2]=a[1][3]=
		System.out.println(a[0][0]);
		System.out.println(a[0][1]);
		//System.out.println(a[0][2]);
		//System.out.println(a[0][3]);
		System.out.println(a[1][0]);
		System.out.println(a[1][1]);
		System.out.println(a[1][2]);
		System.out.println(a[1][3]);
	}
}
