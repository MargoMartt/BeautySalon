package Models;

import java.util.ArrayList;

public class ProfitabilityData {
    private double sum;

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    private ArrayList<Profitability> profitabilities = new ArrayList<>();

    public void setData(Profitability profitability) {
        profitabilities.add(profitability);
    }

    public ArrayList<Profitability> getData() {
        return profitabilities;
    }
}
