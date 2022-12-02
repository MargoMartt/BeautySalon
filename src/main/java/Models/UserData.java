package Models;

import java.util.ArrayList;

public class UserData {
    private ArrayList<User> data = new ArrayList<>();

    public void setData(User registrationPayload) {
        data.add(registrationPayload);
    }

    public ArrayList<User> getData() {
        return data;
    }
}
