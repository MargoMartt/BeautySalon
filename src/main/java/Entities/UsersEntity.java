package Entities;
import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "users", schema = "salonbeauty", catalog = "")
public class UsersEntity {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_user", nullable = false)
    private int idUser;
    @Basic
    @Column(name = "user_name", nullable = true, length = 45)
    private String userName;
    @Basic
    @Column(name = "user_surname", nullable = true, length = 45)
    private String userSurname;
    @Basic
    @Column(name = "login", nullable = true, length = 45)
    private String login;
    @Basic
    @Column(name = "password", nullable = true, length = 45)
    private String password;
    @Basic
    @Column(name = "balance", nullable = true, length = 45)
    private String balance;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usersIdUser", cascade = CascadeType.ALL)
    private Collection<UsersHasRoleEntity> usersHasRolesByIdUser;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public UsersEntity() {
    }

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

    public Collection<UsersHasRoleEntity> getUsersHasRolesByIdUser() {
        return usersHasRolesByIdUser;
    }

    public void setUsersHasRolesByIdUser(Collection<UsersHasRoleEntity> usersHasRolesByIdUser) {
        this.usersHasRolesByIdUser = usersHasRolesByIdUser;
    }

    @Override
    public String toString() {
        return "UsersEntity{" +
                "idUser=" + idUser +
                ", userName='" + userName + '\'' +
                ", userSurname='" + userSurname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", balance='" + balance +
                '}';
    }

}
