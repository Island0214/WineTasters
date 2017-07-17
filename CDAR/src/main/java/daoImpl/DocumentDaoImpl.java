package daoImpl;

import dao.DocumentDao;
import entityPO.DocumentPO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mac on 2017/7/16.
 */
@Repository("documentDao")
public class DocumentDaoImpl implements DocumentDao{

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.openSession();
    }

    @Override
    public DocumentPO getDocumentByCaseNumber(String caseNumber) {
        String hql = "FROM entityPO.DocumentPO WHERE caseNumber = ?";
        return (DocumentPO)getSession().createQuery(hql).setParameter(0, caseNumber).uniqueResult();
    }

    @Override
    public boolean saveDocument(DocumentPO documentPO) {
        return false;
    }

    @Override
    public boolean deleteDocument(Integer documentID) {
        String hql = "FROM entityPO.DocumentPO WHERE id = ?";
        DocumentPO documentPO = (DocumentPO)getSession().createQuery(hql).setParameter(0, documentID).uniqueResult();
        getSession().delete(documentPO);
        return true;
    }

    @Override
    public List<DocumentPO> getDocuments(String rex, int max) {
        return null;
    }

    @Override
    public List<DocumentPO> getDocuments(int n, String category) {
        return null;
    }

    @Override
    public List<DocumentPO> getRecommendDocuments(List<String> keywords) {
        return null;
    }
}
