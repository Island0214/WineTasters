package util;

import dao.DocumentDao;
import daoImpl.DocumentDaoImpl;
import entityPO.DocumentPO;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by qianzhihao on 2017/7/16.
 */
public class XMLAnalyse {
    private static SAXReader reader = new SAXReader();
    static int x = 0;

    public XMLAnalyse() {
    }

    /**
     * 读取xml文件
     *
     * @param file 文件
     */
    public static DocumentPO readXMLFile(File file) throws DocumentException {
        DocumentPO documentPO = null;
        Document document = reader.read(file);
        Element root = document.getRootElement();
        documentPO = new DocumentPO();
        listNodes(root, documentPO);
//        System.out.println(x++);
        return documentPO;
    }

    //遍历当前节点下的所有节点
    private static void listNodes(Element node, DocumentPO documentPO){
        //全文
        if (node.getName().equals("QW")) {
            documentPO.setOriginDocument(node.attributeValue("value"));
        }
        //经办法院
        else if (node.getName().equals("JBFY")) {
            documentPO.setCourt(node.attributeValue("value"));
        }
        //案号
        else if (node.getName().equals("AH")) {
            documentPO.setCaseNumber(node.attributeValue("value").replace("(","（").replace(")","）"));
        }
        //案件性质
        else if (node.getName().equals("AJXZ")) {
            documentPO.setProperty(node.attributeValue("value"));
        }
        //案由
        else if (node.getName().equals("AY")) {
            documentPO.setReason(node.attributeValue("value"));
        }
        //审判程序
        else if (node.getName().equals("SPCX")) {
            documentPO.setProcess(node.attributeValue("value"));
        }
        //裁决时间
        else if (node.getName().equals("CPSJ")) {
            //可能会有"2Ｏ1Ｏ年12月16日"、"本件与原件核对无异2008年4月10日"、"2ОО9年12月25日"(O->0)
            try {
                Date date = DateTransformer.stringToDate(node.attributeValue("value").replace("Ｏ", "0").replace("О", "0").replace("廿", "2").replace("卅", "3").replace("元", "1").replace("…", "1").replace("农", "").replaceAll("^.*(\\d{4}.*)", "$1"));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                documentPO.setEndDate(sdf.format(date));
            }catch (ParseException e){
                //"第40条公民在遗书中涉及死后个人财产处分的内容，确为死者真实意思的表示，有本人签名并注明了年、月1日"
                //如上则date为null
//                e.printStackTrace();
            }
        }
        //当事人
        else if (node.getName().equals("SSCYR")) {
            List<Element> childs = node.elements();
            String name = null;
            String res = documentPO.getLitigant();
            for (Element child : childs) {
                if (child.getName().equals("SSCYRMC")) {
                    name = child.attributeValue("value");
                }
                if (child.getName().equals("SSSF") && (child.attributeValue("value").equals("原告") || child.attributeValue("value").equals("被告"))) {
                    if (res == null) {
                        res = name;
                    } else {
                        res += "、" + name;
                    }
                    documentPO.setLitigant(res);
                    break;
                }
            }
            return;
        }
        else if(node.getName().equals("SSJL")&&documentPO.getLitigant()==null){
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
        else if (node.getName().equals("AJJBQK")) {
            documentPO.setSituation(node.attributeValue("value"));
        }
        //依据
        else if (node.getName().equals("FLFTMC")) {
            if (documentPO.getEvidence() == null) {
                documentPO.setEvidence(node.attributeValue("value") + "：");
            } else {
                String res = documentPO.getEvidence() + "/";
                res += (node.attributeValue("value") + "：");
                documentPO.setEvidence(res);
            }
        } else if (node.getName().equals("TM")) {
            String res = documentPO.getEvidence();
            //若不是第一个，加上顿号
            if (!documentPO.getEvidence().endsWith("：")) {
                res += "、";
            }
            res += node.attributeValue("value") + "条";
            documentPO.setEvidence(res);
        } else if (node.getName().equals("KM")) {
            String res = documentPO.getEvidence();
            res += node.attributeValue("value");
            documentPO.setEvidence(res);
        } else if (node.getName().equals("CPFXGC")){
            documentPO.setJudgeReason(node.attributeValue("value"));
        } else if (node.getName().equals("CPJG")){
            String res = documentPO.getJudgeReason();
            documentPO.setJudgeReason(res+node.attributeValue("value"));
        }

        //同时迭代当前节点下面的所有子节点
        //使用递归
        Iterator<Element> iterator = node.elementIterator();
        while (iterator.hasNext()) {
            Element e = iterator.next();
            listNodes(e, documentPO);
        }
    }

    public static void main(String[] args) throws ParseException, DocumentException {
        ApplicationContext context = ApplicationContextHelper.getApplicationContext();
        DocumentDao documentDao = context.getBean(DocumentDao.class);
        String path = "src/main/java/test";
        File root = new File(path);
        File[] folders = root.listFiles();
        ExtractKeyword extractKeyword = new ExtractKeyword();
        for (File folder : folders) {
//            System.out.println("Folder:"+folder.getName());
            //去除隐藏文件
            if (folder.getName().startsWith(".")) {
                continue;
            }
            File[] files = folder.listFiles();
            for (File file : files) {
                DocumentPO po = XMLAnalyse.readXMLFile(file);
                po.setKeywords(extractKeyword.extractKeyword(po.getOriginDocument()));
                if (documentDao.getDocumentByCaseNumber(po.getCaseNumber())==null){
                    documentDao.saveDocument(po);
                }
//                XMLAnalyse.readXMLFile(file.getPath());
            }
        }
    }
}
