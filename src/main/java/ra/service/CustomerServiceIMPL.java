package ra.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;
import ra.model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.List;
@Service
public class CustomerServiceIMPL implements ICustomerService{
    private static SessionFactory sessionFactory;
    private  static EntityManager entityManager;
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
    public List<Customer> findAll() {
        String queryStr="select c from Customer as c ";
        TypedQuery<Customer> query=entityManager.createQuery(queryStr, Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer findById(Long id) {
        String queryStr="select c from Customer as c where c.id=:id";
        TypedQuery<Customer> query=entityManager.createQuery(queryStr, Customer.class);
        query.setParameter("id",id);
        return query.getSingleResult();
    }

    @Override
    public void deleteById(Long id) {
        Session session=null;
        Transaction transaction= null;
        try {
            session= sessionFactory.openSession();
            transaction=session.beginTransaction();
            session.delete(findById(id));
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            if (transaction!= null){
                transaction.rollback();
            }
        }finally {
            if (session!= null){
                session.close();
            }
        }
    }
    @Override
    public void save(Customer customer) {
        Session session= null;
        Transaction transaction=null;
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
    public Customer update(Customer customer) {
        Session session=null;
        Transaction transaction=null;
        try {
            session=sessionFactory.openSession();
            transaction= session.beginTransaction();
            Customer origin = findById(customer.getId());
            origin.setName(customer.getName());
            origin.setAddress(customer.getAddress());
            origin.setEmail(customer.getEmail());
            session.saveOrUpdate(origin);
            transaction.commit();
            return origin;
        }catch (Exception e){
            e.printStackTrace();
            if (transaction!= null){
                transaction.rollback();
            }
        }finally {
            if (session!= null){
                session.close();
            }
        }
        return null;
    }
}
