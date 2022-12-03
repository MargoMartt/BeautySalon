package Models;

import java.util.ArrayList;

public class Master {
    private int masterId;
    private String masterSurname;
    private String masterName;
    private String activity;
    private int workExperience;
    private String serviceName;
    private ArrayList<String> services = new ArrayList<>();

    public Master(String masterSurname, String masterName, String activity, int workExperience) {
        this.masterSurname = masterSurname;
        this.masterName = masterName;
        this.activity = activity;
        this.workExperience = workExperience;
    }

    public ArrayList<String> getServices() {
        return services;
    }

    public void addServices(String service){
        services.add(service);
    }
    public void setServices(ArrayList<String> services) {
        this.services = services;
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

    public int getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(int workExperience) {
        this.workExperience = workExperience;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Master() {
    }

    @Override
    public String toString() {
        return "Master{" +
                "masterId=" + masterId +
                ", masterSurname='" + masterSurname + '\'' +
                ", masterName='" + masterName + '\'' +
                ", activity='" + activity + '\'' +
                ", workExperience=" + workExperience +
                ", serviceName='" + serviceName + '\'' +
                '}';
    }
}
