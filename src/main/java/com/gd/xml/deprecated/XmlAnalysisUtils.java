package com.gd.xml.deprecated;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * 解析器(需指定xml路径)
 * 
 * @author
 * 
 */
@Deprecated
public class XmlAnalysisUtils {

    private Document document;

    /**
     * 根据指定的路径,加载xml文件
     * 
     * @param path
     *            xml文件路径
     * @return 将xml转化为Document
     */
    public XmlAnalysisUtils(String path) {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // 忽略注释
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);

            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            // 加载xml文件
            document = documentBuilder.parse(path);
        } catch (Exception e) {
            throw new RuntimeException("文件加载失败");
        }
    }

    /**
     * 获取配置文件中的 指定节点的指定属性在xml中对应的值的集合 (要求：不同节点位置的名称不一致。如 不同层级 不能有两个节点都为server)
     * 
     * @param tagName
     *            指定节点名称
     * @param attrName
     *            指定属性名称
     * @return 对应的值的集合
     */
    public List<String> getText1(String tagName, String attrName) {
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
     * 获取配置文件中的 指定节点的指定属性在xml中对应的值的集合 (要求：不同节点位置的名称不一致。如 不同层级 不能有两个节点都为server)
     * 
     * @param tagName
     *            指定节点名称
     * @param attrName
     *            指定属性名称
     * @return 对应的值的集合
     */
    public List<String> getText(String attrName, String tagName) {
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
     * 根据唯一的节点名称 获取对应的文本值
     * 
     * @param tagName
     *            节点名称
     * @return 节点对应的值
     */
    public String getContentByTagName(String tagName) {
        NodeList nodeList = document.getElementsByTagName(tagName);
        // System.out.println(nodeList.getLength());
        String content = nodeList.item(0).getTextContent();
        return content;
    }

    public Document getDocument() {
        return document;
    }
}
