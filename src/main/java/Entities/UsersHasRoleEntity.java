package Entities;

import javax.persistence.*;

@Entity
@Table(name = "users_has_role", schema = "salonbeauty", catalog = "")
@IdClass(UsersHasRoleEntityPK.class)
public class UsersHasRoleEntity {
    public UsersHasRoleEntity(int usersIdUser, int roleIdRole) {
        this.usersIdUser = usersIdUser;
        this.roleIdRole = roleIdRole;
    }

    @Override
    public String toString() {
        return "UsersHasRoleEntity{" +
                "usersIdUser=" + usersIdUser +
                ", roleIdRole=" + roleIdRole +
                '}';
    }

    public UsersHasRoleEntity(int roleIdRole) {
        this.roleIdRole = roleIdRole;
    }


    public UsersHasRoleEntity() {
    }

//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "users_id_user", nullable = false)
    private int usersIdUser;
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "role_id_role", nullable = false)
    private int roleIdRole;

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
}
