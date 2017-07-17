package util;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.util.List;

/**
 * XML文件读取器
 * Created by Croff on 2017/7/17.
 */
public class XMLReader {

    public XMLReader() {

    }

    /**
     * 读取XML文件
     * @param path 文件路径
     * @throws DocumentException 文档异常
     * @throws FileNotFoundException 文件未找到异常
     * @throws UnsupportedEncodingException 编码不支持异常
     */
    public void readXML(String path) throws DocumentException, FileNotFoundException, UnsupportedEncodingException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new InputStreamReader(new FileInputStream(new File(path)), "UTF-8"));
        document.setXMLEncoding("UTF-8");
        Element element = document.getRootElement();
        System.out.println(element.getName());
        listNodes(element);
    }

    /**
     * 遍历当前节点元素下面的所有(元素的)子节点
     *
     * @param node 节点
     */
    private void listNodes(Element node) {
        // 获取当前节点的所有属性节点
        List<Attribute> attributeList = node.attributes();

        // 遍历属性节点
        if (attributeList.size() == 2) {
            System.out.println("当前节点的名称：" + node.getName());

            Attribute attrName = attributeList.get(1), attrValue = attributeList.get(0);
            if (!attrName.getName().equals("nameCN")) {
                Attribute temp = attrName;
                attrName = attrValue;
                attrValue = temp;
            }

            System.out.println(attrName.getText() + ": " + attrValue.getText());
        }

        // 当前节点下面子节点迭代器
        List<Element> elementList = node.elements();
        // 遍历
        for (Element element : elementList) {
            // 对子节点进行遍历
            listNodes(element);
        }
    }
}

