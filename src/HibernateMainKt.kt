import entity.Entitybus
import org.hibernate.*
import org.hibernate.query.Query
import org.hibernate.cfg.Configuration

import javax.persistence.metamodel.EntityType

object HibernateMainKt {
    private val ourSessionFactory: SessionFactory

    val session: Session
        @Throws(HibernateException::class)
        get() = ourSessionFactory.openSession()

    init {
        try {
            val configuration = Configuration()
            configuration.configure()

            ourSessionFactory = configuration.buildSessionFactory()
        } catch (ex: Throwable) {
            throw ExceptionInInitializerError(ex)
        }

    }

    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val session = session

        val createQuery = session.createQuery("select c from Entitybus c")
        session.createQuery("select c from Entitybus c", Entitybus::class.java)
        session.createNamedQuery("My.Test")
        session.createNamedQuery("My.Test", Entitybus::class.java)
        session.createNativeQuery("select c from Entitybus c")
//        session.createNativeQuery("select c from Entitybus c", Entitybus::class)
        session.createNativeQuery("select c from Entitybus c", "AuthorValueMapping")

        session.createSQLQuery("select c Entitybus c")
        session.createSQLQuery("select c from ENTITYBUS c")


        try {
            println("querying all the managed entities...")
            val metamodel = session.sessionFactory.metamodel
            for (entityType in metamodel.entities) {
                val entityName = entityType.name
                val query = session.createQuery("from " + entityName)
                println("executing: " + query.queryString)
                for (o in query.list()) {
                    println("  " + o)
                }
            }
        } finally {
            session.close()
        }
    }

   /* fun ttt(){
        val contract = session
        contract.createNamedQuery("My.Test")
        contract.createNamedQuery("My.Test", Entitybus::class.java)
        contract.createQuery("select c from Entitybus c")
        contract.createQuery("select c from Entitybus c", Entitybus::class.java)
    }*/
}
