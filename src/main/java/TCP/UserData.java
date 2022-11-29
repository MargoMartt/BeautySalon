package TCP;

import java.util.ArrayList;

public class UserData {
    private ArrayList<RegistrationPayload> data = new ArrayList<>();

    public void setData(RegistrationPayload registrationPayload) {
        data.add(registrationPayload);
    }

    public ArrayList<RegistrationPayload> getData() {
        return data;
    }
}
