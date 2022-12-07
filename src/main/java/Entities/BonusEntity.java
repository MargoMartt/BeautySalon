package Entities;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "bonus", schema = "salonbeauty", catalog = "")
public class BonusEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "bonus_id", nullable = false)
    private int bonusId;
    @Basic
    @Column(name = "discount", nullable = true)
    private int discount;
    @Basic
    @Column(name = "certificate", nullable = true)
    private int certificate;
    @Basic
    @Column(name = "id_user", insertable = false, updatable = false, nullable = false)
    private int idUser;
    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false)
    private UsersEntity usersByIdUser;

    public BonusEntity() {
    }

    public BonusEntity(int idUser) {
        this.idUser = idUser;
    }

    public int getBonusId() {
        return bonusId;
    }

    public void setBonusId(int bonusId) {
        this.bonusId = bonusId;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getCertificate() {
        return certificate;
    }

    public void setCertificate(int certificate) {
        this.certificate = certificate;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public UsersEntity getUsersByIdUser() {
        return usersByIdUser;
    }

    public void setUsersByIdUser(UsersEntity usersByIdUser) {
        this.usersByIdUser = usersByIdUser;
    }
}