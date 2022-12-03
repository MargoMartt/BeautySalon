package Models;

import java.util.ArrayList;

public class FinanceData {
    private ArrayList<Finance> finances = new ArrayList<>();

    public void setData(Finance finance) {
        finances.add(finance);
    }

    public ArrayList<Finance> getData() {
        return finances;
    }
}