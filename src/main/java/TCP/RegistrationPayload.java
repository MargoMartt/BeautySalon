package TCP;

import Entities.RoleEntity;

public class RegistrationPayload{
    private int idRole;
    private String userName;
    private String userSurname;
    private String login;
    private String password;

    public void setRole(int idRole) {
        this.idRole = idRole;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RegistrationPayload() {
    }

    @Override
    public String toString() {
        return "RegistrationPayload{" +
                "idRole=" + idRole +
                ", userName='" + userName + '\'' +
                ", userSurname='" + userSurname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
