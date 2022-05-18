import java.time.LocalDateTime;
import java.util.Scanner;

public class Executable {


    public static Flight reservationCycle(){
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


}
