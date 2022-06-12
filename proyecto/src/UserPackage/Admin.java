package UserPackage;

import java.io.Serializable;
import java.util.Objects;

public class Admin extends User implements Serializable {
    private boolean admin;

    public Admin() {
        this.admin = true;
    }


    public Admin(String name, String surname,String email,String pass) {
        super(name, surname, email, pass);
        this.admin = true;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Admin admin1 = (Admin) o;
        return admin == admin1.admin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), admin);
    }

    @Override
    public String toString() {
        return  "-----------------------------------" +
                "\n\033[1;94mAdmin: \t\t\t" + admin +
                "\n\033[0mName: \t\t\t" + super.getName() +
                "\nSurname: \t\t" + super.getSurname() + "\n";

    }
}
