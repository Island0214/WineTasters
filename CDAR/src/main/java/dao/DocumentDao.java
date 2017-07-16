package dao;

import entityPO.DocumentPO;

import java.util.List;

/**
 * Created by mac on 2017/7/16.
 */
public interface DocumentDao {

    /**
     * 根据 caseNumber 判断数据库中是否已有该案例
     * @param caseNumber 唯一标识案件的number
     * @return 是否存在
     */
    boolean checkDocument(String caseNumber);

    /**
     * 存储案件信息
     * @param documentPO 案件PO
     * @return 是否成功
     */
    boolean saveDocument(DocumentPO documentPO);

    /**
     * 删除案件信息
     * @param documentID 案件ID
     * @return 是否成功
     */
    boolean deleteDocument(Integer documentID);

    /**
     * 正则匹配搜索
     * @param rex 匹配的字符串
     * @param max 最大个数
     * @return 包含一个或多个案件PO的List
     */
    List<DocumentPO> getDocuments(String rex,int max);

    /**
     * 随机取出n个案件
     * @param n 案件个数
     * @param category 案件类别
     * @return 包含n个案件PO的List
     */
    List<DocumentPO> getDocuments(int n,String category);

    /**
     * 根据关键字，返回包含该关键字的案例
     * @param keywords 关键字
     * @return 包含该关键字案例PO的List
     */
    List<DocumentPO> getRecommendDocuments(List<String> keywords);
}
