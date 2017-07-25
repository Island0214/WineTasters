package serviceImpl;

import dao.DocumentDao;
import entityPO.DocumentPO;
import entityVO.DocumentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.AnalysisService;
import util.ExtractKeyword;
import util.TransferHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by mac on 2017/7/16.
 */
@Service("analysisService")
public class AnalysisServiceImpl implements AnalysisService{

    @Autowired
    private DocumentDao documentDao;
    
    @Override
    public String analyseKeyWords(String text) {
        ExtractKeyword extractKeyword = new ExtractKeyword();
        return extractKeyword.extractKeyword(text);
    }

    @Override
    public List<DocumentVO> recommendByKeywords(String caseNumber,List<String> keywords) {
        List<DocumentPO> documentPOS = documentDao.getRecommendDocuments(keywords);
        Predicate<DocumentPO> predicate = (s) -> s.getCaseNumber().equals(caseNumber);
        documentPOS.removeIf(predicate);
        Collections.shuffle(documentPOS);

        List<DocumentPO> result = new ArrayList<>();
        for(int i=0;i<5;i++){
            result.add(documentPOS.get(i));
        }

        return TransferHelper.transToDocumentVOList(result);
    }
}
