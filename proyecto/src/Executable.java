import PlanePackage.*;
import UserPackage.Admin;
import UserPackage.User;

import java.awt.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.regex.PatternSyntaxException;   // TODO COMENTAR FUNCIONES

public class Executable {
    public List<User> userList;
    public List<Flight> flightList;
    public List<Planes> planeList;

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
    public void appCycle() {
        boolean loginError = false;

        while (!loginError) {     /// buscar la excepcion correspondiente a este loop
            System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "\n\t\t\tLOG IN" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "\tBienvenido a Aerotaxi" + ConsoleColors.RESET);

            try {
                User user = logIn();
                if (user instanceof Admin) {
                    adminMenu(user);
                } else if (user != null) {
                    userMenu(user);
                }
            } catch (NullPointerException npe) {
                npe.printStackTrace();
                loginError = true;
            }
        }
    }

    /**
     * Menu de usuario
     */
    public void userMenu(User user) {
        Scanner scanner = new Scanner(System.in);
        boolean active = true;
        GenericFileHandler genericFileHandler = new GenericFileHandler();


        while (active) {
            int op;
            scanner.nextLine();
            userMenuList();

            op = intInput("\n\t\tElija una opcion");

            switch (op) {
                case 1:
                    System.out.println("NUEVA RESERVA");
                    Flight flight = cicloReserva(user);
                    flightList.add(flight);
                    genericFileHandler.saveFile(flightList); // guarda cambios  // todo NO LOS ESTA GUARDANDO pq se carga en el estado inicial cuando se inicia el programa

                    System.out.println("La reserva se ha realizado con exito");
                    System.out.println(flight);
                    scanner.nextLine();
                    break;
                case 2:
                    System.out.println("\t\tVER VUELOS");
                    System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "\nSe mostrara el historial de vuelos del usuario" + ConsoleColors.RESET);
                    mostrarHistorialVuelos(flightList, user);
                    System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "\nVuelos activos del usuario" + ConsoleColors.RESET);
                    mostrarVuelosActivos(flightList, user);
                    break;
                case 3:
                    System.out.println("CANCELAR VUELO");
                    cancelarVuelo(flightList, user);


                    scanner.nextLine();
                    break;
                case 4:
                    System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "PERFIL DE USUARIO" + ConsoleColors.RESET);
                    System.out.println(user);

                    ///todo MODIFICAR mail/password // guardar cambios en USERLIST
                    break;
                case 5:
                    if (borrarUsuario(user)) {
                        scanner.nextLine();
                        active = false;
                    }
                    break;
                case 6:
                    System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "MODIFICAR DATOS USUARIO" + ConsoleColors.RESET);
                    modificarDatosUsuario(userList,user);
                    // TODO: 10/6/2022 guardar la lista

                case 7:  // LOG OUT
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "\tGracias por usar AeroTaxi\n" + ConsoleColors.RESET);
                    active = false;
                    break;
                default:
                    System.out.println("VOLVIENDO AL MENU\n");
                    break;
            }
        }
    }

    /**
     * Menu de administrador
     */
    public void adminMenu(User user) {

        Scanner scanner = new Scanner(System.in);
        boolean active = true;
        GenericFileHandler genericFileHandler = new GenericFileHandler();

        while (active) {
            int op = 0;
            scanner.nextLine();
            adminMenuList();
            User aux;

            op = intInput("\n\t\tElija una opcion");


            switch (op) {              /// OPCION AGREGAR AVION y opcion AGREGAR ADMIN // opcion BORRAR ADMIN LOGUEADO
                case 1:
                    System.out.println("NUEVA RESERVA");
                    aux = checkAndGetUser(emailInput()); /// TODO ACA HAY ALGO RARO REVISAR JUANMA
                    Flight flight = cicloReserva(aux);
                    flightList.add(flight);
                    genericFileHandler.saveFile(flightList); // guarda cambios

                    System.out.println("La reserva a nombre del usuario " + aux.getName() + "  " + aux.getSurname() + " se ha realizado con exito");
                    System.out.println(flight); // todo VER SI SE PUEDE HACER LOS PRINTS USANDO UNA INTERFACE o UN OVERRIDE DE "PLANES"

                    scanner.nextLine();
                    break;
                case 2:
                    System.out.println("VER VUELOS POR FECHA"); // TODO DEBUGGEAR
                    mostrarVuelosPorFecha(flightList, validaFecha());

                    scanner.nextLine();
                    break;
                case 3:
                    System.out.println("CANCELAR VUELO");   // TODO DEBUGGEAR
                    aux = checkAndGetUser(emailInput());
                    cancelarVuelo(flightList, aux);

                    genericFileHandler.saveFile(flightList); // guarda cambios

                    scanner.nextLine();
                    break;
                case 4:
                    System.out.println("MUESTRA USUARIOS");
                    mustraUsuarios();

                    scanner.nextLine();
                    break;
                case 5:
                    System.out.println("MUESTRA VUELOS POR USUARIO");
                    aux = checkAndGetUser(emailInput());
                    if (aux != null) {
                        System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "\nSe mostrara el historial de vuelos del usuario" + ConsoleColors.RESET);
                        mostrarHistorialVuelos(flightList, aux);
                        System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "\nVuelos activos del usuario" + ConsoleColors.RESET);
                        mostrarVuelosActivos(flightList, aux);
                    } else {
                        System.out.println(ConsoleColors.RED_BRIGHT + "El usuario no se encuentra en la base de datos" + ConsoleColors.RESET);
                    }
                    scanner.nextLine();
                    break;
                case 6:
                    System.out.println("BUSCA UN USUARIO");
                    aux = checkAndGetUser(emailInput());
                    if (aux != null) {
                        muestraUsuario(aux);
                    } else {
                        System.out.println(ConsoleColors.RED_BRIGHT + "El usuario no se encuentra en la base de datos" + ConsoleColors.RESET);
                    }
                    scanner.nextLine();
                    break;
                case 7:
                    System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "VER FLOTA DE AVIONES" + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT);
                    for (Planes plane : planeList) {
                        System.out.println(plane.toPrint() + "\n");
                    }
                    System.out.println(ConsoleColors.RESET);
                    scanner.nextLine();
                    break;
                case 8:
                    System.out.println("VUELOS DE LA UNIDAD");

                    mostrarVuelosProgramadosXavion(planeList);
                    scanner.nextLine();
                    break;
                case 9:   /// LOG OUT
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "\tGracias por usar AeroTaxi\n" + ConsoleColors.RESET);
                    active = false;

                    break;
                default:
                    System.out.println("VOLVIENDO AL MENU\n");
                    break;
            }
        }
    }

    /**
     * listado del menu de usuario
     */
    public void userMenuList() {
        System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "\t\t\tAERO TAXI\n" + ConsoleColors.RESET);
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
    public void adminMenuList() {
        System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "\t\t\tAERO TAXI\n" + ConsoleColors.RESET);
        System.out.println("\t\t1.\tNueva Reserva");
        System.out.println("\t\t2.\tVer vuelos por fecha");
        System.out.println("\t\t3.\tCancelar vuelo");
        System.out.println("\t\t4.\tMuestra Usuarios");
        System.out.println("\t\t5.\tMuestra vuelos por usuario");
        System.out.println("\t\t6.\tBusca usuario");
        System.out.println("\t\t7.\tVer Flota");
        System.out.println("\t\t8.\tVer vuelos de una unidad");
        System.out.println("\t\t9.\tLog out");
    }

    /**
     * toma por parametro un mail para chequear si
     * está en la lista de usuarios
     *
     * @param email String - mail a chequear
     * @return Usuario o null si no esta
     */
    public User checkAndGetUser(String email) {
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
     *
     * @param email String - mail a chequear
     * @return boolean - true si es encontrado
     */
    public boolean checkUser(String email) {
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
     *
     * @param pass String - password
     * @return boolean - true si es correcto
     */
    public boolean checkPassword(User user, String pass) {
        boolean checks = false;

        // for (User user : userList) {
        if (pass.equals(user.getPassword())) {
            checks = true;
        }
        //    }

        return checks;
    }

    /**
     * Ciclo de logIn de la aplicacion.
     * toma datos de mail y contraseña
     * Si el usaurio no existe lo manda a su creacion
     * Si existe, verifica que las entradas sean correctas para continuar
     *
     * @return User - usuario ya registrado o usuario recien creado
     */
    public User logIn() {
        Scanner scanner = new Scanner(System.in);

        String mail = emailInput();
        String pass;

        User user = checkAndGetUser(mail);

        if (user != null) {
            boolean validPass = false;
            System.out.println("Ingrese su contraseña");
            pass = scanner.nextLine();

            if (checkPassword(user, pass)) {
                System.out.println("Logueado con exito");
            } else {
                int i = 0;
                while (!validPass && i < 3) {
                    System.out.println("Contraseña incorrecta, ingresela nuevamente o inicie la recuperacion");
                    pass = scanner.nextLine();
                    i++;

                    if (pass.equals(user.getPassword())) {
                        validPass = true;
                    }
                }
                if (!validPass) {
                    System.out.println("Intente nuevamente en otra ocasion o inicie la recuperacion de contraseña");
                    System.out.println("Se volvera a la pantalla de login");
                    return null;
                }
            }
        } else {
            user = createsUser(mail);
            userList.add(user);
            GenericFileHandler genericFileHandler = new GenericFileHandler();
            genericFileHandler.saveFile(userList);

            System.out.println("Usuario creado con exito");
        }

        return user;
    }

    /**
     * Crea y devuelve usuario
     *
     * @param mail
     * @return User - usuario creado en este metodo
     */
    public User createsUser(String mail) {   /// Hay que captar todos los errores que puedan saltar en validacion

        String name = validateNames("Ingrese su nombre");
        String surname = validateNames("Ingrese su apellido");
        String dni = validateDNI();
        String age = validateAge();
        String password = validatePassword();

        User user = new User(name, surname, dni, age, mail, password);
        userList.add(user);

        return user;
    }

    /**
     * Crea y retorna ADMIN
     *
     * @return nuevo ADMIN
     */
    public User createsAdmin() { /// PARA AGREGAR A MENU DE ADMIN
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
     *
     * @return String - email del user/admin
     * @throws PatternSyntaxException
     */
    public String emailInput() {
        String regex = "^([a-z\\d\\._-]{1,30})@([a-z\\d_-]{2,15})\\.([a-z]{2,8})(\\.[a-z]{2,8})?$";

        Scanner scanner = new Scanner(System.in);
        boolean valid = false;
        String email = null;
        String emailVerification = null;

        try {

            while (!valid) {
                System.out.println("\nIngrese el email");
                email = scanner.nextLine();
                if (checkUser(email)) {
                    return email;

                } else if (!email.matches(regex)) {

                    System.out.println("El formato del email es invalido o no existe usuario con ese mail, intentelo nuevamente");
                    System.out.println("sino, crearemos su usuario a continuacion");

                } else {
                    int i = 0;
                    do {
                        System.out.println("Ingreselo nuevamente para comprobarlo");
                        emailVerification = scanner.nextLine();
                        i++;
                    } while (!email.equals(emailVerification) && i < 3);

                    if (email.equals(emailVerification)) {
                        valid = true;
                    }
                }
            }
        } catch (PatternSyntaxException pse) {
            pse.printStackTrace();
        }

        return email;
    }

    /**
     * Toma dni por pantalla, verifica su validez
     * regex valida  "primer num 1 al 9" "siguientes 6/7 numeros del 0 al 9"
     *
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
     *
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
     *
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
     *
     * @return String - edad ingresada y validada
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
     *
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
                } while (!pass.equals(passVerification) && i < 3);

                if (pass.equals(passVerification)) {
                    valid = true;
                }
            }
        }
        return pass;
    }

    /**
     * Valida la carga de un int. Si el input es invalido, se captura la excepcion y se continua pidiendo hasta tener exito
     *
     * @param msg mensaje a desplegar cuando se pide un int por pantalla al usuario
     * @return int - int cargado por pantalla
     */
    public int intInput(String msg) {

        while (true) {
            Scanner scanner = new Scanner(System.in);
            int rta;

            try {
                System.out.println(msg);
                rta = scanner.nextInt();
                return rta;
            } catch (InputMismatchException ime) {
                System.out.println("debe ingresar un valor entero");
            } //  no se si tiene que ir un finally con un scanner.close()
        }

    }

    /**
     * Muestra los dias que determinado vuelo pasado por parametro
     * tiene vuelos registrados
     */
    public <T extends Planes> void mostrarVuelosDelAvion(T plane) {

        boolean flag =false;
        for (var horarios : plane.getDias()) {
            //LocalDate LocalDate = java.time.LocalDate.parse(horarios, DateTimeFormatter.ofPattern("dd/MM/yyyy")); // prueba en STRING

            if (horarios.isAfter(LocalDate.now())) {
                System.out.println(horarios);
                flag=true;
            }
            if(!flag){
                System.out.println(ConsoleColors.BLUE_BOLD + "No tiene vuelos programados" + ConsoleColors.RESET);
            }

        }
    }

    /**
     * Revisa si el avion tiene un vuelo registrado para esa fecha
     * para validar que no se pueda generar dos vuelos diarios para un mismo avion
     *
     * @return boolean
     */
    public <T extends Planes> boolean tieneVuelos(T vuelo, LocalDate day) {

        for (var horarios : vuelo.getDias()) {

            if (horarios.isEqual(day)) {

                return true;
            }
        }
        return false;
    }

    /**
     * Funcion generica, muestra los aviones disponibles para la fecha solicitada y
     * de acuerdo a las caracteristicas mostradas el usuario puede elegir un avion
     * filtra por avion disponible y por la capacidad del avion
     *
     * @return Generico que extiende a la clase padre Plane
     */
    public <T extends Planes> T mostrarAvionesDisponibles(LocalDate dias, List<T> vuelos, Integer cantidadPasajeros) {   ///Do while

        Scanner scanner = new Scanner(System.in);
        T aRetornar = null;

        do {
            int i = 0;
            for (var aVerificar : vuelos) {
                if (!tieneVuelos(aVerificar, dias) && aVerificar.getMaxCapacity() >= cantidadPasajeros) {   // TODO: 1/6/2022 filtrar tambien por la capacidad del vuelo
                    System.out.println(i + 1 + ". " + aVerificar);
                    i = i + 1;
                } else {

                    i = i + 1;
                }

            }

            i = scanner.nextInt();

            aRetornar = vuelos.get(i - 1);

        } while (tieneVuelos(aRetornar, dias));

        return aRetornar;
    }

    /**
     * Define la coneccion a realizar por el usuario, y de ahi setea
     * la distancia y el origen-destino del usuario
     *
     * @return Enum Connection
     */
    public static Connections definirConecciones(String origen, String destino) {

        Connections connections = null;

        if (origen.equals(String.valueOf(City.BUENOS_AIRES))) {

            if (destino.equals(String.valueOf(City.CORDOBA))) {

                connections = Connections.BsAs_CORDOBA;

            } else if (destino.equals(String.valueOf(City.SANTIAGO_DE_CHILE))) {
                connections = Connections.BsAs_SANTIAGO;

            } else {
                connections = Connections.BsAs_MONTEVIDEO;
            }

        } else if (origen.equals(String.valueOf(City.SANTIAGO_DE_CHILE))) {

            if (destino.equals(String.valueOf(City.CORDOBA))) {

                connections = Connections.SANTIAGO_CORDOBA;

            } else if (destino.equals(String.valueOf(City.MONTEVIDEO))) {

                connections = Connections.SANTIAGO_MONTEVIDEO;
            } else {

                connections = Connections.SANTIAGO_BsAs;
            }
        } else if (origen.equals(String.valueOf(City.CORDOBA))) {

            if (destino.equals(String.valueOf(City.SANTIAGO_DE_CHILE))) {

                connections = Connections.CORDOBA_SANTIAGO;

            } else if (destino.equals(String.valueOf(City.MONTEVIDEO))) {

                connections = Connections.CORDOBA_MONTEVIDEO;
            } else {

                connections = Connections.CORDOBA_BsAs;
            }

        } else {

            if (destino.equals(String.valueOf(City.SANTIAGO_DE_CHILE))) {

                connections = Connections.MONTEVIDEO_SANTIAGO;

            } else if (destino.equals(String.valueOf(City.CORDOBA))) {

                connections = Connections.MONTEVIDEO_CORDOBA;
            } else {

                connections = Connections.MONTEVIDEO_BsAs;
            }
        }
        return connections;
    }

    /**
     * Valida los datos ingresados por el usuario para generar un
     * LocalDateTime para el vuelo
     * Gestiona un DateTimeException en caso de que el usuario ingrese mal la fecha
     *
     * @Return LocalDateTime
     */
    public LocalDateTime validaFecha() {

        Scanner scanner = new Scanner(System.in);
        LocalDateTime dateTime = null;
        int flag = 0;

        do {

            try {
                System.out.println("Idique la fecha en la que desea reservar vuelo"); // se toma fecha
                int day = intInput("Indique el dia: ");
                while (day < 1 || day > 31) {
                    System.out.print("\nIndico una fecha incorrecta");
                    System.out.print("\nIndique el dia: ");
                    day = scanner.nextInt();
                }
                int month = intInput("Indique el mes: ");
                while (month < 1 || month > 12) {
                    System.out.print("Indico un mes incorrecto");
                    System.out.print("Indique el mes: ");
                    month = scanner.nextInt();
                }
                int year = intInput("Indique el año: ");
                while (year < 2022) {
                    System.out.print("Indico un año incorrecto");
                    System.out.print("Indique el año: ");
                    year = scanner.nextInt();
                }
                int hour = intInput("Indique la hora con formato 24hs: ");
                while (hour < 0 || hour > 24) {
                    System.out.print("Indico una hora incorrecta");
                    System.out.print("Indique la hora: ");
                    hour = scanner.nextInt();
                }
                int minute = intInput("Indique los minutos: ");
                while (minute < 0 || minute > 60) {
                    System.out.print("Indico un minuto incorrecto");
                    System.out.print("Indique los minutos: ");
                    minute = scanner.nextInt();
                }
                dateTime = LocalDateTime.of(year, month, day, hour, minute);

                flag = 1;

            } catch (DateTimeException dte) {
                System.out.println("La fecha ingresada es incorrecta");

            }

        } while (flag == 0);


        return dateTime;
    }


    /**
     * Ciclo de reserva completo, pide datos al usuario y gestiona los mismos
     *
     * @return Flight
     */
    public Flight cicloReserva(User usuario) { // TODO: 1/6/2022 agregar formato y validaciones para que retorne un vuelo correctamente

        Connections coneccion;

        String origin = null;
        String destination = null;
        Scanner scanner = new Scanner(System.in);
        LocalDate date = null;
        LocalDateTime time = null;
        int flag = 0;
        int selector;

        time = validaFecha();
        date = LocalDate.of(time.getYear(), time.getMonth(), time.getDayOfMonth());

        do {

            ////******************** EN LA SELECCION DE ORIGEN/DESTINO HAY QUE VERIFICAR QUE SE PUEDA ESE RECORRIDO*****************************/////

            do {
                System.out.println("Seleccione el ORIGEN del vuelo"); // desplega opciones y elgie con numero o con otra cosa, no por teclado
                System.out.println("1.Buenos Aires\t2.Cordoba\t3.Montevideo\t4.Santiago de Chile");
                selector = intInput("\nIngrese el numero correspondiente a la ciudad: ");
                switch (selector) {
                    case 1:
                        origin = String.valueOf(City.BUENOS_AIRES);
                        System.out.println("A seleccionado " + origin);
                        break;
                    case 2:
                        origin = String.valueOf(City.CORDOBA);
                        System.out.println("A seleccionado " + origin);
                        break;
                    case 3:
                        origin = String.valueOf(City.MONTEVIDEO);
                        System.out.println("A seleccionado " + origin);
                        break;
                    case 4:
                        origin = String.valueOf(City.SANTIAGO_DE_CHILE);
                        System.out.println("A seleccionado " + origin);
                        break;
                    default:
                        System.out.println("Seleccione un origen valido");
                        break;
                }
            } while (origin == null);


            do {
                System.out.println("Seoleccione el DESTINO del vuelo"); // idem
                System.out.println("1.Cordoba\t2.Santiago de Chile\t3.Montevideo\t4.Buenos Aires");
                selector = intInput("\nIngrese el numero correspondiente a la ciudad: ");
                switch (selector) {
                    case 1 -> {
                        destination = String.valueOf(City.CORDOBA);
                        System.out.println("A seleccionado " + destination);
                    }
                    case 2 -> {
                        destination = String.valueOf(City.SANTIAGO_DE_CHILE);
                        System.out.println("A seleccionado " + destination);
                    }
                    case 3 -> {
                        destination = String.valueOf(City.MONTEVIDEO);
                        System.out.println("A seleccionado " + destination);
                    }
                    case 4 -> {
                        destination = String.valueOf(City.BUENOS_AIRES);
                        System.out.println("A seleccionado " + origin);
                    }
                    default -> System.out.println("Seleccione un destino valido");
                }
            } while (destination == null);

            if (origin.equals(destination)) {
                System.out.println(ConsoleColors.RED_BOLD + "No se puede elegir la misma ciudad de origen que de destino\n se volvera a generar el cuestionario" + ConsoleColors.RESET);
            }

        } while (origin.equals(destination));

        coneccion = definirConecciones(origin, destination);

        System.out.println("Ingrese la cantidad de pasajeros: ");
        int passengers = scanner.nextInt();

        Planes avion = mostrarAvionesDisponibles(date, planeList, passengers); ///cambiar vuelos

        avion.getDias().add(date);

        return new Flight(usuario, avion, time, coneccion, passengers);
    }

    /**
     * Funcion para cancelar vuelos, verifica del listado
     * los vuelos posibles de cancelar para el usuario, despues se le pide
     * que ingrese el numero con el cual se identifico un vuelo.
     * Se valida que el vuelo sea correcto, del ingreso de los datos
     * y recien ahi se borra del listado
     */
    public void cancelarVuelo(List<Flight> list, User usuario) {

        GenericFileHandler genericFileHandler = new GenericFileHandler();

        int flag = 0;
        do {
            int i = 0;
            boolean noFlights = true;
            System.out.println(ConsoleColors.YELLOW_BOLD + "Se mostraran sus vuelos disponibles para cancelar: " + ConsoleColors.RESET);
            for (var vuelos : list) {
                if (vuelos.getUser().equals(usuario) && vuelos.getDate().isAfter(LocalDateTime.now().plusDays(1))) {
                    System.out.println(ConsoleColors.YELLOW_BOLD + i + "." + ConsoleColors.RESET + "\n" + vuelos);
                    noFlights = false;
                }
                i = i + 1;
            }

            if (noFlights) {
                return;
            }

            i = intInput("Ingrese el numero de vuelo");

            while (i > flightList.size()) {
                i = intInput("Ingrese el numero de vuelo");
            }

            Flight vuelos = list.get(i);

            if (vuelos.getUser().equals(usuario) && vuelos.getDate().isAfter(LocalDateTime.now().plusDays(1))) {
                list.remove(i);
                flag = 1;
                genericFileHandler.saveFile(flightList); // guarda cambios
                System.out.println(ConsoleColors.BLUE_BOLD + "Su vuelo fue cancelado con exito" + ConsoleColors.RESET);
            } else {
                System.out.println("Ingreso incorrectamente el vuelo, se desplegara nuevamente el menu.");
            }

        } while (flag == 0);
    }

    /**
     * Calcula el total gastado por un usuario dado, historico y los que realizara
     *
     * @return Double
     */
    public double calcularGastosTotales(User usuario, List<Flight> vuelos) {
        double sumaTot = 0;

        for (var aSumar : vuelos) {
            if (aSumar.getUser().equals(usuario)) {
                sumaTot = sumaTot + aSumar.getTotalFare();
            }
        }
        return sumaTot;
    }

    /**
     * Muestra los vuelos de una determinada fecha pasada por parametro
     */
    public void mostrarVuelosPorFecha(List<Flight> vuelos, LocalDateTime fecha) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Los vuelos para la fecha, " + fecha.format(formatter) + " son: ");

        for (var aMostrar : vuelos) {
            LocalDateTime dateTime = aMostrar.getDate();
            if (dateTime.getDayOfMonth() == fecha.getDayOfMonth() && dateTime.getMonth() == fecha.getMonth() && dateTime.getYear() == fecha.getYear()) {
                System.out.println(aMostrar);
            }
        }
    }

    /**
     * Muestra los vuelos de un usuario pero los ya realizados
     */
    public void mostrarHistorialVuelos(List<Flight> flightList, User user) {
        System.out.println("--");
        for (var aMostrar : flightList) {
            if (aMostrar.getUser().equals(user) && aMostrar.getDate().isBefore(LocalDateTime.now())) {
                System.out.println(aMostrar);
            }
        }
    }

    /**
     * Muestra los vuelos de un usuario pero los que estan pendientes
     */
    public void mostrarVuelosActivos(List<Flight> flightList, User user) {
        System.out.println("--");
        for (var aMostrar : flightList) {
            if (aMostrar.getUser().equals(user) && aMostrar.getDate().isAfter(LocalDateTime.now())) {
                System.out.println(aMostrar);
            }
        }
    }

    /**
     * Funcion que borra un usuario, se le pide con un
     * regex que escriba "SI" o "NO" expecificamente para evitar borrados
     * erroneos
     */
    public boolean borrarUsuario(User user) {
//        String si = "^(si|SI)$";
//        String no = "^(no|NO)$";
//        Scanner scanner = new Scanner(System.in);
        GenericFileHandler genericFileHandler = new GenericFileHandler();
        boolean rta;
//        int i = 0;

        System.out.println(ConsoleColors.RED_BOLD + "Esta seguro que desea borrar su usuario? si tiene reservas activas no sera capaz de cancelarlas por la app" + ConsoleColors.RESET);

        rta = confirmacionSIoNO();
//        while (!rta.matches(si) && !rta.matches(no)) {
//            if (i > 3) {
//                System.out.println("...tampoco es tan jodido");
//            }
//            System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Responda \"SI\" o \"NO\"" + ConsoleColors.RESET);
//
//            rta = scanner.nextLine();
//            i++;
//        }

        if (rta) {
            userList.remove(user);
            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "Usuario borrado con exito\n" + ConsoleColors.RESET);
            genericFileHandler.saveFile(userList); // guarda cambios
            return true;
        } else {
            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "Se cancelo la operacion\n" + ConsoleColors.RESET);
            return false;
        }

    }

    /**
     * Define de la totaliad de vuelos, la mejor categoria usada
     *
     * @return String
     */
    public String muestraMejorCategoriaUsado(List<Flight> flightList, User user) {
        PlaneType mayor = PlaneType.BRONZE;
        boolean empty = true;

        for (var vuelo : flightList) {
            if (vuelo.getUser().equals(user)) {
                if (vuelo.getPlaneType() instanceof GoldPlane) {          // TODO: 7/6/2022  RECIEN ARREGLADO
                    return PlaneType.GOLD.toString();
                } else if (vuelo.getPlaneType() instanceof SilverPlane) {
                    mayor = PlaneType.SILVER;
                }
                empty = false;
            }
        }
        if (empty) {
            return "No ha realizado ningun viaje aun";
        }

        return mayor.toString();
    }

    /**
     * Muestra lista de usuarios
     */
    public void mustraUsuarios() {

        for (var user : userList) {
            muestraUsuario(user);
        }

    }

    /**
     * muestra un usuario pasado por parametro
     *
     * @param user
     */
    public void muestraUsuario(User user) {
        System.out.println(user);
        //DecimalFormat df = new DecimalFormat("###,###,###");

        System.out.println("Gastos totales realizados:\t\t\t" + ConsoleColors.YELLOW_BOLD_BRIGHT + " $ " + String.format("%,.2f", calcularGastosTotales(user, flightList)) + ConsoleColors.RESET);
        System.out.println("Mejor categoria de avion utilizado:\t   " + ConsoleColors.YELLOW_BOLD_BRIGHT + muestraMejorCategoriaUsado(flightList, user) + ConsoleColors.RESET);
    }


    /**
     * Funcion que muestra los dias en los que el avion tiene vuelos programados
     * o historico
     *
     * @param planeList
     */

    public void mostrarVuelosProgramadosXavion(List<Planes> planeList) {
        System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Se mostrara la flota completa de aviones, con su indice:" + ConsoleColors.RESET);
        int i = 0;

        for (var planes : planeList) {
            if (planes != null) {
                System.out.println(i + ". " + planes.toPrint());
            }
            i++;
        }
        Planes plane;

        do {

            i = intInput(ConsoleColors.YELLOW_BOLD + "Ingrese el numero de vuelo que quiere averiguar" + ConsoleColors.RESET);

            plane = planeList.get(i);

            if (plane == null) {
                System.out.println(ConsoleColors.RED + "Se ingreso una opcion incorrecta" + ConsoleColors.RESET);
            }

        } while (plane == null);


        System.out.println(ConsoleColors.YELLOW_BOLD + "Para ver los vuelos programados ingrese: \"1\" \nPara ver el historico ingrese: \"2\" \nPara salir: 0 " + ConsoleColors.RESET);
        i = intInput(ConsoleColors.BLUE_BOLD + "Ingrese una opcion: " + ConsoleColors.RESET);

        while (i < 0 || i > 2) {
            System.out.println(ConsoleColors.RED + "Ingreso una opcion incorrecta" + ConsoleColors.RESET);
            i = intInput(ConsoleColors.BLUE_BOLD + "Ingrese una opcion: " + ConsoleColors.RESET);

        }

        if (i == 1) {
            mostrarVuelosDelAvion(plane);
        } else if (i == 2) {
            mostrarHistoricoDelAvion(plane);
        } else {
            System.out.println("Se termino el proceso");
        }

    }


    public <T extends Planes> void mostrarHistoricoDelAvion(T plane) {

        boolean flag=false;
        for (var horarios : plane.getDias()) {
            //LocalDate LocalDate = java.time.LocalDate.parse(horarios, DateTimeFormatter.ofPattern("dd/MM/yyyy")); // prueba en STRING

            if (horarios.isBefore(LocalDate.now()) || horarios.isEqual(LocalDate.now())) {
                System.out.println(horarios);
                flag=true;
            }
            if(!flag){
                System.out.println(ConsoleColors.BLUE_BOLD + "No tiene vuelos historicos" + ConsoleColors.RESET);
            }
        }
    }


    public void modificarDatosUsuario(List<User> userList, User user) { //todo No se si deberia guardar aca o no
        Scanner scanner = new Scanner(System.in);
        int selector;

        if (userList != null && user != null) {

            User aux = user;

            System.out.println(ConsoleColors.WHITE_BOLD + "1.Nombre" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.WHITE_BOLD + "2.Apellido" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.WHITE_BOLD + "3.Dni" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.WHITE_BOLD + "4.age" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.WHITE_BOLD + "4.email" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.WHITE_BOLD + "5.contraseña" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.WHITE_BOLD + "0.Salir" + ConsoleColors.RESET);

            selector = intInput("Ingrese la opcion");

            while (selector < 0 || selector > 5) {
                System.out.println(ConsoleColors.RED + "Opcion incorrecta, intente nuevamente" + ConsoleColors.RESET);
                selector = intInput("Ingrese la opcion");

            }

            switch (selector) {
                case 1:
                    aux.setName(validateNames("Ingrese el nombre"));
                    break;
                case 2:
                    aux.setSurname(validateNames("Ingrese el apellido"));
                    break;
                case 3:
                    aux.setDni(validateDNI());
                    break;
                case 4:
                    aux.setAge(validateAge());
                    break;
                case 5:
                    aux.setPassword(validatePassword());
                    break;
                case 0:
                    break;

            }

            boolean conf = confirmacionSIoNO();

            if (conf) {
                userList.remove(user);
                userList.add(aux);
            }else {
                System.out.println("Se cancelo la operacion");
            }

        }

    }

    public boolean confirmacionSIoNO() {
        boolean val = false;
        Scanner scanner = new Scanner(System.in);
        String si = "^(si|SI)$";
        String no = "^(no|NO)$";
        String rta = "null";
        int i = 0;

        System.out.println(ConsoleColors.RED_BOLD + "Responda \"SI\" o \"NO\"" + ConsoleColors.RESET);

        while (!rta.matches(si) && !rta.matches(no)) {
            if (i > 3) {
                System.out.println("...tampoco es tan jodido >:(");
            }
            System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Responda \"SI\" o \"NO\"" + ConsoleColors.RESET);

            rta = scanner.nextLine();
            i++;
        }

        if (rta.matches(si)) {
            val = true;
        }

        return val;
    }


}
