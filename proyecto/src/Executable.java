import FileManager.ManageFlights;
import FileManager.ManageUsers;
import PlanePackage.*;
import UserPackage.Admin;
import UserPackage.User;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
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

        while (!loginError) {
            System.out.println(yellowBoldText("\n\t\t\tLOG IN"));
            System.out.println(yellowBoldText("\tBienvenido a Aerotaxi"));


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
     * Menu de opciones de usuario
     */
    public void userMenu(User user) {
        Scanner scanner = new Scanner(System.in);
        boolean active = true;

        while (active) {
            int op;
            scanner.nextLine();
            userMenuList();

            op = intInput("\n\t\tElija una opcion");

            switch (op) {
                case 1:
                    System.out.println(yellowBoldText("NUEVA RESERVA"));
                    ManageFlights manageFlights = new ManageFlights();
                    Flight flight = cicloReserva(user);
                    if(flight!=null) {
                        flightList.add(flight);
                        manageFlights.saveFile(flightList); // guarda cambios  // todo NO LOS ESTA GUARDANDO pq se carga en el estado inicial cuando se inicia el programa

                        blueBoldText("La reserva se ha realizado con exito");
                        System.out.println(flight);
                    }else {
                        System.out.println(yellowBoldText("se volvera al menu principal"));
                    }
                    break;
                case 2:
                    System.out.println(yellowBoldText("\t\tVER VUELOS"));
                    System.out.println(yellowBoldText("\nSe mostrara el historial de vuelos del usuario"));
                    mostrarHistorialVuelos(flightList, user);
                    System.out.println(yellowBoldText("\nVuelos activos del usuario"));
                    mostrarVuelosActivos(flightList, user);
                    break;
                case 3:
                    System.out.println( yellowBoldText("CANCELAR VUELO"));
                    cancelarVuelo(flightList, user);
                    break;
                case 4:
                    System.out.println(yellowBoldText("PERFIL DE USUARIO"));
                    System.out.println(user);
                    ///todo MODIFICAR mail/password // guardar cambios en USERLIST y FLIGHTLIST
                    break;
                case 5:
                    if (borrarUsuario(user)) {
                        scanner.nextLine();
                        active = false;
                    }
                    break;
                case 6:
                    System.out.println(yellowBoldText("MODIFICAR DATOS USUARIO"));
                    modificarDatosUsuario(userList,user);  // TODO: 10/6/2022 guardar la lista
                    break;
                case 7:  // LOG OUT
                    System.out.println(yellowBoldText("\tGracias por usar AeroTaxi\n"));
                    active = false;
                    break;
                default:
                    System.out.println(blueBoldText("VOLVIENDO AL MENU\n"));
                    break;
            }
        }
    }

    /**
     * Menu de opciones de administrador
     */
    public void adminMenu(User user) {

        Scanner scanner = new Scanner(System.in);
        ManageFlights manageFlights = new ManageFlights();
        boolean active = true;
        String mail;

        while (active) {
            int op = 0;
            scanner.nextLine();
            adminMenuList();
            User aux;

            op = intInput("\n\t\tElija una opcion");


            switch (op) {
                case 1:
                    System.out.println(yellowBoldText("NUEVA RESERVA"));
                    System.out.println("Ingrese el email");
                    mail= scanner.nextLine();
                    if (checkUser(mail)){
                        aux = checkAndGetUser(mail);
                        Flight flight = cicloReserva(aux);
                        if (flight != null) {
                            flightList.add(flight);
                            manageFlights.saveFile(flightList);
                            System.out.println(yellowBoldText("La reserva a nombre del usuario " + aux.getName() + " " + aux.getSurname() + " se ha realizado con exito"));
                            System.out.println(flight);
                        }else{
                            System.out.println(yellowBoldText("Se volvera al menu principal"));
                        }
                    }else{
                        System.out.println(redText("No existe el usuario"));
                    }
                    break;
                case 2:
                    System.out.println(yellowBoldText("\t\tVER VUELOS POR FECHA"));
                    mostrarVuelosPorFecha(flightList, validaFecha());
                    break;
                case 3:
                    System.out.println( yellowBoldText("CANCELAR VUELO"));  // TODO DEBUGGEAR
                    System.out.println("Ingrese el email");
                    mail= scanner.nextLine();
                    if (checkUser(mail)){
                        aux = checkAndGetUser(mail);
                        cancelarVuelo(flightList, aux);
                        manageFlights.saveFile(flightList);
                    }else{
                        System.out.println(redText("No existe el usuario"));
                    }
                    break;
                case 4:
                    System.out.println( yellowBoldText("MUESTRA USUARIOS"));
                    mustraUsuarios();
                    break;
                case 5:
                    System.out.println( yellowBoldText("MUESTRA VUELOS POR USUARIO"));
                    aux = checkAndGetUser(emailInput());
                    if (aux != null) {
                        System.out.println( yellowBoldText("\nSe mostrara el historial de vuelos del usuario"));
                        mostrarHistorialVuelos(flightList, aux);
                        System.out.println( yellowBoldText("\nVuelos activos del usuario"));
                        mostrarVuelosActivos(flightList, aux);
                    } else {
                        System.out.println(redBoldText("El usuario no se encuentra en la base de datos"));
                    }
                    break;
                case 6:
                    System.out.println(yellowBoldText("BUSCA UN USUARIO"));
                    System.out.println("Ingrese el email");
                    mail = scanner.nextLine();
                    if (checkUser(mail)){
                        aux = checkAndGetUser(mail);
                        muestraUsuario(aux);
                    }else{
                        System.out.println(redText("No existe el usuario"));
                    }
                    break;
                case 7:
                    System.out.println(yellowBoldText("VER FLOTA DE AVIONES"));
                    System.out.println(ConsoleColors.YELLOW_BRIGHT);
                    for (Planes plane : planeList) {
                        System.out.println(plane.toPrint() + "\n--" );
                    }
                    System.out.println(ConsoleColors.RESET);
                    break;
                case 8:
                    System.out.println(yellowBoldText("VUELOS DE LA UNIDAD"));
                    mostrarVuelosProgramadosXavion(planeList);
                    scanner.nextLine();
                    break;
                case 9:
                    createsAdmin();
                    active = false;
                    break;
                case 0:   /// LOG OUT
                    System.out.println(yellowBoldText("\tGracias por usar AeroTaxi\n"));
                    active = false;

                    break;
                default:
                    System.out.println(blueBoldText("VOLVIENDO AL MENU\n"));
                    break;
            }
        }
    }

    /**
     * listado del menu de usuario
     */
    public void userMenuList() {
        System.out.println(yellowBoldText("\n\t\t\tAERO TAXI\n"));
        System.out.println("\t\t1.\tNueva Reserva");
        System.out.println("\t\t2.\tVer vuelos");
        System.out.println("\t\t3.\tCancelar vuelo");
        System.out.println("\t\t4.\tVer perfil");
        System.out.println("\t\t5.\tBaja de usuario");
        System.out.println("\t\t6.\tModificar datos del usuario");
        System.out.println("\t\t7.\tLog out");
    }

    /**
     * listado del menu de administrador
     */
    public void adminMenuList() {
        System.out.println(yellowBoldText("\n\t\t\tAERO TAXI\n"));
        System.out.println("\t\t1.\tNueva Reserva");
        System.out.println("\t\t2.\tVer vuelos por fecha");
        System.out.println("\t\t3.\tCancelar vuelo");
        System.out.println("\t\t4.\tMuestra Usuarios");
        System.out.println("\t\t5.\tMuestra vuelos por usuario");
        System.out.println("\t\t6.\tBusca usuario");
        System.out.println("\t\t7.\tVer Flota");
        System.out.println("\t\t8.\tVer vuelos de una unidad");
        System.out.println("\t\t9.\tCrear administrador");
        System.out.println("\t\t0.\tLog out");
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
                return true;
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

        if (pass.equals(user.getPassword())) {
            checks = true;
        }

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
                System.out.println(blueBoldText("Logueado con exito"));
            } else {
                int i = 0;
                while (!validPass && i < 3) {
                    System.out.println(redText("Contraseña incorrecta, ingresela nuevamente o inicie la recuperacion"));
                    pass = scanner.nextLine();
                    i++;

                    validPass = checkPassword(user, pass);
                }
                if (validPass) {
                    System.out.println(blueBoldText("Logueado con exito"));
                }else{
                    System.out.println(redText("Intente nuevamente en otra ocasion o inicie la recuperacion de contraseña"));
                    System.out.println(redText("Se volvera a la pantalla de login"));
                    return null;
                }
            }
        } else {
            user = createsUser(mail);
            userList.add(user);
            ManageUsers manageUsers = new ManageUsers();
            manageUsers.saveFile(userList);

            System.out.println("Usuario creado con exito");
        }

        return user;
    }

    /**
     * Crea y devuelve usuario
     * @param mail mail de usuario
     * @return User - usuario creado en este metodo
     */
    public User createsUser(String mail) {

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
     * Crea y guarda un nuevo Admin
     * y lo persiste
     */
    public void createsAdmin() {
        String mail = emailInput();
        String name = validateNames("Ingrese el nombre del nuevo administrador");
        String surname = validateNames("Ingrese el apellido");
        String password = validatePassword();

        Admin admin = new Admin(name, surname, mail, password);
        userList.add(admin);
        ManageUsers manageUsers = new ManageUsers();
        manageUsers.saveFile(userList);
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
                    System.out.println("si no esta registrado, crearemos su usuario con su mail a continuacion");

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
            System.out.println("Ingrese el password a utilizar\nLa contraseña debe contener entre 8 y 15 caracteres,\nal menos una minuscula, una mayuscula y un numero");
            pass = scanner.nextLine();
            if (!pass.matches(regex)) {
                System.out.println("El formato ingresado es incorrecto, intentelo nuevamente");
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
            }
        }

    }

    /**
     * Revisa si el avion tiene un vuelo registrado para esa fecha
     * para validar que no se pueda generar dos vuelos diarios para un mismo avion
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
     * @return Generico que extiende a la clase padre Plane
     */
    public <T extends Planes> T mostrarAvionesDisponibles(LocalDate dias, List<T> vuelos, int cantidadPasajeros) {   ///Do while

        Scanner scanner = new Scanner(System.in);
        T aRetornar = null;
        int redflag=0;
        int flag = 0;
        do {
            int i = 0;
            for (var aVerificar : vuelos) {
                if (!tieneVuelos(aVerificar, dias) && aVerificar.getMaxCapacity() >= cantidadPasajeros) {   // TODO: 1/6/2022 filtrar tambien por la capacidad del vuelo
                    System.out.println(yellowBoldText(i + 1 + ". ") + aVerificar.toPrint());
                    i = i + 1;
                    redflag=1;
                } else {

                    i = i + 1;
                }

            }
            if(redflag==1){
                i = intInput(yellowBoldText("Ingrese el numero de avion que desea reservar "));
            }else {
                System.out.println(redText("No hay aviones disponibles con esos requerimientos"));
                return null;
            }


            aRetornar = vuelos.get(i - 1);
            if (aRetornar.getMaxCapacity() < cantidadPasajeros) {
                System.out.println(redText("Ese avion no corresponde con los datos utilizados"));
                flag = 1;
            }
            System.out.println(aRetornar.getMaxCapacity());
            System.out.println(flag);
        } while ( flag == 1 || tieneVuelos(aRetornar,dias));

        return aRetornar;
    }

//    tieneVuelos(aRetornar, dias) &&

    /**
     * Define la coneccion a realizar por el usuario, y de ahi setea
     * la distancia y el origen-destino del usuario
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
     * @Return LocalDateTime fecha validada
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
                    day = intInput("\nIndique el dia: ");
                }
                int month = intInput("Indique el mes: ");
                while (month < 1 || month > 12) {
                    System.out.print("Indico un mes incorrecto");
                    month = intInput("\nIndique el mes: ");

                }
                int year = intInput("Indique el año: ");
                while (year < 2022) {
                    System.out.print("Indico un año incorrecto");
                    year = intInput("\nIndique el año: ");

                }
                int hour = intInput("Indique la hora con formato 24hs: ");
                while (hour < 0 || hour > 24) {
                    System.out.print("Indico una hora incorrecta");
                    hour = intInput("Indique la hora: ");
                }
                int minute = intInput("Indique los minutos: ");
                while (minute < 0 || minute > 60) {
                    System.out.print("Indico un minuto incorrecto");
                    minute = intInput("Indique los minutos: ");
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
     * @return Flight
     */
    public Flight cicloReserva(User usuario) { // TODO: 1/6/2022 agregar formato y validaciones para que retorne un vuelo correctamente

        Connections coneccion;

        String origin = null;
        String destination = null;
        Scanner scanner = new Scanner(System.in);
        LocalDate date = null;
        LocalDateTime time = null;
        int selector;

        time = validaFecha();
        date = LocalDate.of(time.getYear(), time.getMonth(), time.getDayOfMonth());

        do {

            do {
                System.out.println(yellowBoldText("\nSeleccione el ORIGEN del vuelo"));
                System.out.println("1.Buenos Aires\t2.Cordoba\t3.Montevideo\t4.Santiago de Chile");
                selector = intInput("\nIngrese el numero correspondiente a la ciudad: ");
                switch (selector) {
                    case 1:
                        origin = String.valueOf(City.BUENOS_AIRES);
                        System.out.println(blueBoldText("Ha seleccionado ") + origin);
                        break;
                    case 2:
                        origin = String.valueOf(City.CORDOBA);
                        System.out.println(blueBoldText("Ha seleccionado ") + origin);
                        break;
                    case 3:
                        origin = String.valueOf(City.MONTEVIDEO);
                        System.out.println(blueBoldText("Ha seleccionado ") + origin);
                        break;
                    case 4:
                        origin = String.valueOf(City.SANTIAGO_DE_CHILE);
                        System.out.println(blueBoldText("Ha seleccionado ") + origin);
                        break;
                    default:
                        System.out.println(redText("Seleccione un origen valido"));
                        break;
                }
            } while (origin == null);


            do {
                System.out.println(yellowBoldText("\nSeleccione el DESTINO del vuelo")); // idem
                System.out.println("1.Cordoba\t2.Santiago de Chile\t3.Montevideo\t4.Buenos Aires");
                selector = intInput("\nIngrese el numero correspondiente a la ciudad: ");
                switch (selector) {
                    case 1 -> {
                        destination = String.valueOf(City.CORDOBA);
                        System.out.println(blueBoldText("Ha seleccionado ") + destination);
                    }
                    case 2 -> {
                        destination = String.valueOf(City.SANTIAGO_DE_CHILE);
                        System.out.println(blueBoldText("Ha seleccionado ") + destination);
                    }
                    case 3 -> {
                        destination = String.valueOf(City.MONTEVIDEO);
                        System.out.println(blueBoldText("Ha seleccionado ") + destination);                    }
                    case 4 -> {
                        destination = String.valueOf(City.BUENOS_AIRES);
                        System.out.println(blueBoldText("Ha seleccionado ") + origin);
                    }
                    default -> System.out.println(redText("Seleccione un origen valido"));
                }
            } while (destination == null);

            if (origin.equals(destination)) {
                System.out.println(redBoldText("No se puede elegir la misma ciudad de origen que de destino\n se volvera a generar el cuestionario"));
            }

        } while (origin.equals(destination));

        coneccion = definirConecciones(origin, destination);

        int passengers = intInput("Ingrese la cantidad de pasajeros (max:50): ");
        int opc=0;
        while (passengers > 50 || passengers<0) {

            System.out.println(redText("El numero ingresado no corresponde"));
            passengers = intInput("Ingrese la cantidad de pasajeros (max:50): ");
            opc++;
            if(opc>2){
                System.out.println(redText("se volvera al menu principal"));
                System.out.println(redText("no hay vuelos con esa capacidad"));
                return null;
            }

        }

        Planes avion = mostrarAvionesDisponibles(date, planeList, passengers);
        if(avion==null){
            return null;
        }
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

        ManageFlights manageFlights = new ManageFlights();

        int flag = 0;
        int tries = 0;
        do {
            int i = 0;
            boolean noFlights = true;
            System.out.println(yellowBoldText("Se mostraran sus vuelos disponibles para cancelar: \n--"));
            for (var vuelos : list) {
                if (vuelos.getUser().equals(usuario) && vuelos.getDate().isAfter(LocalDateTime.now().plusDays(1))) {
                    System.out.println("\n" + ConsoleColors.YELLOW_BOLD_BRIGHT + i + "." + ConsoleColors.RESET + "\n" + vuelos);
                    noFlights = false;
                }
                i = i + 1;
            }

            if (noFlights) {
                return;
            }

            i = intInput("\nIngrese el numero de vuelo a cancelar");

            while (i > flightList.size()) {
                i = intInput(redText("No hay registrado un vuelo con ese numero, ingrese el numero de vuelo a cancelar"));
                tries++;
                if (tries>2){
                    System.out.println(redBoldText("Demasiados intentos fallidos, se volera al menu principal"));
                    return;
                }
            }

            Flight vuelos = list.get(i);

            if (vuelos.getUser().equals(usuario) && vuelos.getDate().isAfter(LocalDateTime.now().plusDays(1))) {
                list.remove(i);
                flag = 1;
                manageFlights.saveFile(flightList);
                System.out.println(blueBoldText("Su vuelo fue cancelado con exito"));
            } else {
                System.out.println(redText("Ese numero de vuelo no esta disponible para cancelar, ingrese el numero de vuelo a cancelar"));
                tries++;
                if (tries>2){
                    System.out.println(redBoldText("Demasiados intentos fallidos, se volera al menu principal"));
                }
            }


        } while (flag == 0 && tries<3);
    }

    /**
     * Calcula el total gastado por un usuario dado, historico y los que realizara
     *
     * @return Double
     */
    public double calcularGastosTotales(User usuario, List<Flight> vuelos) {
        double sumaTot = 0;

        for (var aSumar : vuelos) {
            if (aSumar.getUser().getId().equals(usuario.getId())) { // todo: si se hace con equals no lo va encontrar despues de un cambio
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
            if (aMostrar.getUser().getId().equals(user.getId()) && aMostrar.getDate().isBefore(LocalDateTime.now())) { // TODO: 11/6/2022 de esta manera si cambia algun atributo sigue buscando a la misma persona
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
            if (aMostrar.getUser().getId().equals(user.getId()) && aMostrar.getDate().isAfter(LocalDateTime.now())) {   // TODO: mismo arriba
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
        ManageUsers manageUsers = new ManageUsers();
        boolean rta;

        rta = confirmacionSIoNO(redBoldText("Esta seguro que desea borrar su usuario? si tiene reservas activas no sera capaz de cancelarlas por la app"));

        if (rta) {
            userList.remove(user);
            System.out.println(blueBoldText("Usuario borrado con exito\n"));
            manageUsers.saveFile(userList); // guarda cambios
            return true;
        } else {
            System.out.println(blueBoldText("Se cancelo la operacion\n"));
            return false;
        }

    }

    /**
     * Define de la totaliad de vuelos, la mejor categoria usada
     * @return String
     */
    public String muestraMejorCategoriaUsado(List<Flight> flightList, User user) {
        PlaneType mayor = PlaneType.BRONZE;
        boolean empty = true;

        for (var vuelo : flightList) {
            if (vuelo.getUser().getId().equals(user.getId())) {  // todo MISMO que calcularGastosTotales();
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
     * @param user usuario pasado por parametro
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
     * @param planeList Lista de Planes
     */
    public void mostrarVuelosProgramadosXavion(List<Planes> planeList) {
        System.out.println(yellowBoldText("Se mostrara la flota completa de aviones, con su indice:"));
        int i = 0;
        Planes plane = null;

        for (var planes : planeList) {
            if (planes != null) {
                System.out.println(yellowBoldText(i + ". ") + planes.toPrint());
            }
            i++;
        }

        do {

            i = intInput(yellowBoldText("Ingrese el numero de avion que quiere consultar"));

            if(i<planeList.size()) {

                plane = planeList.get(i);

            }else{
                System.out.println(redText("Se ingreso una opcion incorrecta"));
            }

        } while (plane == null);

        System.out.println(yellowBoldText("\"1\". Para ver los vuelos programados \n\"2\". Para ver el historial  \n\"0\". Para salir "));
        i = intInput(blueBoldText("Ingrese una opcion: "));

        while (i < 0 || i > 2) {
            System.out.println(redText("Ingreso una opcion incorrecta" ));
            i = intInput(blueBoldText("Ingrese una opcion: "));

        }

        if (i == 1) {
            mostrarVuelosDelAvion(plane);
        } else if (i == 2) {
            mostrarHistoricoDelAvion(plane);
        } else {
            System.out.println("Se termino el proceso");
        }

    }
    /**
     * Muestra los dias que determinado vuelo pasado por parametro
     * tiene vuelos registrados
     */
    public <T extends Planes> void mostrarVuelosDelAvion(T plane) {

        boolean flag =false;
        for (var flight : flightList) {
            if (flight.getPlaneType().equals(plane) && flight.getDate().isAfter(LocalDateTime.now())){

                    System.out.println(flight);
                    flag=true;
            }

        }
        if(!flag){
            System.out.println(blueBoldText("No tiene vuelos programados"));
        }
    }

    /**
     * Muestra el registro historico de vuelos del avion pasado por parametro
     * @param plane Avion a consultar
     * @param <T> Generico Planes
     */
    public <T extends Planes> void mostrarHistoricoDelAvion(T plane) {

        boolean flag=false;
        for (var flight : flightList) {
            if (flight.getPlaneType().equals(plane) && flight.getDate().isBefore(LocalDateTime.now())) {
                    System.out.println(flight);
                    flag = true;
            }
        }
        if(!flag){
            System.out.println(blueBoldText("No tiene vuelos historicos"));
        }
    }

    /**
     * Desplega menu de opciones para modificar un usuario, valida las entradas y persiste datos
     * @param userList Lista de usuarios
     * @param user  usuario a modificar
     */
    public void modificarDatosUsuario(List<User> userList, User user) { //todo No se si deberia guardar aca o no
        int selector;
        boolean flag = false;
        String change;
        String confirmation = yellowBoldText("Esta seguro que desea realizar el cambio?");

        if (userList != null && user != null) {

            System.out.println("1.Nombre" );
            System.out.println("2.Apellido");
            System.out.println("3.DNI");
            System.out.println("4.Edad");
            System.out.println("5.Contraseña");
            System.out.println("6.Email");
            System.out.println("0.Salir");

            selector = intInput("\nIngrese la opcion");

            while (selector < 0 || selector > 6) {
                System.out.println(redBoldText("Opcion incorrecta, intente nuevamente"));
                selector = intInput("Ingrese la opcion");

            }

            switch (selector) {
                case 1:
                    change = validateNames("Ingrese el nombre");
                    if (confirmacionSIoNO(confirmation)){
                        user.setName(change);
                        System.out.println(blueBoldText("Cambio realizado"));
                        flag=true;
                    }else{
                        System.out.println(blueBoldText("Los cambios no se guardaran"));
                    }
                    break;
                case 2:
                    change = validateNames("Ingrese el apellido");
                    if (confirmacionSIoNO(confirmation)){
                        user.setSurname(change);
                        System.out.println(blueBoldText("Cambio realizado"));
                        flag=true;
                    }else {
                        System.out.println(blueBoldText("Los cambios no se guardaran"));
                    }
                    break;
                case 3:
                    change = validateDNI();
                    if (confirmacionSIoNO(confirmation)){
                        user.setDni(change);
                        System.out.println(blueBoldText("Cambio realizado"));
                        flag=true;
                    }else {
                        System.out.println(blueBoldText("Los cambios no se guardaran"));
                    }
                    break;
                case 4:
                    change = validateAge();
                    if (confirmacionSIoNO(confirmation)){
                        user.setAge(change);
                        System.out.println(blueBoldText("Cambio realizado"));
                        flag=true;
                    }else {
                        System.out.println(blueBoldText("Los cambios no se guardaran"));
                    }
                    break;
                case 5:
                    change = validatePassword();
                    if (confirmacionSIoNO(confirmation)){
                        user.setPassword(change);
                        System.out.println(blueBoldText("Cambio realizado"));
                        flag=true;
                    }else {
                        System.out.println(blueBoldText("Los cambios no se guardaran"));
                    }
                    break;
                case 6:
                    change = emailInput();
                    if (confirmacionSIoNO(confirmation)){
                        user.setEmail(change);
                        System.out.println(blueBoldText("Cambio realizado"));
                        flag=true;
                    }else {
                        System.out.println(blueBoldText("Los cambios no se guardaran"));
                    }
                    break;
                case 0:
                    System.out.println(blueBoldText("Se cancelo la operacion"));
                    System.out.println(blueBoldText("Se volvera al menu"));
                    break;

            }

            if(flag){
                ManageUsers manageUsers = new ManageUsers();
                manageUsers.saveFile(userList);
                modifyFlightUser(user);
            }

        }

    }

    /**
     * Modifica el Usuario, actualizadndolo dentro de las listas de vuelos
     * @param user usuario a modificar
     */
    public void modifyFlightUser(User user){
        ManageFlights manageFlights = new ManageFlights();

        for (var flight: flightList){
            if (flight.getUser().getId().equals(user.getId())){
                flight.setUser(user);
            }
        }
        manageFlights.saveFile(flightList);
    }

    /**
     * Valida por Si o por No una decision
     * @param msg mensaje a mostrar en el pedido de confirmacion
     * @return true = si /  false = no
     */
    public boolean confirmacionSIoNO(String msg) {
        boolean val = false;
        Scanner scanner = new Scanner(System.in);
        String si = "^(si|SI)$";
        String no = "^(no|NO)$";
        String rta = "null";
        int i = 0;

        System.out.println(msg);

        while (!rta.matches(si) && !rta.matches(no)) {
            if (i > 3) {
                System.out.println("...tampoco es tan jodido >:(");
            }
            System.out.println(yellowBoldText("Responda \"SI\" o \"NO\""));

            rta = scanner.nextLine();
            i++;
        }

        if (rta.matches(si)) {
            val = true;
        }

        return val;
    }


    //  COLORES DE CONSOLA Y TEXTO
    /**
     * Pasa un String por parametro, el cual sera formateado con
     * ConsoleColors.RED y restableciendo parametros por defecto al final de la cadena
     * @param msg String mensaje a escribir por pantalla
     * @return mensaje formateado
     */
    public String redText (String msg){
        return ConsoleColors.RED + msg + ConsoleColors.RESET;
    }
    /**
     * Pasa un String por parametro, el cual sera formateado con
     * ConsoleColors.RED_BOLD_BRIGHT y restableciendo parametros por defecto al final de la cadena
     * @param msg String mensaje a escribir por pantalla
     * @return mensaje formateado
     */
    public String redBoldText (String msg){
        return ConsoleColors.RED_BOLD_BRIGHT + msg + ConsoleColors.RESET;
    }
    /**
     * Pasa un String por parametro, el cual sera formateado con
     * ConsoleColors.BLUE_BOLD_BRIGHT y restableciendo parametros por defecto al final de la cadena
     * @param msg String mensaje a escribir por pantalla
     * @return mensaje formateado
     */
    public String blueBoldText (String msg){
        return ConsoleColors.BLUE_BOLD_BRIGHT + msg + ConsoleColors.RESET;
    }
    /**
     * Pasa un String por parametro, el cual sera formateado con
     * ConsoleColors.YELLOW_BOLD_BRIGHT y restableciendo parametros por defecto al final de la cadena
     * @param msg String mensaje a escribir por pantalla
     * @return mensaje formateado
     */
    public String yellowBoldText (String msg){
        return ConsoleColors.YELLOW_BOLD_BRIGHT + msg + ConsoleColors.RESET;
    }


}
