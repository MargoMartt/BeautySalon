package TCP;

import Entities.RoleEntity;

public class RegistrationPayload{
    private Integer idRole;
    private String userName;
    private String userSurname;
    private String login;
    private String password;
    private String balance;

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public void setData(String userName, String userSurname, String login, String password){
        this.userName = userName;
        this.userSurname = userSurname;
        this.login = login;
        this.password = password;
    }
    public RegistrationPayload(String userName, String userSurname, String login, String password) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.login = login;
        this.password = password;
    }

    public RegistrationPayload(int idRole, String userName, String userSurname, String login, String password) {
        this.idRole = idRole;
        this.userName = userName;
        this.userSurname = userSurname;
        this.login = login;
        this.password = password;
    }

    public int getIdRole() {
        return idRole;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

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
