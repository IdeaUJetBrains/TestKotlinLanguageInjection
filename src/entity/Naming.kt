package entity

import javax.persistence.Basic
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Naming {
    @get:Id
    @get:Column
    var id: Int = 0
    @get:Basic
    @get:Column
    var number: String? = null

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false

        val naming = o as Naming?

        if (id != naming!!.id) return false
        return if (if (number != null) number != naming.number else naming.number != null) false else true

    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + if (number != null) number!!.hashCode() else 0
        return result
    }
}
