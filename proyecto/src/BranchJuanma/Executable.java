package BranchJuanma;

import PlanePackage.Planes;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

public  class Executable {
    private List <User> userList;
    private List <Flight> flightList;
    private List <Planes> planeList;

    public Executable(List<User> userList, List<Flight> flightList, List<Planes> planeList) {
        this.userList = userList;
        this.flightList = flightList;
        this.planeList = planeList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Flight> getFlightList() {
        return flightList;
    }

    public void setFlightList(List<Flight> flightList) {
        this.flightList = flightList;
    }

    public List<Planes> getPlaneList() {
        return planeList;
    }

    public void setPlaneList(List<Planes> planeList) {
        this.planeList = planeList;
    }

    /**
     * Ciclo principal de la app
     * llama a logIn
     * define si muestra menu ADMIN o USER
     */
    public void appCycle(){

        while(true) {     /// buscar la excepcion correspondiente a este loop
            System.out.println("\t\tLOG IN");
            try {
                User user = logIn();
                if (user instanceof Admin) {
                    adminMenu();
                } else {
                    userMenu();
                }
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }
    }

    /**
     * Menu de usuario
     */
    public void userMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean active = true;

        while (active) {
            int op;
            scanner.nextLine();
            userMenuList();
            op = scanner.nextInt();

            switch (op) {
                case 1:
                    System.out.println("NUEVA RESERVA");
                    break;
                case 2:
                    System.out.println("VER VUELOS"); // LISTA VUELOS FILTRAR POR USUARIO
                    break;
                case 3:
                    System.out.println("CANCELAR VUELO");  // ELIMINAR DE FLIGHTLIST siempre y cuando sea con mas de 24 hs de anticipacion
                    break;
                case 4:
                    // OTRO SWITCH
                    /// VER DATOS DE PERFIL
                    /// VER GASTO TOTAL    // LISTA VUELOS FILTRAR POR USUARIO y sumar gastos
                    /// MODIFICAR mail/password  /// todo  vamo a ve
                    break;
                case 5:
                    /// BAJA DE UISUARIO todo // seria solo borrarlo de la lista de usuarios, pero se mantianen sus datos en las demas listas.
                    break;
                case 6:
                    // LOG OUT
                    System.out.println("Gracias por usar AeroTaxi");
                    active = false;
                    break;
                default:
                    /// VOLVER AL MENU
                    break;
            }
        }
    }

    /**
     * Menu de administrador
     */
    public void adminMenu() {

        Scanner scanner = new Scanner(System.in);
        boolean active= true;

        while(active){
            int op;
            scanner.nextLine();
            adminMenuList();
            op = scanner.nextInt();

            switch (op){              /// OPCION AGREGAR AVION y opcion AGREGAR ADMIN // opcion BORRAR ADMIN LOGUEADO
                case 1:
                    System.out.println("NUEVA RESERVA");
                    break;
                case 2:
                    System.out.println("VER VUELOS POR FECHA"); // TODO
                    break;
                case 3:
                    System.out.println("CANCELAR VUELO");  //  hay que copiar lo mismod e USUARIO
                    break;
                case 4:
                    System.out.println("MUESTRA USUARIOS");  // + La categoría del mejor avión utilizado + Total gastado de todos sus vuelos
                    System.out.println(getUserList());
                    scanner.nextLine();
                    break;
                case 5:
                    /// LOG OUT
                    System.out.println("Gracias por usar AeroTaxi");
                    active = false;
                    //System.exit(0); // 0 para salir
                    break;
                default:
                    System.out.println("VOLVIENDO AL MENU");
                    break;
            }
        }
    }

    /**
     * listado del menu de usuario
     */
    public void userMenuList(){
        System.out.println("\t\t\tAERO TAXI\n");
        System.out.println("\t\t1.\tNueva Reserva");
        System.out.println("\t\t2.\tVer vuelos");
        System.out.println("\t\t3.\tCancelar vuelo");
        System.out.println("\t\t4.\tVer perfil");
        System.out.println("\t\t5.\tBaja de usuario");
        System.out.println("\t\t6.\tLog out");
    }
    /**
     * listado del menu de administrador
     */
    public void adminMenuList(){
        System.out.println("\t\t\tAERO TAXI\n");
        System.out.println("\t\t1.\tNueva Reserva");
        System.out.println("\t\t2.\tVer vuelos por fecha");
        System.out.println("\t\t3.\tCancelar vuelo");
        System.out.println("\t\t4.\tMuestra Usuarios");
        System.out.println("\t\t5.\tLog out");
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

    /**
     * toma por parametro un mail para chequear si
     * esta en la lista de usuarios
     * @param email String - mail a chequear
     * @return Usuario o null si no esta
     */
    public User checkAndGetUser (String email){
        User existingUser = null;

        for (User user : userList) {
            if (email.equals(user.getEmail())) {
                existingUser = user;
            }
        }

        return existingUser;
    }

    /**
     * consulta si existe el usuario en la lista de usuarios
     * @param email String - mail a chequear
     * @return boolean - true si es encontrado
     */
    public boolean checkUser (String email){
        boolean existingUser = false;

        for (User user : userList) {
            if (email.equals(user.getEmail())) {
                existingUser = true;
            }
        }

        return existingUser;
    }

    /**
     * Verifica que el pass, pertenezca al usuario
     * @param pass String - password
     * @return boolean - true si es correcto
     */
    public boolean checkPassword (User user,String pass){
        boolean checks = false;

       // for (User user : userList) {
            if (pass.equals(user.getPassword())) {
                checks = true;
            }
    //    }

        return checks;
    }
/// region LOGIN VIEJO
//    public void logIn (){
//        Scanner scanner = new Scanner(System.in);
//
//
//        String mail = validateEmail();
//        String pass;
//        String passVerification;
//
//        User user = checkAndGetUser(mail);
//
//
//        if(user!=null){
//            boolean valid = false;
//            while(!valid) {
//                System.out.println("Ingrese contraseña");
//                pass = validatePassword();
//
//                if(user.getPassword().equals(pass)){
//                    valid=true;
//                    System.out.println("Logueado con exito");
//                }else{
//                    int i = 0;
//                    do {
//                        System.out.println("Contraseña incorrecta, ingresela nuevamente");
//                        passVerification = scanner.nextLine();
//                        i++;
//                    }while(!pass.equals(passVerification) && i<3);
//
//                    if(pass.equals(passVerification)){
//                        valid = true;
//                    }
//                }
//            }
//        }else{
//            System.out.println("Crearemos su usuario a continuacion");
//            user = createsUser(mail);
//
//        }
//
//    }
/// endregion

    /**
     * Ciclo de logIn de la aplicacion.
     * toma datos de mail y contraseña
     * Si el usaurio no existe lo manda a su creacion
     * Si existe, verifica que las entradas sean correctas para continuar
     * @return User - usuario ya registrado o usuario recien creado
     */
    public User logIn (){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido a Aerotaxi");
        String mail = emailInput();
        String pass;

        User user = checkAndGetUser(mail);

       if(user!=null) {
           boolean validPass = false;
           System.out.println("Ingrese su contraseña");
           pass = scanner.nextLine();

           if (checkPassword(user, pass)) {
               System.out.println("Logueado con exito");
           }else {
               int i = 0;
               while (!validPass && i < 3) {
                   System.out.println("Contraseña incorrecta, ingresela nuevamente o inicie la recuperacion");
                   pass = scanner.nextLine();
                   i++;

                   if (pass.equals(user.getPassword())) {
                       validPass = true;
                   }
               }
               if (i == 3) {
                   System.out.println("Intente nuevamente en otra ocasion o inicie la recuperacion");
                   System.exit(0);
               }
           }
       }else {
           user = createsUser(mail);
           System.out.println("Usuario creado con exito");
       }
        return user;
    }

    /**
     * Crea y devuelve usuario
     * @param mail
     * @return User - usuario creado en este metodo
     */
    public User createsUser (String mail){   /// Hay que captar todos los errores que puedan saltar en validacion

       String name = validateNames("Ingrese su nombre");
       String surname = validateNames("Ingrese su apellido");
       String dni = validateDNI();
       String age = validateAge();
       String password = validatePassword();

       User user = new User(name,surname,dni,age,mail,password);
       userList.add(user);

       return user;
    }

    /**
     * Crea y retorna ADMIN
     * @return nuevo ADMIN
     */
    public User createsAdmin () { /// PARA AGREGAR A MENU DE ADMIN
        String mail = emailInput();
        String name = validateNames("Ingrese el nombre del nuevo administrador");
        String surname = validateNames("Ingrese el apellido");
        String password = validatePassword();

        Admin admin = new Admin(name, surname, mail, password);
        userList.add(admin);

        return admin;
    }

    /**
     * Toma mail por pantalla, verifica su validez y la comrpueba en 2da instancia
     * regex valida  "nombre"  "@" "casilla"  "."  "dominio"  "2do dom opcional"
     * @return String - email del user/admin
     * @throws PatternSyntaxException
     */
    public String emailInput() throws PatternSyntaxException {
        String regex = "^([a-z\\d\\._-]{1,30})@([a-z\\d_-]{2,15})\\.([a-z]{2,8})(\\.[a-z]{2,8})?$";

        Scanner scanner = new Scanner(System.in);
        boolean valid = false;
        String email = null;
        String emailVerification = null;

        while (!valid) {
            System.out.println("\nIngrese su email");
            email = scanner.nextLine();
            if(checkUser(email)){
                return email;

            }else if(!email.matches(regex)) {

                System.out.println("El formato del email es invalido o no existe usuario con ese mail, intentelo nuevamente");
                System.out.println("sino, crearemos su usuario a continuacion");

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

    /**
     * Toma dni por pantalla, verifica su validez
     * regex valida  "primer num 1 al 9" "siguientes 6/7 numeros del 0 al 9"
     * @return String - DNI del user
     * @throws PatternSyntaxException
     */
    public String validateDNI() throws PatternSyntaxException {
        String regex = "^[1-9][0-9]{6,7}$";
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

    /**
     * Toma nombre/apellido por pantalla, verifica su validez
     * regex valida  "letras de la a a la z mayus o minus" minimo un bloque de nombre/apellido, maximo 3
     * @return String - DNI del user
     * @throws PatternSyntaxException
     */
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
        return name.toUpperCase();
    }

    /**
     * Valida formato de fecha
     * regex valida  //0op+1/9 o 10al29 o 30o31 "/"    1 al 12       "/" 19 o 20 + cualquier numero de 2 cifras
     * @param date - String con formato de fecha
     * @return true/false si es valido
     * @throws PatternSyntaxException
     */
    public boolean validateDate(String date) throws PatternSyntaxException {
        String regex = "^(0?[1-9]|[12][0-9]|3[01])[\\/](0?[1-9]|1[012])[\\/](20)(\\d{2})$";
        return date.matches(regex);
    }

    /**
     * Valida la edad del usuario, no acepta menores de 18 ni mayores de 99
     * regex valida "nuemero 18 o 19" o "cualquier valor entre 20 y 99"-
     * @return  String - edad ingresada y validada
     * @throws PatternSyntaxException
     */
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

    /**
     * Valida el password para el registro de usuarios/admins
     * regex valida minumo 1 mauscula, 1 minuscula, un numero, entre 8 y 15 caracteres
     * regex valida con:
     * // ?= positive lookahead
     * //        // .* tiene que pasar al menos una vez para que considere la cadena
     * //        // \S solo considera caracteres que no sean saltos de linea o espacios
     * //        // .{8,15} al menos entre 8 y 15
     * @return String -  password validado
     * @throws PatternSyntaxException
     */
    public String validatePassword() throws PatternSyntaxException {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,15}$";

        Scanner scanner = new Scanner(System.in);
        boolean valid = false;
        String pass = null;
        String passVerification = null;

        while (!valid) {
            System.out.println("Ingrese el password a utilizar");
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

    /**
     * Valida la carga de un int. Si el input es invalido, se captura la excepcion y se continua pidiendo hasta tener exito
     * @param msg mensaje a desplegar cuando se pide un int por pantalla al usuario
     * @return int - int cargado por pantalla
     */
    public int intInput(String msg) {

        while(true){
        Scanner scanner = new Scanner(System.in);
        int rta;

            try {
                System.out.println(msg);
                rta = scanner.nextInt();
                return rta;
            } catch (InputMismatchException ime) {
                System.out.println("debe ingresar in valor entero");
            } //  no se si tiene que ir un finally con un scanner.close()
        }

    }



}
