package com.gd.xml.deprecated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


/**
 * 在使用该类时 根据配置的 <配置文件路径> 加载配置文件 并获得其解析器
 * @author 
 *
 */
@Deprecated
public class FileConfigLoader {
	// 获取加载fileconfig.xml文件的解析器
	public static String path = XmlAttributeCompare.class.getResource(FileConfigConstant.CONFIGPATH).toString();
	public static XmlAnalysisUtils utils = new XmlAnalysisUtils(path);
	
	/**
	 * @param utils 加载过fileconfig.xml的XmlAnalysisUtils
	 * @return 返回 (节点名name属性值和子节点文本值list) 的集合
	 */
	public static Map<String, List<String>> getConfigMap() {
		Map<String,List<String>> map = new HashMap<String,List<String>>();
		
		Document document = utils.getDocument();
		NodeList nodeList = document.getElementsByTagName(FileConfigConstant.TAGNAME);
		for(int i=0;i<nodeList.getLength();i++){
			Element item = (Element) nodeList.item(i);
			//获取element节点的name属性值
			String key = item.getAttributeNode(FileConfigConstant.TAGATTRIBUTE).getValue();
			NodeList childNodes = item.getElementsByTagName(FileConfigConstant.ATTRIBUTE);
			List<String> list = new ArrayList<String>();
			for(int j=0;j<childNodes.getLength();j++){
				//获取子节点的值
				Element attr = (Element) childNodes.item(j);
				String attrValue = attr.getAttribute(FileConfigConstant.ATT_ATT);
				list.add(attrValue);
			}
			map.put(key, list);
		}
		return map;
	}
}
