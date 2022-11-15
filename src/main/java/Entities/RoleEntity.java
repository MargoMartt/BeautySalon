package Entities;
import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "role", schema = "salonbeauty", catalog = "")
public class RoleEntity {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_role", nullable = false)
    private int idRole;
    @Basic
    @Column(name = "role_name", nullable = true, length = 45)
    private String roleName;
    @OneToMany(mappedBy = "roleIdRole")
    private Collection<UsersHasRoleEntity> usersHasRolesByIdRole;

    public RoleEntity() {
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Collection<UsersHasRoleEntity> getUsersHasRolesByIdRole() {
        return usersHasRolesByIdRole;
    }

    public void setUsersHasRolesByIdRole(Collection<UsersHasRoleEntity> usersHasRolesByIdRole) {
        this.usersHasRolesByIdRole = usersHasRolesByIdRole;
    }
}
