package Models;

public class User {
    private int userId;
    private String role;
    private Integer idRole;
    private String userName;
    private String userSurname;
    private String login;
    private String password;


    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setData(String userName, String userSurname, String login, String password) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.login = login;
        this.password = password;
    }

    public User(String userName, String userSurname, String login, String password) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.login = login;
        this.password = password;
    }

    public User(int idRole, String userName, String userSurname, String login, String password) {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public User() {
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
