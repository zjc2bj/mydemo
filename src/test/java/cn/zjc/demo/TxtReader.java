package cn.zjc.demo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class TxtReader {

	/*public static void main(String[] args) throws Exception {
		BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream("C://costtime.txt")));
		StringBuffer strBuffer = new StringBuffer();
		String lineTxt = null;
		while ((lineTxt = bufReader.readLine()) != null) {
			strBuffer.append(lineTxt);
		}
		
		String txtValue = strBuffer.toString();
		String[] split = txtValue.split("\\,");
		int costTime = 0;
		for(String str:split){
			costTime = Integer.parseInt(str)+costTime;
		}
		System.out.println("costTime(S)="+costTime);
		
		String costhor = costTime/3600<10?"0"+costTime/3600 : costTime/3600+"";
		String costmin = (costTime%3600)/ 60<10?"0"+(costTime%3600)/ 60 : (costTime%3600)/ 60+"";
		String costsec = costTime%60<10?"0"+costTime%60 : costTime%60+"";
		String costTotalHour = costhor   +":"+	costmin	 +":"+ costsec;
		System.out.println("耗时："+costTotalHour);
	}*/
	public static void main(String[] args) throws Exception {
		BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream("C://costtime.txt")));
		StringBuffer strBuffer = new StringBuffer();
		String lineTxt = null;
		while ((lineTxt = bufReader.readLine()) != null) {
			strBuffer.append(lineTxt);
		}
		
		String txtValue = strBuffer.toString();
		String[] split = txtValue.split("\\,");
		int costTime = 0;
		for(String str:split){
			String[] costime = str.split("\\:");
			int oneCost = Integer.parseInt(costime[0])*60*60  +  Integer.parseInt(costime[1])*60  +Integer.parseInt(costime[2]);
			System.out.println(oneCost);
			costTime = costTime + oneCost;
		}
		System.out.println("costTime(S)="+costTime);
		
		String costhor = costTime/3600<10?"0"+costTime/3600 : costTime/3600+"";
		String costmin = (costTime%3600)/ 60<10?"0"+(costTime%3600)/ 60 : (costTime%3600)/ 60+"";
		String costsec = costTime%60<10?"0"+costTime%60 : costTime%60+"";
		String costTotalHour = costhor   +":"+	costmin	 +":"+ costsec;
		System.out.println("耗时："+costTotalHour);
	}
}
