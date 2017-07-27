package entityVO;

/**
 * Created by mac on 2017/7/16.
 */
public class DocumentVO {
    /**
     * 列表页需要的：
     * title:xx判决书
     * caseNumber：案号
     * court：审理法院
     * endDate：裁判日期
     * judgeReason:裁判理由，开头是“本院认为。。。”那一段
     *
     * 具体案例页需要的提取结果概要：
     * property：案件类型
     * evidence：法律依据
     * process:审理程序
     * reason:案由..信用卡纠纷
     * endDate：裁判日期
     * litigant:诉讼参与人，原告被告
     * originDocument:裁判书原文
     * publicProsecution:公诉方
     */

    public Integer id;
    //xx判决书 暂时和casenumber一样
    public String title;
    //案号
    public String caseNumber;
    //审理法院
    public String court;
    //裁判日期
    public String endDate;
    //裁判理由
    public String judgeReason;

    //案件类型
    public String property;
    //法律依据
    public String evidence;
    //审理程序
    public String process;
    //案由
    public String reason;
    //诉讼参与人，原告被告
    public String litigant;
    //裁判书原文
    public String originDocument;
    //裁判书原文
    public String keywords;
    //公诉方
    public String publicProsecution;

    /**
     * @param id 案件id
     * @param title xx判决书
     * @param caseNumber 案号
     * @param court 审理法院
     * @param endDate 裁判日期
     * @param judgeReason 裁判理由
     * @param property 案件类型
     * @param evidence 法律依据
     * @param process 审理程序
     * @param reason 案由
     * @param litigant 诉讼参与人，原告被告
     * @param originDocument 裁判书原文
     */
    public DocumentVO(Integer id, String title, String caseNumber, String court, String endDate,
                      String judgeReason, String property, String evidence, String process,
                      String reason, String litigant, String originDocument, String keywords, String publicProsecution) {
        this.id = id;
        this.title = title;
        this.caseNumber = caseNumber;
        this.court = court;
        this.endDate = endDate;
        this.judgeReason = judgeReason;
        this.property = property;
        this.evidence = evidence;
        this.process = process;
        this.reason = reason;
        this.litigant = litigant;
        this.originDocument = originDocument.replaceAll("\n","<br>");
        this.keywords = keywords;
        this.publicProsecution = publicProsecution;
    }
}

