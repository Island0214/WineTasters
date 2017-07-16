package serviceImpl;

import dao.DocumentDao;
import entityVO.DocumentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.AnalysisService;

import java.util.List;

/**
 * Created by mac on 2017/7/16.
 */
@Service("analysisService")
public class AnalysisServiceImpl implements AnalysisService{

    @Autowired
    private DocumentDao documentDao;

    @Override
    public String analyseKeyWords(String text) {
        return null;
    }

    @Override
    public List<DocumentVO> recommendByKeywords(List<String> keywords) {
        return null;
    }
}
