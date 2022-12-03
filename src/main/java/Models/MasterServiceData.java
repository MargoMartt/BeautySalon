package Models;

import java.util.ArrayList;

public class MasterServiceData {
    private ArrayList<MasterService> masters = new ArrayList<>();

    public void setData(MasterService master) {
        masters.add(master);
    }

    public ArrayList<MasterService> getData() {
        return masters;
    }
}