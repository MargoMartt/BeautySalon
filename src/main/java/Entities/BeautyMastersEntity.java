package Entities;
import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "beauty_masters", schema = "salonbeauty", catalog = "")
public class BeautyMastersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "master_id", nullable = false)
    private int masterId;
    @Basic
    @Column(name = "master_surname", nullable = true, length = 60)
    private String masterSurname;
    @Basic
    @Column(name = "master_name", nullable = true, length = 60)
    private String masterName;
    @Basic
    @Column(name = "activity", nullable = true, length = 100)
    private String activity;
    @Basic
    @Column(name = "work_experience", nullable = true)
    private Integer workExperience;
    @OneToMany(mappedBy = "serviceId")
    private Collection<ServiceEntity> servicesByMasterId;

    public BeautyMastersEntity() {
    }

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }

    public String getMasterSurname() {
        return masterSurname;
    }

    public void setMasterSurname(String masterSurname) {
        this.masterSurname = masterSurname;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Integer getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(Integer workExperience) {
        this.workExperience = workExperience;
    }

    public Collection<ServiceEntity> getServicesByMasterId() {
        return servicesByMasterId;
    }

    public void setServicesByMasterId(Collection<ServiceEntity> servicesByMasterId) {
        this.servicesByMasterId = servicesByMasterId;
    }

    @Override
    public String toString() {
        return "BeautyMastersEntity{" +
                "masterId=" + masterId +
                ", masterSurname='" + masterSurname + '\'' +
                ", masterName='" + masterName + '\'' +
                ", activity='" + activity + '\'' +
                ", workExperience=" + workExperience +
                '}';
    }
}
