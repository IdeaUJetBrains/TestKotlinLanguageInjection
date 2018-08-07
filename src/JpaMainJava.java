import entity.Entitybus;
import notentity.Bus;

import javax.persistence.*;


public class JpaMainJava {



        public static void main(String[] args) {
            // Open a database connection
            // (create a new database if it doesn't exist yet):
            EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "NewPersistenceUnit" );

            EntityManager entitymanager = emfactory.createEntityManager( );

            // Store 1000 Point objects in the database:
            entitymanager.getTransaction().begin();

            Bus bus = new Bus( );
            bus.setId(7);
            bus.setNumber("Test7" );
            entitymanager.persist( bus );
            entitymanager.getTransaction( ).commit( );
            // Find the number of objects in the database:
            Query q1 = entitymanager.createQuery("SELECT count(p) FROM Entitybus p");
            Query q2 = entitymanager.createNamedQuery("My.Test").setParameter("name", "1*");
//            Query q3 = entitymanager.createNamedStoredProcedureQuery("ReadUsingNamedRefCursors");
            Query q4 = entitymanager.createNativeQuery("SELECT count(p) FROM Entitybus p");
//            Query q5 = entitymanager.createStoredProcedureQuery("SELECT count(p) FROM Bus p");
            EntityGraph<?> q6 = entitymanager.createEntityGraph("SELECT count(p) FROM Bus p");
            Query q5 = entitymanager.createQuery("SELECT c FROM Entitybus c WHERE c.enumber LIKE ?1");

            Query q7 = entitymanager.createQuery("SELECT count(p) FROM Entitybus p", Entitybus.class);
            Query q8 = entitymanager.createNativeQuery("SELECT *", Entitybus.class);

            Query q9 = entitymanager.createQuery("SELECT c FROM Entitybus "+" c WHERE c.enumber LIKE ?1");

            String str1 = "SELECT c FROM Entitybus c WHERE c.enumber LIKE ?1";
            //String str2 = "WHERE c.enumber LIKE ?1";
            Query q10 = entitymanager.createQuery(str1);

            Query q11 = entitymanager.createNativeQuery("SELECT count(p) FROM Entitybus p", "");
           /* Entitybus entitybus = new Entitybus( );
            entitybus.setEid(11);
            entitybus.setEnumber("Test4" );
            entitymanager.persist( entitybus );
            entitymanager.getTransaction( ).commit( );
            // Find the number of objects in the database:
            Query q1 = entitymanager.createQuery("SELECT count(p) FROM Entitybus p");*/


            System.out.println("Total : " + q1.getSingleResult());
            // Close the database connection:
            entitymanager.close();
            emfactory.close();
        }

}
