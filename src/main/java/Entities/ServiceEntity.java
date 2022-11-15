package Entities;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Collection;

@Entity
@Table(name = "service", schema = "salonbeauty", catalog = "")
public class ServiceEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "service_id", nullable = false)
    private int serviceId;
    @Basic
    @Column(name = "service_name", nullable = false, length = 45)
    private String serviceName;
    @Basic
    @Column(name = "service_price", nullable = false, precision = 0)
    private BigInteger servicePrice;
    @Basic
    @Column(name = "servicecol", nullable = false, length = 45)
    private String servicecol;
    @Basic
    @Column(name = "master_id", nullable = false)
    private int masterId;
    @OneToMany (mappedBy = "serviceId")
    private Collection<RecordEntity> recordsByServiceId;
    @ManyToOne
    @JoinColumn(name = "master_id", referencedColumnName = "master_id", insertable = false, updatable = false, nullable = false)
    private BeautyMastersEntity beautyMastersByMasterId;

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public BigInteger getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(BigInteger servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getServicecol() {
        return servicecol;
    }

    public void setServicecol(String servicecol) {
        this.servicecol = servicecol;
    }

//    public int getMasterId() {
//        return masterId;
//    }
//
//    public void setMasterId(int masterId) {
//        this.masterId = masterId;
//    }

    public Collection<RecordEntity> getRecordsByServiceId() {
        return recordsByServiceId;
    }

    public void setRecordsByServiceId(Collection<RecordEntity> recordsByServiceId) {
        this.recordsByServiceId = recordsByServiceId;
    }

    public BeautyMastersEntity getBeautyMastersByMasterId() {
        return beautyMastersByMasterId;
    }

    public void setBeautyMastersByMasterId(BeautyMastersEntity beautyMastersByMasterId) {
        this.beautyMastersByMasterId = beautyMastersByMasterId;
    }
}
