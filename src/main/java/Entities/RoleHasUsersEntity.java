package Entities;

import javax.persistence.*;

@Entity
@Table(name = "users_has_role", schema = "salonbeauty", catalog = "")
@IdClass(RoleHasUsersEntityPK.class)
public class RoleHasUsersEntity {
    @Override
    public String toString() {
        return "RoleHasUsersEntity{" +
                "idRole=" + idRole +
                ", usersIdUser=" + usersIdUser +
                '}';
    }

    public RoleHasUsersEntity(int usersIdUser, int idRole) {
        this.usersIdUser = usersIdUser;
        this.idRole = idRole;
    }

    public RoleHasUsersEntity() {
    }

//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "role_id_role", nullable = false, length = 15)
    private int idRole;
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "users_id_user", nullable = false)
    private int usersIdUser;
    @ManyToOne
    @JoinColumn(name = "role_id_role", referencedColumnName = "id_role", insertable = false, updatable = false, nullable = false)
    private RoleEntity roleByRoleId;
    @ManyToOne
    @JoinColumn(name = "users_id_user", referencedColumnName = "id_user", insertable = false, updatable = false, nullable = false)
    private UsersEntity userByUserId;

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

//    public RoleEntity getRoleByRoleName() {
//        return roleByRoleName;
//    }
//
//    public void setRoleByRoleName(RoleEntity roleByRoleName) {
//        this.roleByRoleName = roleByRoleName;
//    }
}
