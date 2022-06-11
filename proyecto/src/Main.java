import UserPackage.*;
import PlanePackage.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<Planes> planeList = new ArrayList<>();
        List<Flight> flightList = new ArrayList<>();
        List<User> userList = new ArrayList<>();


        ManageFlights manageFlights = new ManageFlights();
        flightList = manageFlights.readFile(flightList);


        ManageUsers manageUsers = new ManageUsers();
        userList = manageUsers.readFile(userList);


        ManagePlanes managePlanes = new ManagePlanes();
        planeList = managePlanes.readFile(planeList);

        Executable exec = new Executable(userList, flightList, planeList);

        exec.appCycle();


        ////todo crear metodo AGREGAR AVION


    }

}

// region /// todo CARGA INICIAL DE LISTAS
//        Admin admin1 = new Admin("Juan Manuel","Sanjurjo","admin1","admin1");
//        Admin admin2 = new Admin("Mariano","Fernandez","admin2","admin2");
//        User user1= new User("JUAN MANUEL","SANJURJO","34883649","32","juanma@hotmail.com","Juanma123");
//        User user2 = new User("MARIANO","FERNANDEZ","39645127","28","mariano@hotmail.com","Mariano123");
//        User user3 = new User("JULIO","VERNE","45678912","99","verne@hotmail.com","Vernejulio12");
//        User user4 = new User("JOSE MATIAS","PEREZ","34512789","31","matias@gmail.com","Matias123456");
//        User user5 = new User("RODRIGO","ORTIGOZA","24568974","49","rodrigo@outlook.com","Rodrigoortigozal12");
//        User user6 = new User("SERGIO PATRICIO","GONZALEZ ORTIGOZA","23561427","54","sergio@live.com.ar","sergioPatricio123");
//        User user7 = new User("MARINA JULIETA","DI GIORGIO","45214569","24","marina@live.com.ar","marinaDigiorgio123");
//        User user8 = new User("ANA INES","VERANO","35124578","31","ana@live.com.ar","anaVerano123");
//        User user9 = new User("JULIETA","GIULIANO","33157856","33","julieta@live.com.ar","JulieTaG123");
//        User user10 = new User("GABRIELA","DI SCALA","39564872","34","gabriela@live.com.ar","20Gabriela");
//        User user11 = new User("CAMILA","ROMANO","14563256","55","camila@live.com.ar","camiLA4589");
//        User user12 = new User("MICAELA","VERGARA MOLINA","251457985","40","micaela@live.com.ar","MicaMolina40");
//        User user13 = new User("ROMINA","BRONCO","36987458","37","romina@live.com.ar","RomiBronco99");
//        User user14 = new User("CINTIA","LOZADA","17895462","45","cintia@live.com.ar","cinLozada27");
//        User user15 = new User("MARCOS","LABRA","35145287","30","marcos@live.com.ar","marcosLa50");
//
//
//        userList.add(admin1);
//        userList.add(admin2);
//        userList.add(user1);
//        userList.add(user2);
//        userList.add(user13);
//        userList.add(user3);
//        userList.add(user4);
//        userList.add(user5);
//        userList.add(user6);
//        userList.add(user7);
//        userList.add(user8);
//        userList.add(user9);
//        userList.add(user14);
//        userList.add(user10);
//        userList.add(user11);
//        userList.add(user12);
//        userList.add(user15);
//
//     List <LocalDate>  dateplane1 = new ArrayList<>();
//     dateplane1.add(LocalDate.now());
//     dateplane1.add(LocalDate.now().minusDays(5));
//     dateplane1.add(LocalDate.now().plusDays(10));
//     dateplane1.add(LocalDate.now().minusDays(20));
//
//     List <LocalDate>  dateplane2 = new ArrayList<>();
//     dateplane2.add(LocalDate.now().plusDays(1));
//     dateplane2.add(LocalDate.now().minusDays(2));
//     dateplane2.add(LocalDate.now().plusDays(3));
//     dateplane2.add(LocalDate.now().minusDays(1));
//
//     List <LocalDate>  dateplane3 = new ArrayList<>();
//     dateplane3.add(LocalDate.now().minusDays(35));
//     dateplane3.add(LocalDate.now().minusDays(15));
//     dateplane3.add(LocalDate.now().minusDays(11));
//     dateplane3.add(LocalDate.now().plusDays(20));
//
//     List <LocalDate>  dateplane4 = new ArrayList<>();
//     dateplane4.add(LocalDate.now().minusDays(20));
//     dateplane4.add(LocalDate.now().plusDays(40));
//     dateplane4.add(LocalDate.now().plusDays(60));
//     dateplane4.add(LocalDate.now().minusDays(70));
//
//     List <LocalDate>  dateplane5 = new ArrayList<>();
//     dateplane5.add(LocalDate.now().plusDays(20));
//     dateplane5.add(LocalDate.now().minusDays(40));
//     dateplane5.add(LocalDate.now().plusDays(60));
//     dateplane5.add(LocalDate.now().plusDays(70));
//
//     List <LocalDate>  dateplane6 = new ArrayList<>();
//     dateplane6.add(LocalDate.now().plusDays(5));
//     dateplane6.add(LocalDate.now().plusDays(25));
//     dateplane6.add(LocalDate.now().minusDays(60));
//     dateplane6.add(LocalDate.now().plusDays(70));
//
//     List <LocalDate>  dateplane7 = new ArrayList<>();
//     dateplane7.add(LocalDate.now().plusDays(5));
//     dateplane7.add(LocalDate.now().plusDays(25));
//     dateplane7.add(LocalDate.now().minusDays(60));
//     dateplane7.add(LocalDate.now().plusDays(70));
//
//     List <LocalDate>  dateplane8 = new ArrayList<>();
//     dateplane8.add(LocalDate.now().plusDays(5));
//     dateplane8.add(LocalDate.now().plusDays(25));
//     dateplane8.add(LocalDate.now().minusDays(60));
//     dateplane8.add(LocalDate.now().plusDays(70));
//
//     List <LocalDate>  dateplane9 = new ArrayList<>();
//     dateplane9.add(LocalDate.now().plusDays(5));
//     dateplane9.add(LocalDate.now().plusDays(25));
//     dateplane9.add(LocalDate.now().minusDays(60));
//     dateplane9.add(LocalDate.now().plusDays(70));
//
//     List <LocalDate>  dateplane10 = new ArrayList<>();
//     dateplane10.add(LocalDate.now().plusDays(5));
//     dateplane10.add(LocalDate.now().plusDays(25));
//     dateplane10.add(LocalDate.now().minusDays(60));
//     dateplane10.add(LocalDate.now().plusDays(70));
//
//     List <LocalDate>  dateplane11 = new ArrayList<>();
//     dateplane11.add(LocalDate.now().plusDays(5));
//     dateplane11.add(LocalDate.now().plusDays(25));
//     dateplane11.add(LocalDate.now().minusDays(60));
//     dateplane11.add(LocalDate.now().plusDays(70));
//
//     List <LocalDate>  dateplane12 = new ArrayList<>();
//     dateplane12.add(LocalDate.now().plusDays(5));
//     dateplane12.add(LocalDate.now().plusDays(25));
//     dateplane12.add(LocalDate.now().minusDays(60));
//     dateplane12.add(LocalDate.now().plusDays(70));
//
//        BronzePlane bronze1 = new BronzePlane(1500, 150, 10, 100, Planes.TypeOfPropulsion.MOTOR_A_REACCION.typeOfPropulsion,dateplane1);
//        BronzePlane bronze2 = new BronzePlane(2500, 200, 5, 100, Planes.TypeOfPropulsion.MOTOR_A_HELICE.typeOfPropulsion,dateplane2);
//        BronzePlane bronze3 = new BronzePlane(2000, 150, 15, 100, Planes.TypeOfPropulsion.MOTOR_A_PISTONES.typeOfPropulsion,dateplane3);
//        BronzePlane bronze4 = new BronzePlane(3000, 300, 20, 100, Planes.TypeOfPropulsion.MOTOR_A_REACCION.typeOfPropulsion,dateplane4);
//        BronzePlane bronze5 = new BronzePlane(1000, 150, 2, 100, Planes.TypeOfPropulsion.MOTOR_A_HELICE.typeOfPropulsion,dateplane5);
//        BronzePlane bronze6 = new BronzePlane(2000, 200, 4, 100, Planes.TypeOfPropulsion.MOTOR_A_HELICE.typeOfPropulsion,dateplane6);
//        BronzePlane bronze7 = new BronzePlane(2500, 250, 5, 100, Planes.TypeOfPropulsion.MOTOR_A_REACCION.typeOfPropulsion,dateplane7);
//        BronzePlane bronze8 = new BronzePlane(2000, 200, 4, 100, Planes.TypeOfPropulsion.MOTOR_A_PISTONES.typeOfPropulsion,dateplane8);
//
//        SilverPlane silver1 = new SilverPlane(1500, 150, 2, 400, Planes.TypeOfPropulsion.MOTOR_A_REACCION.typeOfPropulsion, false,dateplane1);
//        SilverPlane silver2 = new SilverPlane(2500, 250, 8, 400, Planes.TypeOfPropulsion.MOTOR_A_PISTONES.typeOfPropulsion, true,dateplane2);
//        SilverPlane silver3 = new SilverPlane(2000, 200, 4, 400, Planes.TypeOfPropulsion.MOTOR_A_REACCION.typeOfPropulsion, false,dateplane4);
//        SilverPlane silver4 = new SilverPlane(1000, 150, 2, 400, Planes.TypeOfPropulsion.MOTOR_A_HELICE.typeOfPropulsion, true,dateplane6);
//        SilverPlane silver5 = new SilverPlane(3000, 300, 10, 400, Planes.TypeOfPropulsion.MOTOR_A_REACCION.typeOfPropulsion, true,dateplane8);
//        SilverPlane silver6 = new SilverPlane(2500, 150, 10, 400, Planes.TypeOfPropulsion.MOTOR_A_PISTONES.typeOfPropulsion, true,dateplane10);
//        SilverPlane silver7 = new SilverPlane(2000, 200, 4, 400, Planes.TypeOfPropulsion.MOTOR_A_REACCION.typeOfPropulsion, false,dateplane11);
//        SilverPlane silver8 = new SilverPlane(1500, 300, 4, 400, Planes.TypeOfPropulsion.MOTOR_A_REACCION.typeOfPropulsion, true,dateplane12);
//
//        GoldPlane gold1 = new GoldPlane(3000, 200, 20, 1000, Planes.TypeOfPropulsion.MOTOR_A_REACCION.typeOfPropulsion, false, true,dateplane3);
//        GoldPlane gold2 = new GoldPlane(4000, 200, 30, 1000, Planes.TypeOfPropulsion.MOTOR_A_REACCION.typeOfPropulsion, false, false,dateplane4);
//        GoldPlane gold3 = new GoldPlane(5000, 300, 20, 1000, Planes.TypeOfPropulsion.MOTOR_A_REACCION.typeOfPropulsion, false, false,dateplane5);
//        GoldPlane gold4 = new GoldPlane(3000, 200, 20, 1000, Planes.TypeOfPropulsion.MOTOR_A_REACCION.typeOfPropulsion, false, true,dateplane6);
//        GoldPlane gold5 = new GoldPlane(4000, 300, 20, 1000, Planes.TypeOfPropulsion.MOTOR_A_REACCION.typeOfPropulsion, true, true,dateplane7);
//        GoldPlane gold6 = new GoldPlane(5000, 300, 30, 1000, Planes.TypeOfPropulsion.MOTOR_A_REACCION.typeOfPropulsion, true, false,dateplane12);
//        GoldPlane gold7 = new GoldPlane(4000, 200, 20, 1000, Planes.TypeOfPropulsion.MOTOR_A_REACCION.typeOfPropulsion, true, false,dateplane11);
//        GoldPlane gold8 = new GoldPlane(3000, 150, 30, 1000, Planes.TypeOfPropulsion.MOTOR_A_REACCION.typeOfPropulsion, false, false,dateplane10);
//        GoldPlane gold9 = new GoldPlane(6000, 300, 40, 1000, Planes.TypeOfPropulsion.MOTOR_A_REACCION.typeOfPropulsion, true, true,dateplane5);
//        GoldPlane gold10 = new GoldPlane(10000, 300, 50, 1000, Planes.TypeOfPropulsion.MOTOR_A_REACCION.typeOfPropulsion, true, true,dateplane8);
//
//        planeList.add(bronze1);
//        planeList.add(bronze2);
//        planeList.add(bronze3);
//        planeList.add(bronze4);
//        planeList.add(bronze5);
//        planeList.add(bronze6);
//        planeList.add(bronze7);
//        planeList.add(bronze8);
//        planeList.add(silver1);
//        planeList.add(silver2);
//        planeList.add(silver3);
//        planeList.add(silver4);
//        planeList.add(silver5);
//        planeList.add(silver6);
//        planeList.add(silver7);
//        planeList.add(silver8);
//        planeList.add(gold1);
//        planeList.add(gold2);
//        planeList.add(gold3);
//        planeList.add(gold4);
//        planeList.add(gold5);
//        planeList.add(gold6);
//        planeList.add(gold7);
//        planeList.add(gold8);
//        planeList.add(gold9);
//        planeList.add(gold10);
//
//
//
//       Flight flight1 = new Flight(user1,bronze1,LocalDateTime.now(),Connections.BsAs_CORDOBA,4);
//        Flight flight2 = new Flight(user1,silver1,LocalDateTime.now().minusDays(5),Connections.CORDOBA_MONTEVIDEO,2);
//        Flight flight3 = new Flight(user1,silver2,LocalDateTime.now().plusDays(10),Connections.CORDOBA_SANTIAGO,3);
//        Flight flight4 = new Flight(user1,gold1,LocalDateTime.now().minusDays(20),Connections.MONTEVIDEO_SANTIAGO,3);
//
//        Flight flight5 = new Flight(user2,bronze2,LocalDateTime.now().plusDays(1),Connections.BsAs_MONTEVIDEO,2);
//        Flight flight6 = new Flight(user2,silver3,LocalDateTime.now().plusDays(1),Connections.BsAs_SANTIAGO,2);
//        Flight flight7 = new Flight(user2,gold2,LocalDateTime.now().plusDays(1),Connections.BsAs_MONTEVIDEO,4);
//        Flight flight8 = new Flight(user2,gold2,LocalDateTime.now().minusDays(1),Connections.BsAs_SANTIAGO,4);
//
//        Flight flight9 = new Flight(user3,bronze3,LocalDateTime.now().minusDays(35),Connections.CORDOBA_SANTIAGO,1);
//        Flight flight10 = new Flight(user3,bronze4,LocalDateTime.now().minusDays(15),Connections.CORDOBA_BsAs,1);
//        Flight flight11 = new Flight(user3,gold2,LocalDateTime.now().minusDays(11),Connections.BsAs_CORDOBA,2);
//        Flight flight12 = new Flight(user3,silver4,LocalDateTime.now().plusDays(20),Connections.CORDOBA_SANTIAGO,1);
//
//        Flight flight13 = new Flight(user4,bronze5,LocalDateTime.now().minusDays(20),Connections.SANTIAGO_BsAs,4);
//        Flight flight14 = new Flight(user4,bronze5,LocalDateTime.now().plusDays(40),Connections.SANTIAGO_CORDOBA,4);
//        Flight flight15 = new Flight(user4,silver5,LocalDateTime.now().plusDays(60),Connections.SANTIAGO_MONTEVIDEO,2);
//        Flight flight16 = new Flight(user4,silver6,LocalDateTime.now().minusDays(70),Connections.SANTIAGO_CORDOBA,2);
//
//        Flight flight17 = new Flight(user5,silver7,LocalDateTime.now().plusDays(20),Connections.MONTEVIDEO_SANTIAGO,1);
//        Flight flight18 = new Flight(user5,silver7,LocalDateTime.now().minusDays(40),Connections.MONTEVIDEO_CORDOBA,2);
//        Flight flight19 = new Flight(user5,silver8,LocalDateTime.now().plusDays(60),Connections.MONTEVIDEO_BsAs,4);
//        Flight flight20 = new Flight(user5,silver8,LocalDateTime.now().plusDays(70),Connections.MONTEVIDEO_BsAs,2);
//
//        Flight flight21 = new Flight(user7,bronze6,LocalDateTime.now().plusDays(5),Connections.CORDOBA_SANTIAGO,1);
//        Flight flight22 = new Flight(user7,bronze6,LocalDateTime.now().plusDays(25),Connections.CORDOBA_SANTIAGO,2);
//        Flight flight23 = new Flight(user7,bronze6,LocalDateTime.now().minusDays(60),Connections.BsAs_CORDOBA,4);
//        Flight flight24 = new Flight(user7,bronze6,LocalDateTime.now().plusDays(70),Connections.SANTIAGO_MONTEVIDEO,2);
//
//        Flight flight25 = new Flight(user8,bronze7,LocalDateTime.now().plusDays(5),Connections.CORDOBA_BsAs,1);
//        Flight flight26 = new Flight(user8,gold3,LocalDateTime.now().plusDays(25),Connections.CORDOBA_MONTEVIDEO,2);
//        Flight flight27 = new Flight(user8,gold4,LocalDateTime.now().minusDays(60),Connections.MONTEVIDEO_SANTIAGO,3);
//        Flight flight28 = new Flight(user8,gold5,LocalDateTime.now().plusDays(70),Connections.CORDOBA_BsAs,2);
//
//        Flight flight29 = new Flight(user9,gold5,LocalDateTime.now().plusDays(5),Connections.BsAs_SANTIAGO,4);
//        Flight flight30 = new Flight(user9,gold6,LocalDateTime.now().plusDays(25),Connections.SANTIAGO_BsAs,2);
//        Flight flight31 = new Flight(user9,gold7,LocalDateTime.now().minusDays(60),Connections.BsAs_MONTEVIDEO,4);
//        Flight flight32 = new Flight(user9,gold7,LocalDateTime.now().plusDays(70),Connections.BsAs_CORDOBA,2);
//
//        Flight flight33 = new Flight(user10,gold5,LocalDateTime.now().plusDays(5),Connections.BsAs_CORDOBA,4);
//        Flight flight34 = new Flight(user10,gold6,LocalDateTime.now().plusDays(25),Connections.CORDOBA_BsAs,2);
//        Flight flight35 = new Flight(user10,gold7,LocalDateTime.now().minusDays(60),Connections.MONTEVIDEO_BsAs,4);
//        Flight flight36 = new Flight(user10,gold8,LocalDateTime.now().plusDays(70),Connections.BsAs_MONTEVIDEO,2);
//
//        Flight flight37 = new Flight(user11,gold9,LocalDateTime.now().plusDays(5),Connections.SANTIAGO_BsAs,4);
//        Flight flight38 = new Flight(user11,bronze8,LocalDateTime.now().plusDays(25),Connections.BsAs_SANTIAGO,2);
//        Flight flight39 = new Flight(user11,silver1,LocalDateTime.now().minusDays(60),Connections.SANTIAGO_CORDOBA,1);
//        Flight flight40 = new Flight(user11,gold10,LocalDateTime.now().plusDays(70),Connections.SANTIAGO_CORDOBA,2);
//
//        Flight flight41 = new Flight(user12,silver3,LocalDateTime.now().plusDays(5),Connections.CORDOBA_BsAs,4);
//        Flight flight42 = new Flight(user12,bronze8,LocalDateTime.now().plusDays(25),Connections.BsAs_MONTEVIDEO,2);
//        Flight flight43 = new Flight(user12,bronze4,LocalDateTime.now().minusDays(60),Connections.CORDOBA_SANTIAGO,1);
//        Flight flight44 = new Flight(user12,gold7,LocalDateTime.now().plusDays(70),Connections.MONTEVIDEO_CORDOBA,2);
//
//        flightList.add(flight1);
//        flightList.add(flight2);
//        flightList.add(flight3);
//        flightList.add(flight4);
//        flightList.add(flight5);
//        flightList.add(flight6);
//        flightList.add(flight7);
//        flightList.add(flight8);
//        flightList.add(flight9);
//        flightList.add(flight10);
//        flightList.add(flight11);
//        flightList.add(flight12);
//        flightList.add(flight13);
//        flightList.add(flight14);
//        flightList.add(flight15);
//        flightList.add(flight16);
//        flightList.add(flight17);
//        flightList.add(flight18);
//        flightList.add(flight19);
//        flightList.add(flight20);
//        flightList.add(flight21);
//        flightList.add(flight22);
//        flightList.add(flight23);
//        flightList.add(flight24);
//        flightList.add(flight25);
//        flightList.add(flight26);
//        flightList.add(flight27);
//        flightList.add(flight28);
//        flightList.add(flight29);
//        flightList.add(flight30);
//        flightList.add(flight31);
//        flightList.add(flight32);
//        flightList.add(flight33);
//        flightList.add(flight34);
//        flightList.add(flight35);
//        flightList.add(flight36);
//        flightList.add(flight37);
//        flightList.add(flight38);
//        flightList.add(flight39);
//        flightList.add(flight40);
//        flightList.add(flight41);
//        flightList.add(flight42);
//        flightList.add(flight43);
//        flightList.add(flight44);
//
//  endregion

