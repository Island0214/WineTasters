package serviceImpl;

import dao.DocumentDao;
import entityPO.DocumentPO;
import entityVO.DocumentVO;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ManageService;
import util.ExtractKeyword;
import util.TransferHelper;
import util.XMLAnalyse;

import java.io.File;
import java.text.ParseException;
import java.util.List;

/**
 * Created by mac on 2017/7/16.
 */
@Service("manageService")
public class ManageServiceImpl implements ManageService {

    @Autowired
    private DocumentDao documentDao;

    @Override
    public DocumentVO getDocumentByCaseNumber(String caseNumber) {
        return new DocumentVO(documentDao.getDocumentByCaseNumber(caseNumber));
    }

    @Override
    public String uploadDocument(File file) throws DocumentException {
        DocumentPO documentPO = XMLAnalyse.readXMLFile(file);
        if(documentDao.getDocumentByCaseNumber(documentPO.getCaseNumber())==null){
            documentPO.setKeywords(new ExtractKeyword().extractKeyword(documentPO.getOriginDocument()));
            documentDao.saveDocument(documentPO);
            return documentPO.getCaseNumber();
        }
        return null;
    }

    @Override
    public boolean deleteDocument(Integer documentID) {
        return documentDao.deleteDocument(documentID);
    }

    @Override
    public int getPageNumberByRex(String rex, int max) {
        return documentDao.getPageNumber("select count(*) from DocumentPO where originDocument like '%"+rex+"%'",max);
    }

    @Override
    public List<DocumentVO> getDocumentsByRex(String rex, int page, int max) {
        return TransferHelper.transToDocumentVOList(documentDao.getDocuments("from DocumentPO where originDocument like '%"+rex+"%'",page,max));
    }

    @Override
    public int getPageNumberByCategory(String category, int max) {
        return documentDao.getPageNumber("select count(*) from DocumentPO where property = '"+category+"'",max);
    }

    @Override
    public List<DocumentVO> getDocumentsByCategory(String category, int page, int max) {
        return TransferHelper.transToDocumentVOList(documentDao.getDocuments("from DocumentPO where property = '"+category+"'",page,max));
    }

    @Override
    public int getPageNumberByReason(String reason, int max) {
        return documentDao.getPageNumber("select count(*) from DocumentPO where reason = '"+reason+"'",max);
    }

    @Override
    public List<DocumentVO> getDocumentsByReason(String reason, int page, int max) {
        return TransferHelper.transToDocumentVOList(documentDao.getDocuments("from DocumentPO where reason = '"+reason+"'",page,max));
    }

    @Override
    public List<String> getAllReason() {
        return documentDao.getAllReason();
    }


}
