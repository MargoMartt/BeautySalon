package Entities;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "service", schema = "salonbeauty", catalog = "")
public class ServiceEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "service_id", nullable = false)
    private int serviceId;
    @Basic
    @Column(name = "service_name", nullable = true, length = 45)
    private String serviceName;
    @Basic
    @Column(name = "service_price", nullable = true, precision = 0)
    private Double servicePrice;
    @Basic
    @Column(name = "master_id", nullable = false)
    private int masterId;

    public ServiceEntity() {
    }

    public int getserviceId() {
        return serviceId;
    }

    public void setserviceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(Double servicePrice) {
        this.servicePrice = servicePrice;
    }

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }
}
