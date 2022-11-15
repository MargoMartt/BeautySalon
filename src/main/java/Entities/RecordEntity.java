package Entities;
import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "record", schema = "salonbeauty", catalog = "")
public class RecordEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "record_id", nullable = false)
    private int recordId;
    @Basic
    @Column(name = "time", nullable = true)
    private Time time;
    @Basic
    @Column(name = "date", nullable = true)
    private Date date;
    @Basic
    @Column(name = "total_cost", nullable = true, precision = 0)
    private BigInteger totalCost;
    @Basic
    @Column(name = "id_user", insertable = false, updatable = false, nullable = false)
    private int idUser;
    @Basic
    @Column(name = "servise_id", insertable = false, updatable = false, nullable = false)
    private int serviseId;
    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false)
    private UsersEntity usersByIdUser;
    @ManyToOne
    @JoinColumn(name = "servise_id", referencedColumnName = "servise_id", nullable = false)
    private ServiceEntity serviceByServiseId;

    public RecordEntity() {
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigInteger getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigInteger totalCost) {
        this.totalCost = totalCost;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getServiseId() {
        return serviseId;
    }

    public void setServiseId(int serviseId) {
        this.serviseId = serviseId;
    }

    public UsersEntity getUsersByIdUser() {
        return usersByIdUser;
    }

    public void setUsersByIdUser(UsersEntity usersByIdUser) {
        this.usersByIdUser = usersByIdUser;
    }

    public ServiceEntity getServiceByServiseId() {
        return serviceByServiseId;
    }

    public void setServiceByServiseId(ServiceEntity serviceByServiseId) {
        this.serviceByServiseId = serviceByServiseId;
    }
}
