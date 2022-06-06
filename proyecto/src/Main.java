import BranchJuanma.*;
import PlanePackage.*;
import com.google.gson.Gson;
import com.sun.jdi.connect.spi.Connection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    private static final String usersFilePath = "userFile.json";
    private static final String flightsFilePath = "flightsFile.json";
    private static final String planesFilePath = "planesFile.json";

    public static void main(String[] args) throws FileNotFoundException {
        List<Planes> planeList =new ArrayList<>();
        List<Flight> flightList = new ArrayList<>();
        List<User> userList = new ArrayList<>();

        FileHandler fileHandler = new FileHandler();

//        FileHandler <Planes> fileHandlerPlanes = new FileHandler<>();
//        FileHandler <Flight> fileHandlerFlights = new FileHandler<>();
//        FileHandler <User> fileHandlerUsers = new FileHandler<>();

//        planeList = fileHandlerPlanes.readFile(planeList,usersFilePath);
//        flightList = fileHandlerFlights.readFile(flightList,usersFilePath);
//        userList = fileHandlerUsers.readFile(userList,usersFilePath);


        User user1= new User("juan manuel","sanjurjo","12345678","25","juanma@hotmail.com","Juanmanuel12");
        User user2 = new User("mariano","fernandez","24561238","27","mariano@hotmail.com","Mariano12");
        User user3 = new User("julio","verne","45678912","99","verne","Vernejulio12");
        User user4 = new User("matias","perez","34512789","31","matias@hotmail.com","Matias123456");
        User user5 = new User("rodrigo","otigoza","24568974","59","rodrigo@hotmail.com","Rodrigoortigozal12");
        Admin admin1 = new Admin("juan manuel","sanjurjo","admin1","admin1");
        Admin admin2 = new Admin("mariano","fernandez","admin2","admin2");

        BronzePlane bronze1 = new BronzePlane(1500, 150, 10, 100, Planes.TypeOfPropulsion.MOTOR_A_REACCION.typeOfPropulsion);
        BronzePlane bronze2 = new BronzePlane(2500, 200, 5, 100, Planes.TypeOfPropulsion.MOTOR_A_HELICE.typeOfPropulsion);
        BronzePlane bronze3 = new BronzePlane(2000, 150, 15, 100, Planes.TypeOfPropulsion.MOTOR_A_PISTONES.typeOfPropulsion);
        BronzePlane bronze4 = new BronzePlane(3000, 300, 20, 100, Planes.TypeOfPropulsion.MOTOR_A_REACCION.typeOfPropulsion);
        BronzePlane bronze5 = new BronzePlane(1000, 150, 2, 100, Planes.TypeOfPropulsion.MOTOR_A_HELICE.typeOfPropulsion);
        BronzePlane bronze6 = new BronzePlane(2000, 200, 4, 100, Planes.TypeOfPropulsion.MOTOR_A_HELICE.typeOfPropulsion);
        BronzePlane bronze7 = new BronzePlane(2500, 250, 5, 100, Planes.TypeOfPropulsion.MOTOR_A_REACCION.typeOfPropulsion);
        BronzePlane bronze8 = new BronzePlane(2000, 200, 4, 100, Planes.TypeOfPropulsion.MOTOR_A_PISTONES.typeOfPropulsion);

        SilverPlane silver1 = new SilverPlane(1500, 150, 2, 400, Planes.TypeOfPropulsion.MOTOR_A_REACCION.typeOfPropulsion, false);
        SilverPlane silver2 = new SilverPlane(2500, 250, 8, 400, Planes.TypeOfPropulsion.MOTOR_A_PISTONES.typeOfPropulsion, true);
        SilverPlane silver3 = new SilverPlane(2000, 200, 4, 400, Planes.TypeOfPropulsion.MOTOR_A_REACCION.typeOfPropulsion, false);
        SilverPlane silver4 = new SilverPlane(1000, 150, 2, 400, Planes.TypeOfPropulsion.MOTOR_A_HELICE.typeOfPropulsion, true);
        SilverPlane silver5 = new SilverPlane(3000, 300, 10, 400, Planes.TypeOfPropulsion.MOTOR_A_REACCION.typeOfPropulsion, true);
        SilverPlane silver6 = new SilverPlane(2500, 150, 10, 400, Planes.TypeOfPropulsion.MOTOR_A_PISTONES.typeOfPropulsion, true);
        SilverPlane silver7 = new SilverPlane(2000, 200, 4, 400, Planes.TypeOfPropulsion.MOTOR_A_REACCION.typeOfPropulsion, false);
        SilverPlane silver8 = new SilverPlane(1500, 300, 4, 400, Planes.TypeOfPropulsion.MOTOR_A_REACCION.typeOfPropulsion, true);

        GoldPlane gold1 = new GoldPlane(3000, 200, 20, 1000, Planes.TypeOfPropulsion.MOTOR_A_REACCION.typeOfPropulsion, false, true);
        GoldPlane gold2 = new GoldPlane(4000, 200, 30, 1000, Planes.TypeOfPropulsion.MOTOR_A_REACCION.typeOfPropulsion, false, false);
        GoldPlane gold3 = new GoldPlane(5000, 300, 20, 1000, Planes.TypeOfPropulsion.MOTOR_A_REACCION.typeOfPropulsion, false, false);
        GoldPlane gold4 = new GoldPlane(3000, 200, 20, 1000, Planes.TypeOfPropulsion.MOTOR_A_REACCION.typeOfPropulsion, false, true);
        GoldPlane gold5 = new GoldPlane(4000, 300, 20, 1000, Planes.TypeOfPropulsion.MOTOR_A_REACCION.typeOfPropulsion, true, true);
        GoldPlane gold6 = new GoldPlane(5000, 300, 30, 1000, Planes.TypeOfPropulsion.MOTOR_A_REACCION.typeOfPropulsion, true, false);
        GoldPlane gold7 = new GoldPlane(4000, 200, 20, 1000, Planes.TypeOfPropulsion.MOTOR_A_REACCION.typeOfPropulsion, true, false);
        GoldPlane gold8 = new GoldPlane(3000, 150, 30, 1000, Planes.TypeOfPropulsion.MOTOR_A_REACCION.typeOfPropulsion, false, false);
        GoldPlane gold9 = new GoldPlane(6000, 300, 40, 1000, Planes.TypeOfPropulsion.MOTOR_A_REACCION.typeOfPropulsion, true, true);
        GoldPlane gold10 = new GoldPlane(10000, 300, 50, 1000, Planes.TypeOfPropulsion.MOTOR_A_REACCION.typeOfPropulsion, true, true);

        Flight flight1 = new Flight(user1,bronze1,LocalDateTime.now(),Connections.BsAs_CORDOBA,4);
        Flight flight2 = new Flight(user2,silver1,LocalDateTime.now().minusDays(5),Connections.CORDOBA_MONTEVIDEO,2);
        Flight flight3 = new Flight(user3,silver2,LocalDateTime.now().plusDays(10),Connections.CORDOBA_SANTIAGO,3);
        Flight flight4 = new Flight(user4,gold1,LocalDateTime.now().minusDays(20),Connections.MONTEVIDEO_SANTIAGO,3);
        Flight flight5 = new Flight(user5,gold4,LocalDateTime.now().plusDays(1),Connections.BsAs_MONTEVIDEO,1);

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);
        userList.add(admin1);
        userList.add(admin2);

        // con GSON
//        fileHandlerUsers.saveFileGson(userList,usersFilePath);
//        userList = fileHandlerUsers.readFileGson(userList,usersFilePath);



        // con Json
        //fileHandler.saveFile(userList);
        fileHandler.readFile(userList,usersFilePath);

        System.out.println(userList.get(2));
        for(var a : userList ){
            System.out.println(a);
        }

        System.out.println("BUSQUEDA DE UN USUARIO EN PARTICULAR");
        for(var a : userList ){
            if(a.getName().equals("AAAAAAAAAAA")){
                System.out.println(a);
            }
        }

        flightList.add(flight1);
        flightList.add(flight2);
        flightList.add(flight3);
        flightList.add(flight4);
        flightList.add(flight5);


        planeList.add(bronze1);
        planeList.add(bronze2);
        planeList.add(bronze3);
        planeList.add(bronze4);
        planeList.add(bronze5);
        planeList.add(bronze6);
        planeList.add(bronze7);
        planeList.add(bronze8);
        planeList.add(silver1);
        planeList.add(silver2);
        planeList.add(silver3);
        planeList.add(silver4);
        planeList.add(silver5);
        planeList.add(silver6);
        planeList.add(silver7);
        planeList.add(silver8);
        planeList.add(gold1);
        planeList.add(gold2);
        planeList.add(gold3);
        planeList.add(gold4);
        planeList.add(gold5);
        planeList.add(gold6);
        planeList.add(gold7);
        planeList.add(gold8);
        planeList.add(gold9);
        planeList.add(gold10);


        //GSON
//        fileHandler.saveFileGson(flightList,flightsFilePath);
//        flightList = fileHandler.readFileGson(flightList,flightsFilePath);
//
//        for(var a : flightList ){
//            System.out.println(a);
//        }
//
//        fileHandlerPlanes.saveFile(planeList);
//        planeList = fileHandlerPlanes.readFile(planesFilePath);
//        for (var a : planeList){
//            System.out.println(a);
//
//        }
//
//        fileHandlerFlights.saveFile(flightList);
//        flightList = fileHandlerFlights.readFile(flightsFilePath);
//
//        for (var flight : flightList){
//            System.out.println(flight);
//
//        }
//


//        Executable exec = new Executable(userList,flightList,planeList);
//
//        //exec.appCycle();
//
//        int a = exec.validateInt("Ingrese un numero entero");
//        System.out.println("el numero ingresado es " + a);











    }




    //Mariano: Sacar lo marcado de la clase Planes, terminar lo de mostrar vuelos disponibles, voy a ver lo de archivos
}
