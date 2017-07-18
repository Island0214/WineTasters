package service;

import entityVO.DocumentVO;
import org.dom4j.DocumentException;

import java.io.File;
import java.util.List;

/**
 * Created by mac on 2017/7/16.
 */
public interface ManageService {

    /**
     * 上传*.xml文件，分析并存储到数据库
     * @param file *.xml
     * @return 是否成功(若重复，则return false)
     */
    boolean uploadDocument(File file) throws DocumentException;

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
     * @return 包含一个或多个案件VO的List
     */
    List<DocumentVO> getDocuments(String rex, int max);

    /**
     * 随机取出n个案件
     * @param n 案件个数
     * @param category 案件类别
     * @return 包含n个案件VO的List
     */
    List<DocumentVO> getDocuments(int n,String category);

}
