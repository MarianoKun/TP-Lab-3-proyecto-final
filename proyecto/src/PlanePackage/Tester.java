package PlanePackage;

import BranchJuanma.City;
import BranchJuanma.Flight;
import BranchJuanma.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Tester {
    List<Planes>vuelos=new ArrayList<Planes>();

    public Tester() {

    }

    public void tester() {

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

        List<BronzePlane> arrayBronze = new ArrayList<>(15);
        List<GoldPlane> arrayGold = new ArrayList<>(15);
        List<SilverPlane> arraySilver = new ArrayList<>(15);

        arrayBronze.add(bronze1);
        arrayBronze.add(bronze2);
        arrayBronze.add(bronze3);
        arrayBronze.add(bronze4);
        arrayBronze.add(bronze5);
        arrayBronze.add(bronze6);
        arrayBronze.add(bronze7);
        arrayBronze.add(bronze8);

        arraySilver.add(silver1);
        arraySilver.add(silver2);
        arraySilver.add(silver3);
        arraySilver.add(silver4);
        arraySilver.add(silver5);
        arraySilver.add(silver6);
        arraySilver.add(silver7);
        arraySilver.add(silver8);

        arrayGold.add(gold1);
        arrayGold.add(gold2);
        arrayGold.add(gold3);
        arrayGold.add(gold4);
        arrayGold.add(gold5);
        arrayGold.add(gold6);
        arrayGold.add(gold7);
        arrayGold.add(gold8);
        arrayGold.add(gold9);
        arrayGold.add(gold10);

//        List<Planes>vuelos=new ArrayList<Planes>();
        vuelos.addAll(arrayBronze);
        vuelos.addAll(arrayGold);
        vuelos.addAll(arraySilver);




        for (var vuelo : arrayBronze) {
            if (vuelo != null) {
                System.out.println(vuelo.toPrint());
            }

        }

        for (var vuelo : arraySilver) {
            if (vuelo != null) {
                System.out.println(vuelo);
            }

        }

        for (var vuelo : arrayGold) {
            if (vuelo != null) {
                System.out.println(vuelo.toPrint());
            }

        }


        gold1.dias.add(LocalDate.of(2022,6, 25));


    }


    public <T extends Planes> void mostrarVuelosDelAvion(T vuelo) {

        for (var horarios : vuelo.dias) {
            if (horarios.isAfter(LocalDate.now())) {
                System.out.println(horarios);

            }
        }

    }

    public <T extends Planes> boolean tieneVuelos(T vuelo, LocalDate day) {

        for (var horarios : vuelo.dias) {

            if (horarios.isEqual(day)) {

                return true;
            }
        }
        return false;
    }

    public <T extends Planes> T mostrarAvionesDisponibles(LocalDate dias, List<T> vuelos) {   ///Do while

        Scanner scanner = new Scanner(System.in);
        T aRetornar = null;
        int i=0;

        for (var aVerificar : vuelos) {
            if (tieneVuelos(aVerificar, dias) == false) {
                System.out.println(i+1 + ". " + aVerificar);

            }else{

                i=i+1;
            }

        }

        i=scanner.nextInt();

        aRetornar=vuelos.get(i-1); ///esta mal

        tieneVuelos(aRetornar,dias); //false  puede ser condicion de corte


        return aRetornar;
    }

    public static Connections definirConecciones(String origen,String destino){

        ///Cambiar las connection para poner distino y origen

        Connections connections = null;

        boolean Cordoba = origen.equals(String.valueOf(City.CORDOBA)) || destino.equals(String.valueOf(City.CORDOBA));

        boolean Santiago = origen.equals(String.valueOf(City.SANTIAGO_DE_CHILE)) || destino.equals(String.valueOf(City.SANTIAGO_DE_CHILE));

        boolean BuenosAires= origen.equals(String.valueOf(City.BUENOS_AIRES)) || destino.equals(String.valueOf(City.BUENOS_AIRES));

        if(BuenosAires){

            if(Cordoba){

                connections=Connections.BsAs_CORDOBA;

            }else if(Santiago){
                connections=Connections.BsAs_SANTIAGO;

            }else {
                connections=Connections.BsAs_MONTEVIDEO;
            }

        }else if(Cordoba){

            if(Santiago){

                connections=Connections.CORDOBA_SANTIAGO;
            }else {
                connections=Connections.CORDOBA_MONTEVIDEO;
            }
        }else {
            connections=Connections.MONTEVIDEO_SANTIAGO;
        }
        return connections;
    }


    public Flight ciclo(User usuario) {

        Flight flight=new Flight();
        Connections coneccion;

        String origin = null;
        String destination=null;
        Scanner scanner =new Scanner(System.in);
        LocalDate date;
        LocalDateTime time;
        do {

            System.out.println("Idique la fecha en la que desea reservar vuelo"); // se toma fecha
            int day=scanner.nextInt();
            int month=scanner.nextInt();
            int year= scanner.nextInt();
            int hour=scanner.nextInt();
            int minute= scanner.nextInt();

             time = LocalDateTime.of(year,month,day,hour,minute);
             date = LocalDate.of(year,month,day);
            ///Hacer las validaciones

            ////******************** EN LA SELECCION DE ORIGEN/DESTINO HAY QUE VERIFICAR QUE SE PUEDA ESE RECORRIDO*****************************/////
            System.out.println("Seleccione el ORIGEN del vuelo"); // desplega opciones y elgie con numero o con otra cosa, no por teclado
            System.out.println("1. Buenos Aires\t2. Cordoba\t3.Montevideo");
            int selector = scanner.nextInt();


            do {
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
                        System.out.println("Seleccione un destino valido");
                }
            } while (origin != null);


            System.out.println("Seoleccione el DESTINO del vuelo"); // idem
            System.out.println("1. Cordoba\t2. Santiago de Chile\t3.Montevideo");

            do {
                switch (selector) {
                    case 1:
                        destination = String.valueOf(City.CORDOBA);
                        System.out.println("A seleccionado " + destination);
                        break;
                    case 2:
                        destination = String.valueOf(City.SANTIAGO_DE_CHILE);
                        System.out.println("A seleccionado " + destination);
                        break;
                    case 3:
                        destination = String.valueOf(City.MONTEVIDEO);
                        System.out.println("A seleccionado " + destination);
                        break;
                    case 4:
                        destination = String.valueOf(City.SANTIAGO_DE_CHILE);
                        System.out.println("A seleccionado " + origin);
                        break;
                    default:
                        System.out.println("Seleccione un destino valido");
                }
            }while(destination != null);


        }while(destination!=null && origin!=null);

       coneccion = definirConecciones(origin,destination);

        mostrarAvionesDisponibles(date,vuelos);
        ///avion que devuelve
        ///avion.dia.add(date)


       flight.setConecction(coneccion);
       flight.setDate(time);
//       flight.setPlaneType();



        return flight;
    }
}