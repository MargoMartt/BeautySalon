package Enums;

public enum Roles {
    VISITOR ("Посетитель", 1) ,
    SALON_ADMIN ("Администратор Салона", 2),
    ADMIN("Администратор", 3);
    private String value;
    private int idRole;
     private Roles(String value, int id){
        this.value = value;
        this.idRole = id;
    }

    public String getValue() {
        return value;
    }

    public int getId() {
        return idRole;
    }
}
