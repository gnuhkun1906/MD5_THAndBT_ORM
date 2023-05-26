package ra.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;
import ra.model.Pictures;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
@Service
public class PictureServiceIpm implements IPictureService{
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;
    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.conf.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Pictures> findAll() {
        String query="select p from Pictures as p";
        TypedQuery<Pictures> list= entityManager.createQuery(query, Pictures.class);
        return list.getResultList();
    }

    @Override
    public Pictures findById(int id) {
      String query="select p from Pictures as p where p.id= :id";
      TypedQuery<Pictures> queryStr= entityManager.createQuery(query, Pictures.class);
      queryStr.setParameter("id", id);
      return queryStr.getSingleResult();
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void save(Pictures p) {
        Session session= null;
        Transaction transaction= null;
        try {
            session=sessionFactory.openSession();
            transaction= session.beginTransaction();
            session.saveOrUpdate(p);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            if (transaction!= null){
                transaction.isActive();
            }
        }finally {
            if (session!= null){
                session.close();
            }
        }
    }

    @Override
    public Pictures update(Pictures p) {
        return null;
    }
}
