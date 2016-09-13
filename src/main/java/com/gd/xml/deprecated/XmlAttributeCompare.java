package com.gd.xml.deprecated;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * 获取配置参数    比较多组文件	：Service层
 * @author zjc
 *
 */
@Deprecated 
public class XmlAttributeCompare {
	
	public static void main(String[] args) {
		try {
			//配置文件的解析器
			XmlAnalysisUtils utils = FileConfigLoader.utils;
			
			// 获取配置文件fileconfig中的两个文件的路径
			String filepath1 = utils.getContentByTagName(FileConfigConstant.FILEPATH1);
			String filepath2 = utils.getContentByTagName(FileConfigConstant.FILEPATH2);
			
			XmlAnalysisUtils utils1 = new XmlAnalysisUtils(filepath1);
			XmlAnalysisUtils utils2 = new XmlAnalysisUtils(filepath2);
			
			Map<String, List<String>> configMap = FileConfigLoader.getConfigMap();
			//System.out.println(configMap);
			
			//比较后相同的信息集合
			List<String> resultList1 = new ArrayList<String>();
			//比较后不相同的信息集合
			List<String> resultList2 = new ArrayList<String>();
			
			//比较两个xml中的参数集是否相等
			for(Entry<String, List<String>> entry:configMap.entrySet()){
				String key = entry.getKey();
				List<String> value = entry.getValue();
				if(value.size()>0){
					for(String str:value){
						List<String> list1 = utils1.getText(key, str);
						List<String> list2 = utils2.getText(key, str);
						if(list1.equals(list2)){
							resultList1.add(key+"节点的"+str+"属性"+"数据一致");
						}else{
							resultList2.add(key+"节点的"+str+"属性"+"数据不一致");
						}
					}
				}else{
					List<String> list1 = utils1.getText(key, null);
					List<String> list2 = utils2.getText(key, null);
					if(list1.equals(list2)){
						resultList1.add(key+"节点的数据一致");
					}else{
						resultList2.add(key+"节点的数据不一致");
					}
				}
			}
			//将结果输出到指定文件
			/*LogWriter.log(resultList1);
			LogWriter.log("\r\n");
			LogWriter.log(resultList2);*/
			
			//关闭LogWriter的输出流
			//LogWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}