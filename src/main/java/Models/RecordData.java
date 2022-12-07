package Models;

import java.util.ArrayList;

public class RecordData {
    private ArrayList<Record> records = new ArrayList<>();

    public void setData(Record record) {
        records.add(record);
    }

    public ArrayList<Record> getData() {
        return records;
    }
}
