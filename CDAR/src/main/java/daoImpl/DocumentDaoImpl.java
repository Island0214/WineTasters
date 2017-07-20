package daoImpl;

import dao.DocumentDao;
import entityPO.DocumentPO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
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
        try {
            //过滤掉含有提示关键字的案件
            if(documentPO.getOriginDocument().contains("提示：")){
                return false;
            }
            Session session = getSession();
            session.beginTransaction();
            session.save(documentPO);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
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
    public int getPageNumber(String hql, int max) {
        Session session = getSession();
        long totalNumber = (long)session.createQuery(hql).uniqueResult();
        session.close();
        int result = (int) (totalNumber / max);
        if(totalNumber % max > 0)
            return result + 1;
        else
            return result;
    }

    @Override
    public List<DocumentPO> getDocuments(String hql, int page, int max) {
        Session session = getSession();
        Query q = session.createQuery(hql);
        q.setFirstResult((page-1) * max);
        q.setMaxResults(max);
        List<DocumentPO> result = q.getResultList();
        return result;
    }


    @Override
    public List<DocumentPO> getRecommendDocuments(List<String> keywords) {
        Session session = getSession();
        StringBuilder hql = new StringBuilder("from DocumentPO where keywords like ?");
        String addSubQuery = " or keywords like ?";

        for(int i = 0; i < keywords.size()-1; i++){
            hql.append(addSubQuery);
        }

        Query q = session.createQuery(hql.toString());

        for(int i = 0; i < keywords.size(); i++){
            q.setParameter(i,"%"+keywords.get(i)+"%");
        }

        q.setMaxResults(10);

        List<DocumentPO> result = q.getResultList();
        session.close();
        return result;
    }

    @Override
    public List<String> getAllReason() {
        Session session = getSession();
        Query q = session.createQuery("select distinct reason from DocumentPO");
        List<String> result = q.getResultList();
        session.close();
        return result;
    }
}
