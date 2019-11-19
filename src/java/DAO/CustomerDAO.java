/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import bean.Customer;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class CustomerDAO {
    public void CustomerAdd(Customer c){
        Transaction trans = null;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            trans = session.beginTransaction();
            session.save(c);
            trans.commit();
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    public Customer get(String username, String password){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Query query  = session.createQuery("from Customer c where c.username = :user and password = :password");
        query.setParameter("user", username);
        
        query.setParameter("password", password);
        Customer c = (Customer) query.uniqueResult();
        
        return c;
    }
}
