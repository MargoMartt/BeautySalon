package Entities;

import TCP.Request;

import javax.persistence.*;

@Entity
@Table(name = "role", schema = "salonbeauty", catalog = "")
public class RoleEntity {
    public RoleEntity(int idRole) {
        this.idRole = idRole;
    }

    public RoleEntity() {
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="id_role", nullable = false)
    private int idRole;

    @Column(name = "role_name", nullable = false, length = 15)
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "roleName='" + roleName + '\'' +
                "idRole='" + idRole + '\'' +
                '}';
    }
}
