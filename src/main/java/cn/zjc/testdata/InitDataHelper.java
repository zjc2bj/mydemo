package cn.zjc.testdata;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class InitDataHelper {
	private static List<InitDataDef> list = null;
	private static String prefix = "";
	static{
		list = init("init/test/init.xml");
	}
	
	public static List<InitDataDef> init(String s){
		List<InitDataDef> list = null;
		try {
			SAXReader A = new SAXReader();
			if (s == null)
				s = "init/test/init.xml";
			InputStream inputstream = InitDataHelper.class.getClassLoader().getResourceAsStream(s);
			if (inputstream == null)
				throw new MagicException("初始化文件" + s + "不存在");
			prefix  = s.substring(0, s.lastIndexOf("/") + 1);
			Document document = A.read(inputstream);
			Element element = document.getRootElement();
			list = readLine2Entity(element);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void initData(String s) throws Exception {
		for (InitDataDef initDataDef : list) {
			Object object = GroovyLoader.parseDataFile(prefix + initDataDef.getFile());
		}
	}
	private static List<InitDataDef> readLine2Entity(Element element) {
		List list = element.selectNodes("initData");
		ArrayList<InitDataDef> arraylist = new ArrayList<InitDataDef>(list.size());
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Element element1 = (Element) iterator.next();
			InitDataDef initdatadef = new InitDataDef();
			initdatadef.setText(element1.attributeValue("text"));
			initdatadef.setFile(element1.attributeValue("file"));
			if (initdatadef.getText() == null || initdatadef.getFile() == null)
				throw new MagicException("initData的text和file都不能为空");
			arraylist.add(initdatadef);
		}

		return arraylist;
	}
}
