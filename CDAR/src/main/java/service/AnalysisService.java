package service;

import entityVO.DocumentVO;

import java.util.List;

/**
 * Created by mac on 2017/7/16.
 */
public interface AnalysisService {

    /**
     * 根据文本内容，提取关键字
     * @param text 需要分析的文本内容
     * @return 关键字/关键字/关键字/...
     */
    String analyseKeyWords(String text);

    /**
     * 根据关键字，返回推荐的案例
     * @param caseNumber 案号
     * @param keywords 关键字list
     * @return 案例VO的list
     */
    List<DocumentVO> recommendByKeywords(String caseNumber,List<String> keywords);
}
