package Enums;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public enum RecordTime {
    NINE("09:00"),
    ELEVEN("11:00"),
    THIRTEEN("13:00"),
    FIFTEEN("15:00"),
    SEVENTEEN("17:00");

    private String time;

    private RecordTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }
}
