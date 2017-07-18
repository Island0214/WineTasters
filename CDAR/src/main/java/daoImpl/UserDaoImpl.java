package daoImpl;

import dao.UserDao;
import entityPO.UserPO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;


/**
 * Created by mac on 2017/7/17.
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.openSession();
    }

    @Override
    public boolean signUp(UserPO userPO){
        Session session = getSession();

        //判断该用户是否存在（已注册）
        Query q = session.createQuery("select count(*) from UserPO where account = :account");
        q.setParameter("account",userPO.getAccount());

        if( (long)q.getSingleResult() > 0)
            return false;

        session.beginTransaction();
        session.save(userPO);
        session.getTransaction().commit();
        session.close();

        return true;
    }

    @Override
    public boolean signIn(UserPO userPO) {
        Session session = getSession();

        Query q = session.createQuery("select password from UserPO where account = :account");
        q.setParameter("account",userPO.getAccount());
        if(q.getResultList().size() == 0)
            return false;

        String password = (String) q.getSingleResult();

        session.close();

        return password.equals(userPO.getPassword());
    }
}
