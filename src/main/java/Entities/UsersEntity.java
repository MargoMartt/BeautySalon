package Entities;

import TCP.Request;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "users", schema = "salonbeauty", catalog = "")
public class UsersEntity {

    public UsersEntity(String userName, String userSurname, String login, String password) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.login = login;
        this.password = password;
    }

    public UsersEntity() {
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_user", nullable = false)
    private int idUser;
    @Basic
    @Column(name = "user_name", nullable = false, length = 45)
    private String userName;
    @Basic
    @Column(name = "user_surname", nullable = false, length = 45)
    private String userSurname;
    @Basic
    @Column(name = "login", nullable = false, length = 45)
    private String login;
    @Basic
    @Column(name = "password", nullable = false, length = 45)
    private String password;
    @OneToMany(mappedBy = "idUser")
    private Collection<RecordEntity> recordsByIdUser;
    @OneToMany(mappedBy = "usersIdUser")
    private Collection<RoleHasUsersEntity> roleHasUsersByIdUser;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<RecordEntity> getRecordsByIdUser() {
        return recordsByIdUser;
    }

    public void setRecordsByIdUser(Collection<RecordEntity> recordsByIdUser) {
        this.recordsByIdUser = recordsByIdUser;
    }

    public Collection<RoleHasUsersEntity> getRoleHasUsersByIdUser() {
        return roleHasUsersByIdUser;
    }

    public void setRoleHasUsersByIdUser(Collection<RoleHasUsersEntity> roleHasUsersByIdUser) {
        this.roleHasUsersByIdUser = roleHasUsersByIdUser;
    }

    @Override
    public String toString() {
        return "UsersEntity{" +
                "idUser=" + idUser +
                ", userName='" + userName + '\'' +
                ", userSurname='" + userSurname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
