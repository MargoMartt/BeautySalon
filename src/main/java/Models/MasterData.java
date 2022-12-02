package Models;

import java.util.ArrayList;

public class MasterData {
    private ArrayList<Master> masters = new ArrayList<>();

    public void setData(Master master) {
        masters.add(master);
    }

    public ArrayList<Master> getData() {
        return masters;
    }
}
