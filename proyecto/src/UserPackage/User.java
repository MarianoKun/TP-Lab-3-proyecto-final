package UserPackage;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class User implements Serializable {
    private UUID id;
    private String name;
    private String surname;
    private String dni;
    private String age;
    private String email; // funciona como nombre de usuario
    private String password; // verificacion de al menos una mayuscula y un numero (ver si se pueden quitar simbolos)

    public User() {
    }

//    public User(String name,String surname){
//        this.id = UUID.randomUUID();
//        this.name = name;
//        this.surname = surname;
//        this.dni = "";
//        this.age = "";
//        this.email = "admin"; //"admin@admin.com";
//        this.password = "admin"; //"Admin100";
//    }

    // PARA ADMIN
public User(String name, String surname,String email, String password) {
    this.id = UUID.randomUUID();
    this.name = name;
    this.surname = surname;
    this.email = email;
    this.password = password;
}

    public User(String name, String surname, String dni, String age, String email, String password) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.age = age;
        this.email = email;
        this.password = password;
    }
// region GETTER Y SETTERS

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // endregion

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(dni, user.dni) && Objects.equals(age, user.age) && Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, dni, age, email, password);
    }

    @Override
    public String toString() {
        return  "Name: \t\t" + name +
                "\nSurname: \t" + surname +
                "\nEmail: \t\t" + email +
                "\nDNI: \t\t" + dni +
                "\nAge: \t\t" + age +
                "\n-----------------------" ;

    }


}
