package Models;

import java.util.ArrayList;

public class ServiceData {
    private ArrayList<Service> services = new ArrayList<>();

    public void setData(Service service) {
        services.add(service);
    }

    public ArrayList<Service> getData() {
        return services;
    }

}
