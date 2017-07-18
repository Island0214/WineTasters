package util;

import entityPO.DocumentPO;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * XML读取器的测试用例
 * Created by Croff on 2017/7/17.
 */
public class XMLAnalyseTest {

    @Test
    public void readXMLFile() throws Exception {
        for (int i=1; i<=18; i++) {
            DocumentPO documentPO = XMLAnalyse.readXMLFile(new File("src/test/resources/xml/" + String.valueOf(i) + ".xml"));
            showDocumentPO(documentPO);
        }
    }

    private void showDocumentPO(DocumentPO documentPO) {
        System.out.println("案件ID: " + documentPO.getId());
        System.out.println("全文: " + documentPO.getOriginDocument());
        System.out.println("经办法院: " + documentPO.getCourt());
        System.out.println("案号: " + documentPO.getCaseNumber());
        System.out.println("案件性质: " + documentPO.getProperty());
        System.out.println("案由: " + documentPO.getReason());
        System.out.println("审判程序: " + documentPO.getProcess());
        System.out.println("裁判时间: " + documentPO.getEndDate());
        System.out.println("当事人: " + documentPO.getLitigant());
        System.out.println("基本情况: " + documentPO.getSituation());
        System.out.println("分析过程、法条名称: " + documentPO.getEvidence());
        System.out.println("关键词: " + documentPO.getKeywords());
        System.out.println();
    }
}