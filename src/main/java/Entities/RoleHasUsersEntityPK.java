package Entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class RoleHasUsersEntityPK implements Serializable {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "role_id_role", nullable = false, length = 15)
    private int idRole;
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "users_id_user", nullable = false)

    private int usersIdUser;

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public int getUsersIdUser() {
        return usersIdUser;
    }

    public void setUsersIdUser(int usersIdUser) {
        this.usersIdUser = usersIdUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleHasUsersEntityPK that = (RoleHasUsersEntityPK) o;
        return usersIdUser == that.usersIdUser && Objects.equals(idRole, that.idRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRole, usersIdUser);
    }
}
