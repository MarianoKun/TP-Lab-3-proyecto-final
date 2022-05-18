import java.util.Objects;

public class User {
    private String name;
    private String surname;
    private int dni;
    private int age;

    public User() {
    }

    public User(String name, String surname, int dni, int age) {
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.age = age;
    }

    // region GETTER Y SETTERS
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

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // endregion


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User usuario = (User) o;
        return dni == usuario.dni && age == usuario.age && Objects.equals(name, usuario.name) && Objects.equals(surname, usuario.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, dni, age);
    }

    @Override
    public String toString() {
        return "Usuario: " +
                "\nName: \t" + name +
                "\nsurname: \t" + surname +
                "\ndni: \t" + dni +
                "\nage: \t" + age ;
    }


}
