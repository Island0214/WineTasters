package dao;

import entityPO.DocumentPO;

import java.util.List;

/**
 * Created by mac on 2017/7/16.
 */
public interface DocumentDao {

    /**
     * 根据 caseNumber 取出数据库中对应案例，若不存在则返回null
     * @param caseNumber 唯一标识案件的number
     * @return 案例的po或null
     */
    DocumentPO getDocumentByCaseNumber(String caseNumber);

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
     * 返回对应页数
     * @param hql 查询语句
     * @param max 每页最大个数
     * @return 页数
     */
    int getPageNumber(String hql, int max);

    /**
     * 按页取出
     * @param hql 查询语句
     * @param page 页数
     * @param max 每页最大个数
     * @return 包含一个或多个案件PO的List
     */
    List<DocumentPO> getDocuments(String hql,int page,int max);

    /**
     * 根据关键字，返回包含该关键字的案例
     * @param keywords 关键字
     * @return 包含该关键字案例PO的List
     */
    List<DocumentPO> getRecommendDocuments(List<String> keywords);
}
