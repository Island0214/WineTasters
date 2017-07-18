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
        Session session = getSession();
        String hql = "FROM DocumentPO WHERE caseNumber = :caseNumber";
        DocumentPO documentPO = (DocumentPO)session.createQuery(hql).setParameter("caseNumber", caseNumber).uniqueResult();
        session.close();
        return documentPO;
    }

    @Override
    public boolean saveDocument(DocumentPO documentPO) {
        return false;
    }

    @Override
    public boolean deleteDocument(Integer documentID) {
        Session session = getSession();
        String hql = "DELETE FROM DocumentPO WHERE id = :id";
        session.createQuery(hql).setParameter("id", documentID);
        session.close();
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
