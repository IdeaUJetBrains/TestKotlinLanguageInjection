import notentity.Bus;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JpaMainJava {




    public static void main(String[] args) {

            // Open a database connection
            // (create a new database if it doesn't exist yet):
            EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "NewPersistenceUnit" );

            EntityManager entitymanager = emfactory.createEntityManager( );

            // Store 1000 Point objects in the database:
            entitymanager.getTransaction().begin();

            Bus bus = new Bus( );
            bus.setId(3);
            bus.setNumber("Test4" );
            entitymanager.persist( bus );
            entitymanager.getTransaction( ).commit( );
            // Find the number of objects in the database:
            Query q1;
            q1 = entitymanager.createQuery("SELECT count(p) FROM Bus p");
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
