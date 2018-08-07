package newutil;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateSessionFactory {
    private static final SessionFactory sessionFactory = buildSessionFactory1();

    private static SessionFactory buildSessionFactory1() {
        Configuration configuration = new Configuration();
        configuration.configure().setPhysicalNamingStrategy(MyPhysicalNamingStrategy.INSTANCE);
        return configuration.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
