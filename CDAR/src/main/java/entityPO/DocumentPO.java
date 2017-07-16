package entityPO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by qianzhihao on 2017/7/15.
 */
@Entity
public class DocumentPO {
    private Integer id;
    private String originDocument;
    private String analysis;

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

    @Column(length = 10000)
    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }
}
