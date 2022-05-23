import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Executable {
    private List <User> userList;


    public Executable(List<User> userList) {
        this.userList = userList;
    }

    public List<User> getUserList() {
        return userList;
    }


    public Flight reservationCycle(){
        Scanner scanner = new Scanner(System.in);
        char op= 27;
        User user;
        PlaneType planeType;  // FIJARSE SI ES MEJOR STRING O TIPO ENUM
        LocalDateTime date;
        int selector;
        City origin = null;
        City destination = null;
        int paxNumber=1;


        //************  ACA HAY QUE INGRESAR ANTES AL USUARIO O VERIFICAR QUE EXISTA **********************///

        do {

            System.out.println("Idique la fecha en la que desea reservar vuelo"); // se toma fecha
            date = LocalDateTime.parse(scanner.next());


            ////******************** EN LA SELECCION DE ORIGEN/DESTINO HAY QUE VERIFICAR QUE SE PUEDA ESE RECORRIDO*****************************/////
            System.out.println("Seleccione el ORIGEN del vuelo"); // desplega opciones y elgie con numero o con otra cosa, no por teclado
            System.out.println("1. Buenos Aires\t2. Cordoba\t3.Montevideo");
            selector = scanner.nextInt();

            do {
                switch (selector) {
                    case 1:
                        origin = City.BUENOS_AIRES;
                        System.out.println("A seleccionado " + origin);
                        break;
                    case 2:
                        origin = City.CORDOBA;
                        System.out.println("A seleccionado " + origin);
                        break;
                    case 3:
                        origin = City.MONTEVIDEO;
                        System.out.println("A seleccionado " + origin);
                        break;
                    default:
                        System.out.println("Seleccione un destino valido");
                }
            }while(origin!=null);


            System.out.println("Seoleccione el DESTINO del vuelo"); // idem
            System.out.println("1. Cordoba\t2. Santiago de Chile\t3.Montevideo");
            do {
                switch (selector) {
                    case 1:
                        destination = City.CORDOBA;
                        System.out.println("A seleccionado " + destination);
                        break;
                    case 2:
                        destination = City.SANTIAGO_DE_CHILE;
                        System.out.println("A seleccionado " + destination);
                        break;
                    case 3:
                        destination = City.MONTEVIDEO;
                        System.out.println("A seleccionado " + destination);
                        break;
                    default:
                        System.out.println("Seleccione un destino valido");
                }
            }while(destination!=null);

            System.out.println("Indique la cantidad de acompañantes"); // toma int
            paxNumber = scanner.nextInt(); // verificar la cantidad disponible de lugares

            System.out.println("Se mostraran los aviones disponibles para la fecha deseada");  // desplega aviones disponibles


            System.out.println("El cosoto total del vuelo es: ");  // calcula y muetra costo


            System.out.println("Confirme o decline la seleccion");  // si confiram sale y muestra cartel de confirmacion || si sale reinicia y muestra cartel de cancelacion



        }while(op!=27);

        return new Flight(user,planeType,date,origin,destination,paxNumber);
    }

    public User checkAndGetUser (String email){
        User existingUser = null;

        for (User user : userList) {
            if (email.equals(user.getEmail())) {
                existingUser = user;
            }
        }

        return existingUser;
    }

    public void logIn (){
        Scanner scanner = new Scanner(System.in);
        String mail;
        String pass;

        System.out.println("Ingrese su mail");
        mail = scanner.nextLine();

        User user = checkAndGetUser(mail);

        System.out.println("Ingrese contraseña");
        pass = scanner.nextLine();

        if(user!=null){

            System.out.println("Logueado con exito");

        }else{
            System.out.println("Crearemos su usuario a continuacion");
            user = createsUser(mail);
        }


    }

    public User createsUser (String mail){   /// Hay que captar todos los errores que puedan saltar en validacion
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese su nombre");
        String name = scanner.nextLine();
        System.out.println("Ingrese su apellido");
        String surname = scanner.nextLine();
        System.out.println("Ingrese su DNI");
        int dni = scanner.nextInt();
        System.out.println("Ingrese su edad");
        int age = scanner.nextInt();
        System.out.println("Ingrese su contraseña");
        String password= scanner.nextLine();;
        ///////// comprobacion de contraseña valida /////////////////

        String passwordVerification;

        int i =0;
        boolean flag;
        do {
            System.out.println("Vuelva a ingresar la contraseña para comprobarla");
            passwordVerification = scanner.nextLine();
            flag = password.equals(passwordVerification);
            i++;
        } while (i!= 3 && !flag);

        if(!flag){
            System.out.println("Las contraseñas no son las mismas, vuelva a intentarlo desde el principio");
        }


        return new User(name,surname,dni,age,mail,password);
    }

    public boolean checkEmail(String email){
        boolean acceptable = false;

        if(email.contains("@") && email.contains(".com")){

            char[] array = email.toCharArray();
           for(int i=array.length;i>=0; i--){
               if(Character.isUpperCase(array[i])){
                   acceptable=true;
                   array.
               }

           }
        }
        return acceptable;
    }




}
