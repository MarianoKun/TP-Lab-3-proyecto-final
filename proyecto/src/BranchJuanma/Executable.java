package BranchJuanma;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

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
        return new Flight();
        //return new Flight(user,planeType,date,origin,destination,paxNumber);
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


        String mail = validateEmail();
        String pass;
        String passVerification;

        User user = checkAndGetUser(mail);


        if(user!=null){
            boolean valid = false;
            while(!valid) {
                System.out.println("Ingrese contraseña");
                pass = validatePassword();

                if(user.getPassword().equals(pass)){
                    valid=true;
                    System.out.println("Logueado con exito");
                }else{
                    int i = 0;
                    do {
                        System.out.println("Contraseña incorrecta, ingresela nuevamente");
                        passVerification = scanner.nextLine();
                        i++;
                    }while(!pass.equals(passVerification) && i<3);

                    if(pass.equals(passVerification)){
                        valid = true;
                    }
                }
            }
        }else{
            System.out.println("Crearemos su usuario a continuacion");
            user = createsUser(mail);

        }

    }

    public User createsUser (String mail){   /// Hay que captar todos los errores que puedan saltar en validacion

       String name = validateNames("Ingrese su nombre");
       String surname = validateNames("Ingrese su apellido");
       String dni = validateDNI();
       String age = validateAge();
       String password = validatePassword();

        ///////// comprobacion de datos ingresados valida /////////////////

        return new User(name,surname,dni,age,mail,password);
    }

//    public boolean validateEmail(String email) throws PatternSyntaxException {
//        String regex = "^([a-z\\d\\._-]{1,30})@([a-z\\d_-]{2,15})\\.([a-z]{2,8})(\\.[a-z]{2,8})?$";
//                           // nombre         @    casilla      .  dominio       2do dom opcional
//        return  email.matches(regex);
//    }


    public String validateEmail() throws PatternSyntaxException {
        String regex = "^([a-z\\d\\._-]{1,30})@([a-z\\d_-]{2,15})\\.([a-z]{2,8})(\\.[a-z]{2,8})?$";

        Scanner scanner = new Scanner(System.in);
        boolean valid = false;
        String email = null;
        String emailVerification = null;


        while (!valid) {
            System.out.println("Ingrese su email");
            email = scanner.nextLine();
            if (!email.matches(regex)) {
                System.out.println("El formato del email es invalido, intentelo nuevamente");
            } else {
                int i = 0;
                do {
                    System.out.println("Ingreselo nuevamente para comprobarlo");
                    emailVerification = scanner.nextLine();
                    i++;
                }while(!email.equals(emailVerification) && i<3);

                if(email.equals(emailVerification)){
                    valid = true;
                }

            }
        }

        return email;
    }

//    public boolean validateDNI(String DNI) throws PatternSyntaxException {
//        String regex = "^[0-9]{7,8}$";
//        // solo numeros de 7 u 8 cifras
//        return DNI.matches(regex);
//    }


    public String validateDNI() throws PatternSyntaxException {
        String regex = "^[0-9]{7,8}$";
        Scanner scanner = new Scanner(System.in);
        boolean valid = false;
        String DNI = null;

        while (!valid) {
            System.out.println("Ingrese su DNI sin puntos");
            DNI = scanner.nextLine();
            if (!DNI.matches(regex)) {
                System.out.println("El formato del DNI es incorrecto");
            } else {
                valid = true;
            }
        }
        return DNI;
    }


//    public boolean validateNames (String name) throws PatternSyntaxException {
//        String regex = "^([a-zA-Z]+[ ]?){1,3}$";  // se repite de 1 a 3 veces
//        // Mayusculas o minusculas, despues lo vamos a guardar todo con minuscula o mayuscula
//        return name.matches(regex);
//    }

    public String validateNames(String msg) throws PatternSyntaxException {
        String regex = "^([a-zA-Z]+[ ]?){1,3}$";
        Scanner scanner = new Scanner(System.in);
        boolean valid = false;
        String name = null;

        while (!valid) {
            System.out.println(msg);
            name = scanner.nextLine();
            if (!name.matches(regex)) {
                System.out.println("El formato de nombre ingresado es incorrecto");
            } else {
                valid = true;
            }
        }
        return name;
    }


    public boolean validateDate(String date) throws PatternSyntaxException {
        String regex = "^(0?[1-9]|[12][0-9]|3[01])[\\/](0?[1-9]|1[012])[\\/](20)(\\d{2})$";
                        //0op+1/9 o 10al29 o 30o31 "/"    1 al 12       "/" 19 o 20 + cualquier numero de 2 cifras

        // luego de verificar formato verificar con LOCALDATE que sea
        // excepcion DateTimeException

        return date.matches(regex);
    }

//    public boolean validateAge(String age) throws PatternSyntaxException {
//        String regex = "^(1[89]|[2-9][0-9])$";
//
////        if (age.matches(regex)){
////            int ageInt = Integer.parseInt(age);
////            if (ageInt < 18) {
////                System.out.println("Debe ser mayor de 18 años para registrarse en nuestra plataforma");
////                return false;
////            }
////        }
//
//        // no hay para mayores de 99 si fuiera mayor se le pide que ingrese 99
//        return true;
//    }

    public String validateAge() throws PatternSyntaxException {
        String regex = "^(1[89]|[2-9][0-9])$";

        Scanner scanner = new Scanner(System.in);
        boolean valid = false;
        String age = null;

        while (!valid) {
            System.out.println("Ingrese su edad");
            age = scanner.nextLine();
            if (!age.matches(regex)) {
                System.out.println("El formato ingresado es incorrecto o ud. es menor de 18 años");
            } else {
                valid = true;
            }
        }
        return age;
    }

//    public boolean validatePassword(String pass) throws PatternSyntaxException {
//        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,15}$";
//        // ?= positive lookahead
//        // .* tiene que pasar al menos una vez para que considere la cadena
//        // \S solo considera caracteres que no sean saltos de linea o espacios
//        // .{8,15} al menos entre 8 y 15
//        return pass.matches(regex);
//    }

    public String validatePassword() throws PatternSyntaxException {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,15}$";

        Scanner scanner = new Scanner(System.in);
        boolean valid = false;
        String pass = null;
        String passVerification = null;


        while (!valid) {
            System.out.println("Ingrese su password");
            pass = scanner.nextLine();
            if (!pass.matches(regex)) {
                System.out.println("El formato ingresado es incorrecto intentelo nuevamente");
            } else {
                int i = 0;
                do {
                    System.out.println("Ingreselo nuevamente para comprobarlo");
                    passVerification = scanner.nextLine();
                    i++;
                }while(!pass.equals(passVerification) && i<3);

                if(pass.equals(passVerification)){
                    valid = true;
                }
            }
        }
        return pass;
    }

}
