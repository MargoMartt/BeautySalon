package Entities;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
@Embeddable
public class UsersHasRoleEntityPK implements Serializable {
    private int usersIdUser;

    private int roleIdRole;

    public UsersHasRoleEntityPK() {
    }

    public int getUsersIdUser() {
        return usersIdUser;
    }

    public void setUsersIdUser(int usersIdUser) {
        this.usersIdUser = usersIdUser;
    }

    public int getRoleIdRole() {
        return roleIdRole;
    }

    public void setRoleIdRole(int roleIdRole) {
        this.roleIdRole = roleIdRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersHasRoleEntityPK that = (UsersHasRoleEntityPK) o;
        return usersIdUser == that.usersIdUser && roleIdRole == that.roleIdRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(usersIdUser, roleIdRole);
    }
}
