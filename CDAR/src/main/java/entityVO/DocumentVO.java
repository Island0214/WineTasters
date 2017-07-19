package entityVO;

import entityPO.DocumentPO;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mac on 2017/7/16.
 */
public class DocumentVO {
    private Integer id;
    //全文
    private String originDocument;
    //标题
    private String title;
    //经办法院
    private String court;
    //案号
    private String caseNumber;
    //案件性质
    private String property;
    //案由（nullable）
    private String reason;
    //审判程序
    private String process;
    //裁判时间
    private String endDate;
    //全文、诉讼参与人全集、（被告、被告）
    //当事人
    private String litigant;
    //全文、案件基本情况（nullable）
    private String situation;
    //全文、裁判分析过程、法律法条名称
    //形如"《中华人民共和国侵权责任法》:第六条、第十六条/《最高人民法院关于确定民事侵权精神损害赔偿责任若干问题的解释》:第八条"
    private String evidence;
    //裁决理由（裁判分析过程+裁判结果）
    private String judgeReason;
    //关键词，以/分割
    private List<String> keywords;

    public DocumentVO() {
    }

    public DocumentVO(DocumentPO documentPO) {
        this.id = documentPO.getId();
        this.originDocument = documentPO.getOriginDocument();
        this.title = documentPO.getCaseNumber();
        this.court = documentPO.getCourt();
        this.caseNumber = documentPO.getCaseNumber();
        this.property = documentPO.getProperty();
        this.reason = documentPO.getReason();
        this.process = documentPO.getProcess();
        this.endDate = documentPO.getEndDate();
        this.litigant = documentPO.getLitigant();
        this.situation = documentPO.getSituation();
        this.evidence = documentPO.getEvidence();
        this.judgeReason = documentPO.getJudgeReason();

        this.keywords = Arrays.asList(documentPO.getKeywords().split("/"));

    }
}

