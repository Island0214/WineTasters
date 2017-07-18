package service;

import entityVO.DocumentVO;

import java.io.File;
import java.util.List;

/**
 * Created by mac on 2017/7/16.
 */
public interface ManageService {

    /**
     * 上传*.xml文件，分析并存储到数据库
     * @param file *.xml
     * @return 是否成功
     */
    boolean uploadDocument(File file);

    /**
     * 删除案件信息
     * @param documentID 案件ID
     * @return 是否成功
     */
    boolean deleteDocument(Integer documentID);

    /**
     * 返回正则匹配对应页数
     * @param rex 正则匹配内容
     * @param max 每页最大个数
     * @return 页数
     */
    int getPageNumberByRex(String rex, int max);

    /**
     * 正则匹配搜索
     * @param rex 匹配的字符串
     * @param page 页数
     * @param max 每页最大个数
     * @return 包含一个或多个案件VO的List
     */
    List<DocumentVO> getDocumentsByRex(String rex, int page,int max);

    /**
     * 返回某一类别案件对应页数
     * @param category 案件类别
     * @param max 每页最大个数
     * @return 页数
     */
    int getPageNumber(String category, int max);

    /**
     * 取出该类别的案件
     * @param category 案件类别
     * @param page 页数
     * @param max 每页最大个数
     * @return 包含n个案件VO的List
     */
    List<DocumentVO> getDocumentsByCategory(String category, int page, int max);

}
