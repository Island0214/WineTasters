package entityPO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by qianzhihao on 2017/7/15.
 */
@Entity
public class DocumentPO {
    private Integer id;
    //全文
    private String originDocument;
    //全文、文首、经办法院
    private String court;
    //全文、文首、案号
    private String caseNumber;
    //全文、文首、案件性质
    private String property;
    //全文、诉讼记录、案由
    private String reason;
    //全文、文首、审判程序
    private String process;
    //全文、文尾、裁判时间
    private Date endDate;
    //全文、诉讼参与人全集、（被告）
    private String defendant;
    //全文、诉讼参与人全集、（原告）
    private String plaintiff;
    //全文、案件基本情况
    private String situation;
    //全文、裁判分析过程、法律法条名称
    //形如"《中华人民共和国侵权责任法》:第六条、第十六条/《最高人民法院关于确定民事侵权精神损害赔偿责任若干问题的解释》:第八条"
    private String evidence;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(length = 10000)
    public String getOriginDocument() {
        return originDocument;
    }

    public void setOriginDocument(String originDocument) {
        this.originDocument = originDocument;
    }

    public String getCourt() {
        return court;
    }

    public void setCourt(String court) {
        this.court = court;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDefendant() {
        return defendant;
    }

    public void setDefendant(String defendant) {
        this.defendant = defendant;
    }

    public String getPlaintiff() {
        return plaintiff;
    }

    public void setPlaintiff(String plaintiff) {
        this.plaintiff = plaintiff;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    @Column(length = 1000)
    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }
}