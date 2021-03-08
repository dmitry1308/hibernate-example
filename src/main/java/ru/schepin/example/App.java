package ru.schepin.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.schepin.example.entity.User;

import java.util.List;

public class App {
    public static void main(String[] args) {
        Configuration configure = new Configuration().configure();
        configure.addAnnotatedClass((User.class));

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configure.getProperties());

        SessionFactory factory = configure.buildSessionFactory(builder.build());

        //CRUD

        //create
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        User diman = new User("Diman", "891301373");
        session.save(diman);
        transaction.commit();
        session.close();

        //read
        Session session1 = factory.openSession();
        Transaction transaction1 = session1.beginTransaction();
        diman = session1.find(User.class, 23);
        System.out.println(diman.toString());
        transaction1.commit();
        session1.close();


        //update
        Session session2 = factory.openSession();
        Transaction transaction2 = session2.beginTransaction();
        diman = session2.find(User.class, 23);
        diman.setName("man");
        session2.update(diman);
        transaction2.commit();
        session1.close();

        //delete
  /*      Session session3 = factory.openSession();
        Transaction transaction3 = session3.beginTransaction();
        session3.delete(diman);
        transaction3.commit();
        session3.close();*/

        //RAW query
        Session session4 = factory.openSession();
        Transaction transaction4 = session4.beginTransaction();
        Query query = session4.createQuery("from User u where u.name=:id");//hql
        query.setParameter("id", "Diman");
        List<User> resultList = query.getResultList();
        System.out.println(resultList.toString());
    }
}
