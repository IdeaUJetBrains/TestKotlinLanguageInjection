import javax.persistence.*;


/**
 * Created by Olga Pavlova on 9/16/2016.
 */
@Entity
@Table(name = "ENTITYBUS", uniqueConstraints={@UniqueConstraint(columnNames={"ENUMBER"})})//(name = "ENTITYBUS" , schema = "INFORMATION_SCHEMA", catalog = "TEST")
@NamedQuery(name = "My.Test", query = "SELECT e FROM Entitybus e WHERE e.enumber like :name")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "findStockByStockCodeNativeSQL",
                query = "select * from ENTITYBUS s where s.ENUMBER = :arg",
                resultClass = entity.Entitybus.class
        )
})
@SqlResultSetMapping(
        name = "BusValueMapping",
        classes = @ConstructorResult(
                targetClass = entity.Entitybus.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "enumber")}))
public class Entitybus
         {
    private int eid;


    @Id
    @SequenceGenerator(name="CUST_SEQ", allocationSize=25)
    @GeneratedValue(strategy=SEQUENCE, generator="CUST_SEQ")
    @Column(name = "EID", nullable = false)
    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }


    private String enumber;
    @Basic

    @Column(name = "ENUMBER", nullable = true, length = 255)
    public String getEnumber() {
        return enumber;
    }

    public void setEnumber(String enumber) {
        this.enumber = enumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        entity.Entitybus entitybus = (entity.Entitybus) o;

        if (eid != entitybus.eid) return false;
        if (enumber != null ? !enumber.equals(entitybus.enumber) : entitybus.enumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eid;
        result = 31 * result + (enumber != null ? enumber.hashCode() : 0);
        return result;
    }
}
