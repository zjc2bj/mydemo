package cn.zjc.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryDemo {
	public static void main(String[] args) {
		try {
			ArrayList<List> list = new ArrayList<List>();
			ArrayList<List> list2 = new ArrayList<List>();
			ArrayList<String> list3 = new ArrayList<String>();
			
			Map<String,String> map1 = new HashMap<String,String>();
			Map<String,String> map2 = new HashMap<String,String>();
			Map<String,String> map3 = null;
			
			
			File file =  new File("d:/abc.txt");
			if(file.exists()){
				file.delete();
			}
			file.createNewFile();
			long length1 = file.length();
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(list);
			oos.flush();
			 long length2 = file.length();
			 System.out.println("list大小="+(length2-length1));
			 
			 oos.writeObject(list2);
			 oos.flush();
			 long length3 = file.length();
			 System.out.println("list大小="+(length3-length2));

			 oos.writeObject(list3);
			 oos.flush();
			 long length4 = file.length();
			 System.out.println("list大小="+(length4-length3));

			 oos.writeObject(map1);
			 oos.flush();
			 long length5 = file.length();
			 System.out.println("hashMap大小="+(length5-length4));

			 oos.writeObject(map2);
			 oos.flush();
			 long length6 = file.length();
			 System.out.println("hashMap大小="+(length6-length5));
			 
			 oos.writeObject(map3);
			 oos.flush();
			 long length7 = file.length();
			 System.out.println("hashMap大小="+(length7-length6));
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
