package com.gd.xml;

import java.util.List;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.gd.xml.XmlUtils;

public class XmlUtilsTest {
	
	@Test
	public void testGetText(){
		try {
			String path = this.getClass().getResource("file.xml").toString();
			Document document = XmlUtils.getDocument(path);
			List<Element> list = XmlUtils.getText(document, "xml","student","class","student");
			System.out.println(list.size());
			//XmlUtils.getText(document, null, "xml","student");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*@Test
	public void testStringSplit(){
		String str = "xml";
		String[] split = str.split("\\.");
		System.out.println(split.length);
		//System.out.println(split[1]);
		//System.out.println(split[2]);
	}*/
}
