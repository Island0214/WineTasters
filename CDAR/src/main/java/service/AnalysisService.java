package service;

import entityVO.DocumentVO;

import java.util.List;

/**
 * Created by mac on 2017/7/16.
 */
public interface AnalysisService {

    /**
     * 根据关键字，返回推荐的案例
     * @param caseNumber 案号
     * @param keywords 关键字list
     * @return 案例VO的list
     */
    List<DocumentVO> recommendByKeywords(String caseNumber,List<String> keywords);
}
