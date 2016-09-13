package com.gd.txt;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TxtLoader {
	private static String src="c:/TxtLoader.txt";
	
	/**
	 * 
	 * @param src 文本路径
	 * @return 
	 */ 
	public static List<String> readLines(String srcPath){ 
		List<String> list = new ArrayList<String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(srcPath),"gbk"));
			String str;
			while ((str = br.readLine()) != null) {
				list.add(str);
			}
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			throw new RuntimeException("文件加载失败");
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				if(br!=null){
					br.close();
					br = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return list;
	}
	
	/**
	 * 
	 * @param list 文本中的每行数据封装的list
	 * @param args 逗号分隔的数据所表示的属性名
	 * @return 将每行的数据封装成map 并添加到list返回
	 */
	public static List<Map<String,String>> getContentMap(String srcPath,String... args ){
		List<Map<String,String>> tList = new ArrayList<Map<String,String>>();
		Map<String,String> lineMap = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(srcPath)));
			String str;
			//逐行读取文本 
			while ((str = br.readLine()) != null) {
				String[] splits = str.split("\\,");
				int len = 0;
				if((len=splits.length)!=args.length){
					throw new RuntimeException("属性参数个数与文档不匹配， 请检查。。。");
				}
				
				lineMap = new HashMap<String, String>();
				for(int i=0;i<len;i++){
					lineMap.put(args[i], splits[i]);
				}
				tList.add(lineMap);
			}
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			throw new RuntimeException("文件加载失败");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				if(br!=null){
					br.close();
					br = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return tList;
	}
	
	public static void main(String[] args) {
		//List<String> list = readLines(src);
		//System.out.println(list);
		
		List<Map<String, String>> contentMap = getContentMap(src, "id","key","value");
		System.out.println(contentMap);
	}
}
