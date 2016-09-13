package com.gd.compare;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import cn.zjc.util.LogWriter;

import com.gd.xml.FileConfigAnalysis;
import com.gd.xml.XmlUtils;

/**
 * 获取配置参数 比较多组文件 ：Service层
 * 
 * @author zjc
 * 
 */
@SuppressWarnings("unchecked")
public class XmlCompare {
    public static String output;

    @Test
    public void compare() {
        try {
            List<String> resultList1 = new ArrayList<String>();// 比较后相同的信息集合
            List<String> resultList2 = new ArrayList<String>();// 比较后不相同的信息集合

            List<Map<String, Map>> xmlConfMap = FileConfigAnalysis.getXmlConfMap();
            /**
             * list = [ {para={student/父节点.student=[examid, idcard],...},
             * url={filePath1=c:/exam1.xml, filePath2=c:/exam2.xml,
             * output=/app/log/system}}, {para={student/父节点.student=[examid,
             * idcard],...}, url={filePath1=c:/exam1.xml,
             * filePath2=c:/exam2.xml, output=/app/log/system}} ]
             */
            // 将配置文件中的每组数据取出 进行逐一对比
            for (Map<String, Map> map : xmlConfMap) {
                // 取路径
                Map<String, String> urlMap = map.get("url");
                String filePath1 = urlMap.get("filePath1");
                String filePath2 = urlMap.get("filePath2");
                output = (String) urlMap.get("output");

                // 获取两个文件的document
                Document doc1 = XmlUtils.getDocument(filePath1);
                Document doc2 = XmlUtils.getDocument(filePath2);

                // 获取比较的xml参数集合 para={ student=[examid, idcard] }
                Map<String, List<String>> paraMap = map.get("para");
                // 遍历,对比
                Set<Entry<String, List<String>>> entrySet = paraMap.entrySet();
                for (Entry<String, List<String>> entry : entrySet) {
                    String key = entry.getKey();// xml.exam.student
                    List<String> valueList = entry.getValue();
                    // 将key截取为数组
                    String[] tagNames = key.split("\\.");

                    // 获取filepath1指定的xml中 符合节点名称的所有列表
                    List<Element> elements1 = XmlUtils.getText(doc1, tagNames);
                    // 获取filepath1指定的xml中 符合节点名称的所有列表
                    List<Element> elements2 = XmlUtils.getText(doc2, tagNames);

                    if (elements1.size() == elements2.size() && elements1.size() > 0) {
                        if (valueList.size() > 0) {
                            for (String value : valueList) {
                                List<String> list1 = new ArrayList<String>();// 存放filePath1--xml中的属性值列表
                                List<String> list2 = new ArrayList<String>();// 存放filePath2--xml中的属性值列表

                                for (Element ele : elements1) {
                                    String attrValue = XmlUtils.getAttribute(ele, value);
                                    list1.add(attrValue);
                                }
                                for (Element ele : elements2) {
                                    String attrValue = XmlUtils.getAttribute(ele, value);
                                    list2.add(attrValue);
                                }
                                if (list1.equals(list2)) {
                                    resultList1.add(key + "节点的" + value + "属性的值一致");
                                } else {
                                    resultList2.add(key + "节点的" + value + "属性的值不一致");
                                }
                            }
                        } else {// 无属性值,只比较节点的文本值
                            List<String> list1 = new ArrayList<String>();// 存放filePath1--xml中的节点值列表
                            List<String> list2 = new ArrayList<String>();// 存放filePath2--xml中的节点值列表
                            for (Element ele : elements1) {
                                String content = ele.getTextContent();
                                list1.add(content);
                            }
                            for (Element ele : elements2) {
                                String content = ele.getTextContent();
                                list2.add(content);
                            }
                            if (list1.equals(list2)) {
                                resultList1.add(key + "节点的" + "的文本值一致");
                            } else {
                                resultList2.add(key + "节点的" + "的文本值不一致");
                            }
                        }
                    } else {
                        resultList2.add(key + "的节点数量为零 或两者不一致，请检查");
                    }
                }

            }

            // 将结果输出到指定文件
            LogWriter writer = new LogWriter(output);
            writer.log(resultList1);
            writer.log("\r\n");
            writer.log(resultList2);
            // System.out.println(resultList1);
            // System.out.println(resultList2);
            // 关闭LogWriter的输出流
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new XmlCompare().compare();
    }
}