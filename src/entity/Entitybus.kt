package entity

import javax.persistence.*
import javax.persistence.GenerationType.SEQUENCE

/**
 * Created by Olga Pavlova on 9/16/2016.
 */
@Entity
@Table(name = "ENTITYBUS", uniqueConstraints = arrayOf(UniqueConstraint(columnNames = arrayOf("ENUMBER"))))
@NamedQuery(name = "My.Test", query = "SELECT e FROM Entitybus e WHERE e.enumber like :name")
@NamedNativeQueries(NamedNativeQuery(name = "findStockByStockCodeNativeSQL", query = "select * from ENTITYBUS s where s.ENUMBER = :arg", resultClass = Entitybus::class))
//@SqlResultSetMapping(name = "AuthorValueMapping", classes = ConstructorResult(targetClass = Entitybus::class, columns = arrayOf(ColumnResult(name = "id", type = Long::class), ColumnResult(name = "firstname"), ColumnResult(name = "lastname"), ColumnResult(name = "numBooks", type = Long::class))))
class Entitybus {
    @get:Id
    @SequenceGenerator(name = "CUST_SEQ", allocationSize = 25)
    @GeneratedValue(strategy = SEQUENCE, generator = "CUST_SEQ")
    @get:Column(name = "EID", nullable = false)
    var eid: Int = 0

    @get:Basic
    @get:Column(name = "ENUMBER", nullable = true, length = 255)
    var enumber: String? = null

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false

        val entitybus = o as Entitybus?

        if (eid != entitybus!!.eid) return false
        return if (if (enumber != null) enumber != entitybus.enumber else entitybus.enumber != null) false else true

    }

    override fun hashCode(): Int {
        var result = eid
        result = 31 * result + if (enumber != null) enumber!!.hashCode() else 0
        return result
    }


}
