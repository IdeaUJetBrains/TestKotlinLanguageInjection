import entity.Entitybus
import notentity.Bus

import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence
import javax.persistence.Query

object JpaMainKt {


    @JvmStatic
    fun main(args: Array<String>) {
        // Open a database connection
        // (create a new database if it doesn't exist yet):
        val emfactory = Persistence.createEntityManagerFactory("NewPersistenceUnit")

        val entitymanager = emfactory.createEntityManager()

        // Store 1000 Point objects in the database:
        entitymanager.transaction.begin()

        val bus = Bus()
        bus.id = 3
        bus.number = "Test4"
        entitymanager.persist(bus)
        entitymanager.transaction.commit()
        // Find the number of objects in the database:
        val q1 = entitymanager.createQuery("SELECT count(p) FROM Entitybus p")
        val q2 = entitymanager.createNamedQuery("My.Test").setParameter("name", "1*")
//        val q3 = entitymanager.createNamedStoredProcedureQuery("ReadUsingNamedRefCursors")
        val q4 = entitymanager.createNativeQuery("SELECT count(p) FROM Entitybus p")
//        val q5 = entitymanager.createStoredProcedureQuery("SELECT count(p) FROM Bus p")

        val q6 = entitymanager.createEntityGraph("SELECT count(p) FROM Bus p")
        val q5 = entitymanager.createQuery("SELECT c FROM Entitybus c WHERE c.enumber LIKE ?1")

        val q7 = entitymanager.createQuery("SELECT count(p)"+ " FROM Entitybus p", Entitybus::class.java)
        val q8 = entitymanager.createNativeQuery("SELECT *", Entitybus::class.java)

        val str1 = "SELECT c FROM Entitybus c WHERE c.enumber LIKE ?1"
        //String str2 = "WHERE c.enumber LIKE ?1";
        val q10 = entitymanager.createQuery(str1)
        /* Entitybus entitybus = new Entitybus( );
            entitybus.setEid(11);
            entitybus.setEnumber("Test4" );
            entitymanager.persist( entitybus );
            entitymanager.getTransaction( ).commit( );
            // Find the number of objects in the database:
            Query q1 = entitymanager.createQuery("SELECT count(p) FROM Entitybus p");*/


        println("Total : " + q1.singleResult)
        // Close the database connection:
        entitymanager.close()
        emfactory.close()
    }

}
