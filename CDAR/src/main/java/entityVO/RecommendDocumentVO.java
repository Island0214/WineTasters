package entityVO;

import java.util.List;

/**
 * 类案推荐的案件的vo 包含关键字和标题
 * Created by Mark.W on 2017/7/17.
 */
public class RecommendDocumentVO {

    //案件id
    public Integer id;
    //关键字
    public List<String> keywords;
    //案件标题
    public String title;

    /**
     * @param id 案件id
     * @param keywords 关键字
     * @param title 案件标题
     */
    public RecommendDocumentVO(Integer id, List<String> keywords, String title) {
        this.id = id;
        this.keywords = keywords;
        this.title = title;
    }
}
