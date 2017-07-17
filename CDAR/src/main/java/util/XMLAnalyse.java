package util;

import entityPO.DocumentPO;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by qianzhihao on 2017/7/16.
 */
public class XMLAnalyse {
    private static SAXReader reader = new SAXReader();

    public XMLAnalyse() {
    }

    /**
     * 读取xml文件
     * @param filepath 文件路径
     */
    public static DocumentPO readXMLFile(String filepath){
        DocumentPO documentPO = null;
        try {
            Document document = reader.read(new File(filepath));
            Element root = document.getRootElement();
            documentPO = new DocumentPO();
            listNodes(root,documentPO);
            System.out.println(documentPO);

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return documentPO;
    }

    //遍历当前节点下的所有节点
    private static void listNodes(Element node, DocumentPO documentPO){
        //全文
        if(node.getName().equals("QW")){
            documentPO.setOriginDocument(node.attributeValue("value"));
        }
        //经办法院
        else if(node.getName().equals("JBFY")){
            documentPO.setCourt(node.attributeValue("value"));
        }
        //案号
        else if(node.getName().equals("AH")){
            documentPO.setCaseNumber(node.attributeValue("value"));
        }
        //案件性质
        else if(node.getName().equals("AJXZ")){
            documentPO.setProperty(node.attributeValue("value"));
        }
        //案由
        else if(node.getName().equals("AY")){
            documentPO.setReason(node.attributeValue("value"));
        }
        //审判程序
        else if(node.getName().equals("SPCX")){
            documentPO.setProcess(node.attributeValue("value"));
        }
        //裁决时间
        else if(node.getName().equals("CPSJ")){
            //可能会有"2Ｏ1Ｏ年12月16日"、"本件与原件核对无异2008年4月10日"、"2ОО9年12月25日"(O->0)
            documentPO.setEndDate(DateTransformer.stringToDate(node.attributeValue("value").replace("Ｏ", "0").replace("O", "0").replaceAll("^.*(\\d{4}.*)", "$1")));
        }
        //当事人
        else if(node.getName().equals("SSJL")){
            String res = "";
            //诉讼记录
            String ssjl = node.attributeValue("value");
            Pattern p = Pattern.compile("(?<=被告)(.*?)(?=，|、)");
            Matcher m = p.matcher(ssjl);
            if(m.find()){
                res+=m.group();
            }
            p = Pattern.compile("(?<=原告)(.*?)(?=，|、)");
            m = p.matcher(ssjl);
            if(m.find()){
                res+="、";
                res+=m.group();
            }

            documentPO.setLitigant(res);
        }
        //案件基本情况
        else if(node.getName().equals("AJJBQK")){
            documentPO.setSituation(node.attributeValue("value"));
        }
        //依据
        else if(node.getName().equals("FLFTMC")){
            if(documentPO.getEvidence()==null){
                documentPO.setEvidence(node.attributeValue("value")+"：");
            }
            else {
                String res = documentPO.getEvidence()+"/";
                res+=(node.attributeValue("value")+"：");
                documentPO.setEvidence(res);
            }
        }
        else if(node.getName().equals("TM")){
            String res = documentPO.getEvidence();
            //若不是第一个，加上顿号
            if(!documentPO.getEvidence().endsWith("：")){
                res+="、";
            }
            res+=node.attributeValue("value")+"条";
            documentPO.setEvidence(res);
        }
        else if(node.getName().equals("KM")){
            String res = documentPO.getEvidence();
            res+=node.attributeValue("value");
            documentPO.setEvidence(res);
        }



        //同时迭代当前节点下面的所有子节点
        //使用递归
        Iterator<Element> iterator = node.elementIterator();
        while(iterator.hasNext()){
            Element e = iterator.next();
            listNodes(e,documentPO);
        }
    }

    public static void main(String[] args) {
        String path = "src/main/java/test";
        File root = new File(path);
        File[] folders = root.listFiles();
        for(File folder:folders){
            //去除隐藏文件
            if(folder.getName().startsWith(".")){
                continue;
            }
            File[] files = folder.listFiles();
            for(File file:files){
                XMLAnalyse.readXMLFile(file.getPath());
            }
        }
    }
}
