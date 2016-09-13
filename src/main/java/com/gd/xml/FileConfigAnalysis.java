package com.gd.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * 对fileconf.xml解析--类似于DAO层
 * 		fileconf.xml--类似于DB层
 * @author zjc
 *
 */
public class FileConfigAnalysis {
	public static Document document;
	public static String outputPath;//默认输出路径
	public static String configPath = "conf/fileconfig2.xml";
	//加载配置文件
	static{
		String xmlPath = FileConfigAnalysis.class.getClassLoader().getResource(configPath).toString();
		//System.out.println("xmlPath=="+xmlPath);
		document = XmlUtils.getDocument(xmlPath); 
		outputPath = XmlUtils.getText(document, "defaultOutputPath").get(0);
	}
	
	/*
	<onefile> 
		<filesAttribute output="/app/log/system">
			<file name="file1" url="c:/111.xml" />
			<file name="file2" url="d:/student.xml" />
			<file name="file3" url="e:/readme.txt" />
		</filesAttribute>
	</onefile>
	*/
	/**
	 * 获取<onefile/> (普通文件) 配置参数
	 * map={
	 * 	file2=d:/student.xml, 
	 * 	file3=e:/readme.txt, 
	 * 	file1=c:/111.xml, 
	 * 	output=/app/log/system
	 * }
	 */
	public static Map<String,String> getFileConfigMap(){
		Map<String,String> map = new HashMap<String, String>();	// map={output="",name=url,name=url}
		
		Element element = (Element) document.getElementsByTagName("onefile").item(0);
		
		Element item = (Element) element.getElementsByTagName("filesAttribute").item(0);
		String output = item.getAttribute("output");
		
		map = XmlUtils.readAttribute2Map(item, "file", "name", "url");
		
		if(output == null||"".equals(output)){
			map.put("output", outputPath);
		}else{
			map.put("output", output);
		}
		/*
		NodeList nodeList = item.getElementsByTagName("file");
		//获取<file>节点的属性   存入map
		for(int i=0;i<nodeList.getLength();i++){
			Element ele = (Element) nodeList.item(i);
			String fileName = ele.getAttribute("name");
			String url = ele.getAttribute("url");
			map.put(fileName, url);
		}*/
		return map;
	}
	
	public static Map<String,String> getFoldersConfigMap(){
		Map<String,String> map = new HashMap<String, String>();	// map={output="",name=url,name=url}
		
		Element element = (Element) document.getElementsByTagName("folders").item(0);
		
		Element item = (Element) element.getElementsByTagName("folder").item(0);
		String output = item.getAttribute("output");
		map = XmlUtils.readAttribute2Map(item, "group", "fielpath1", "filepath2");
		
		if(output == null||"".equals(output)){
			map.put("output", outputPath);
		}else{
			map.put("output", output);
		}
		return map;
	}
	
	/*
	<xmlfiles>
		<filesCompare output="/app/log/system">
			<fileGroup>
				<filePath1>c:/exam1.xml</filePath1>
				<filePath2>c:/exam2.xml</filePath2>
				<element name="student">
					<attribute name="examid"></attribute>
					<attribute name="idcard"></attribute>
				</element>
				<element name="id">
					<attribute name="examid"></attribute>
					<attribute name="idcard"></attribute>
				</element>
			</fileGroup>
			<fileGroup>
				<filePath1>c:/exam1.xml</filePath1>
				<filePath2>c:/exam2.xml</filePath2>
				<element name="student">
					<attribute name="examid"></attribute>
					<attribute name="idcard"></attribute>
				</element>
			</fileGroup>
		</filesCompare>
	</xmlfiles>
	*/
	/**获取  比较的xml文件 的配置参数
	 * list = [
	 *	 {para={student=[examid, idcard]}, url={filePath1=c:/exam1.xml, filePath2=c:/exam2.xml, output=/app/log/system}}, 
	 *	 {para={student=[examid, idcard]}, url={filePath1=c:/exam1.xml, filePath2=c:/exam2.xml, output=/app/log/system}}
	 *	]
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public static List<Map<String, Map>> getXmlConfMap(){
		
		Element element = (Element) document.getElementsByTagName("filesCompare").item(0);
		String output = element.getAttribute("output");
		
		//将配置文件中的数据存入,...
		NodeList nodeList = element.getElementsByTagName("fileGroup");
		List<Map<String,Map>> list = new ArrayList<Map<String,Map>>(); 
		for(int i=0;i<nodeList.getLength();i++){
			Element item= (Element) nodeList.item(i);
			
			Element node1 = (Element) item.getElementsByTagName("filePath1").item(0);
			Element node2 = (Element) item.getElementsByTagName("filePath2").item(0);
			String path1 = node1.getTextContent();
			String path2 = node2.getTextContent();
			
			//将地址存入到map
			Map<String,String> urlMap = new HashMap<String, String>();
			if(output == null||"".equals(output)){
				urlMap.put("output", outputPath);
			}else{
				urlMap.put("output", output);
			}
			urlMap.put("filePath1",path1);
			urlMap.put("filePath2", path2);
			
			//用于存节点，属性的map
			Map<String, List<String>> parametersMap = XmlUtils.getConfigMap(item, "element","name", "attribute", "name");
			
			Map<String,Map> configMap = new HashMap<String, Map>();
			// map= { url:{output= ,filePath1= ,filePath2= }  param:{student=[examid,idcard] , ...}  }
			configMap.put("url", urlMap);
			configMap.put("para", parametersMap);
			
			list.add(configMap);
		}
		return list;
	}
	
	//解析properties文件
	//解析js文件 
	//解析html
}
