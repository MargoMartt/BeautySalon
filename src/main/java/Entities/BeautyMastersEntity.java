package Entities;

import javax.persistence.*;

@Entity
@Table(name = "beauty_masters", schema = "salonbeauty", catalog = "")
public class BeautyMastersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "master_id", nullable = false)
    private int masterId;
    @Basic
    @Column(name = "master_surname", nullable = false, length = 60)
    private String masterSurname;
    @Basic
    @Column(name = "master_name", nullable = false, length = 60)
    private String masterName;
    @Basic
    @Column(name = "activity", nullable = false, length = 100)
    private String activity;
    @Basic
    @Column(name = "work_experience", nullable = false)
    private int workExperience;

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

    public int getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(int workExperience) {
        this.workExperience = workExperience;
    }
}
