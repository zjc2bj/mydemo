package cn.zjc.util.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ORMConfigLoader {
    /**
     * 根据实体类的class，加载hibernate配置文件，并返回 columnName-->fieldName 对应的map
     * 
     * @param clazz
     *            example: ShengYiNotifyRecord.calss
     * @return Map<String, String> --> example： { <br>
     *         id=id, <br>
     *         retry_times=retryTimes, <br>
     *         req_url=reqUrl, <br>
     *         ...<br>
     *         }
     */
    public static Map<String, String> loadORM(Class<?> clazz) {
        try {
            Document doc = loadDocument(clazz);
            Element rootElement = doc.getRootElement();
            Map<String, String> propertyMap = getConfigMap(rootElement, "property", "name", "column", "name");
            Map<String, String> idMap = getConfigMap(rootElement, "id", "name", "column", "name");

            Map<String, String> columnMap = new HashMap<String, String>();
            columnMap.putAll(idMap);
            columnMap.putAll(propertyMap);
            return columnMap;
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("deprecation")
    private static Document loadDocument(Class<?> clazz) throws JDOMException, IOException {
        InputStream is = clazz.getResourceAsStream("./" + clazz.getSimpleName() + ".hbm.xml");
        SAXBuilder builder = new SAXBuilder(false);
        builder.setIgnoringElementContentWhitespace(true);
        builder.setEntityResolver(new EntityResolver() {
            @Override
            public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
                return new InputSource(new StringBufferInputStream(""));
            }
        });
        return builder.build(is);
    }

    @SuppressWarnings("unchecked")
    private static Map<String, String> getConfigMap(Element ele, String tagName, String tagAttrName,
            String attrTagName, String attrAttrName) {
        Map<String, String> map = new HashMap<String, String>();

        List<Element> children = ele.getChild("class").getChildren(tagName);
        for (Element element : children) {
            // 获取property节点的name属性值
            String fieldName = element.getAttributeValue(tagAttrName);
            // <property name="superId" column="superior_id" insert="false"
            // update="false"/>
            String columnName = element.getAttributeValue(attrTagName);

            if (columnName == null || columnName.trim().equals("")) {
                // 获取column节点的name属性值
                Element child = element.getChild(attrTagName);
                columnName = child.getAttributeValue(attrAttrName);
            }

            map.put(columnName, fieldName);
        }
        return map;
    }
    // public static void main(String[] args) throws Exception {
    // Class<ShengYiNotifyRecord> clazz = ShengYiNotifyRecord.class;
    // Map<String, String> columnMap = loadORM(clazz);
    // System.out.println(columnMap);
    // }
}
