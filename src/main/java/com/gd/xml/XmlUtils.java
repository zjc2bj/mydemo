package com.gd.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * xml工具类
 * @author zjc
 *
 */
public class XmlUtils {
	private static List<Element> alist = new ArrayList<Element>();
	
	/**
	 * 根据指定的路径 加载xml 获得Document
	 * @param xmlPath
	 * @return
	 */
	public static Document getDocument(String xmlPath) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			// 忽略注释
			factory.setIgnoringComments(true);
			factory.setIgnoringElementContentWhitespace(true);

			DocumentBuilder documentBuilder = factory.newDocumentBuilder();
			// 加载xml文件
			Document document = documentBuilder.parse(xmlPath);
			return document;
		} catch (Exception e) {
			throw new RuntimeException("文件加载失败");
		}
	}

	/**
	 * 根据指定文本的节点名称 获得节点文本值集合
	 * @param doc
	 * @param tagName
	 * @return
	 */
	public static List<String> getText(Document doc, String tagName){
		NodeList nodeList = doc.getElementsByTagName(tagName);
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node item = nodeList.item(i);
			String content = item.getTextContent();
			list.add(content);
		}
		return list;
	}

	/**
	 * 获取 指定具体节点在xml中对应的节点的集合 
	 * (条件：不同节点位置的名称一致。如 不同层级 有两个节点都为server)
	 * @param tagName
	 *            指定节点(加上所有父节点)名称
	 * @return 对应的值的集合
	 */
	public static List<Element> getText(Document document,String... tagName) {
		if (document == null) {
			throw new RuntimeException("你还没有加载xml文件");
		}
		List<Element> list = new ArrayList<Element>();;
		NodeList nodeList = document.getElementsByTagName(tagName[0]);
		for (int i = 0;i<nodeList.getLength();i++) {
			Element item = (Element) nodeList.item(i);
			if(tagName.length>1){
				list = getElementsByTagName(1, item, tagName);
			}else{
				list.add(item);
			}
		}
		return list;
	}
	
	/*
	 * 递归
	 */
	public static List<Element> getElementsByTagName(int temp, Element element, String... tagName) {
		try {
			NodeList childNodes = element.getChildNodes();
			if(childNodes.getLength()>0){
				for(int i=0;i<childNodes.getLength();i++){
					if (childNodes.item(i) instanceof Element) {
						//System.out.println("符合");
						Element item = (Element) childNodes.item(i);
						if (item.getTagName().equals(tagName[temp])) {
							if (temp < tagName.length - 1){
								getElementsByTagName(temp + 1, item, tagName);
							}
							if(temp == tagName.length-1){
								//将student节点存入list
								alist.add(item);
								System.out.println(item);
							}
						}
					}
				}
				return alist;
			}
			return null;
			
			/*if(nodeList.getLength()>0){
				for (int i = 0; i < nodeList.getLength(); i++) {
					Element item = (Element) nodeList.item(i);
					if(temp<tagName.length-1){
						getElementsByTagName(temp+1, item, tagName);
					}
					list.add(item);
				}
				return list;
			}
			return null;*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("查找节点失败");
			//e.printStackTrace();
		}
		//return null;

	}
	/*
	 * NodeList nodeList = document.getElementsByTagName(tagName); List<String>
	 * list = new ArrayList<String>(); for(int i=0;i<nodeList.getLength();i++){
	 * Element element = (Element) nodeList.item(i); if(attrName==null){
	 * list.add(element.getTextContent()); }else{
	 * list.add(element.getAttribute(attrName)); } } return list;
	 */

	/**
	 * 获取 指定节点的指定属性在xml中对应的值的集合 
	 * (条件：不同节点位置的名称不一致。如 不同层级 不能有两个节点都为server)
	 * 
	 * @param tagName 指定节点名称
	 * @param attrName 指定属性名称
	 * @return 所有符合的对应的值的集合
	 */
	public List<String> getText(Document document, String tagName,
			String attrName) {
		if (document == null) {
			throw new RuntimeException("你还没有加载xml文件");
		}
		NodeList nodeList = document.getElementsByTagName(tagName);
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element element = (Element) nodeList.item(i);
			if (attrName == null) {
				list.add(element.getTextContent());
			} else {
				list.add(element.getAttribute(attrName));
			}
		}
		return list;
	}
	
	/**
	 * 根据节点和属性名 获得属性值
	 * @param element
	 * @param attrName
	 * @return
	 */
	public static String getAttribute(Element element,String attrName){
		return element.getAttribute(attrName);
	}
	
	/**
	 * 将节点的属性值及子节点的属性值 封装成map
	 * <element name="student1">
	 *		<attribute name="examid"/>
	 *		<attribute name="idcard"/>
	 *	</element>
	 * <element name="student2">
	 *		<attribute name="name"/>
	 *		<attribute name="idcard"/>
	 *	</element>
	 *{student1=[examid,idcard] , student2=[name,idcard]...}
	 *
	 * @param elemment	父节点
	 * @param tagName	节点名
	 * @param tagAttrName	节点属性名
	 * @param attrTagName	节点下的属性节点名
	 * @param attrAttrName	节点下的属性节点的属性名 
	 * @return
	 */
	public static Map<String, List<String>> getConfigMap(Element elemment,String tagName,String tagAttrName,String attrTagName,String attrAttrName) {
		Map<String,List<String>> map = new HashMap<String,List<String>>();
		
		NodeList nodeList = elemment.getElementsByTagName(tagName);
		for(int i=0;i<nodeList.getLength();i++){
			Element item = (Element) nodeList.item(i);
			//获取element节点的name属性值
			String key = item.getAttributeNode(tagAttrName).getValue();
			NodeList childNodes = item.getElementsByTagName(attrTagName);
			List<String> list = new ArrayList<String>();
			for(int j=0;j<childNodes.getLength();j++){
				//获取子节点的值
				Element attr = (Element) childNodes.item(j);
				String attrValue = attr.getAttribute(attrAttrName);
				list.add(attrValue);
			}
			map.put(key, list);
		}
		return map;
	}
	
	/**
	 * <address>
	 *	<mail no="1" value="jin@1563.com"/> 
	 *	<mail no="2" value="zhan@1563.com"/> 
	 *	<mail no="3" value="jinnan@1563.com"/>  
	 * </address>
	 * 返回：{1=jin@1563.com,2=zhan@1563.com,...}
	 * 
	 * @param ele	父节点
	 * @param tagName	子节点名称
	 * @return	子节点的属性值对应的map
	 */
	public static Map<String,String> readAttribute2Map(Element ele,String tagName,String attr1,String attr2){
		Map<String,String> map = new HashMap<String, String>();
		
		NodeList nodeList = ele.getElementsByTagName(tagName);
		for(int i=0;i<nodeList.getLength();i++){
			Element item = (Element) nodeList.item(i);
			String attrValue1 = item.getAttribute(attr1);
			String attrValue2 = item.getAttribute(attr2);
			map.put(attrValue1, attrValue2);
		}
		return map;
	}
}