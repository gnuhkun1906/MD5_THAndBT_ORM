package ra.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;
import ra.model.Customer;
import ra.model.Song;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
@Service
public class SongServiceIpm implements ISongService{
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
    public List<Song> findAll() {
        String query = "select s from Song as s";
        TypedQuery<Song> list= entityManager.createQuery(query, Song.class);
        return list.getResultList();
    }

    @Override
    public Song findById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void save(Song customer) {
        Session session= null;
        Transaction transaction= null;
        try {
            session=sessionFactory.openSession();
            transaction=session.beginTransaction();
            session.saveOrUpdate(customer);
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
    public Song update(Song customer) {
        return null;
    }
}
