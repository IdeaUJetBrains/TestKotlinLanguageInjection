import entity.Entitybus;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;

import javax.persistence.OrderBy;
import javax.persistence.metamodel.EntityType;
import javax.sql.DataSource;

import java.util.Map;
import java.util.Set;

public class HibernateMainJava {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        String str = "select c from Entitybus c";
        session.createQuery(str);
        session.createQuery("select c from Entitybus c", Entitybus.class);
        session.createNamedQuery("My.Test");
        session.createNamedQuery("My.Test", Entitybus.class);
        session.createNativeQuery("SELECT * FROM ENTITYBUS");
        session.createNativeQuery("SELECT EID, ENUMBER FROM ENTITYBUS", Entitybus.class);
        session.createNativeQuery("SELECT EID, ENUMBER FROM ENTITYBUS","AuthorValueMapping");
        session.createNativeQuery("select c from ENTITYBUS c");
        
        Query query1 = session.createQuery("from Entitybus where Entitybus = :arg ");
        query1.setParameter("arg", "1ENTITYBUS");

        session.createSQLQuery("SELECT EID, ENUMBER FROM ENTITYBUS");
        session.createSQLQuery("select c from ENTITYBUS c");

        /*SharedSessionContract contract = getSession();
        contract.createNamedQuery("My.Test");
        contract.createNamedQuery("My.Test", Entitybus.class);
        contract.createQuery("select c from Entitybus c");
        contract.createQuery("select c from Entitybus c", Entitybus.class);*/

        try {
            System.out.println("querying all the managed entities...");
            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                final String entityName = entityType.getName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }
        } finally {
            session.close();
        }
    }
    public void test(){
        Configuration configuration = new Configuration();
        configuration.configure("hibernate_sp.cfg.xml");
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());

        String hql = "FROM Entitybus E WHERE E.id > 10 ORDER BY E.id DESC";
            Session session = sessionFactory.openSession();
            Query q = session.createQuery(hql);
    }

    public void testHqlNotInjected() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
        Session session = sessionFactory.openSession();
        String hql = "FROM Entitybus E WHERE E.id > 10 ORDER BY E.id DESC";
        org.hibernate.Query q = session.createQuery(hql);
        org.hibernate.Query query = session.createQuery("from Entitybus where id = :code ");

    }
}