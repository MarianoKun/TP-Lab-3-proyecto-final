package PlanePackage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Tester {

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

        List<BronzePlane> arrayBronze = new ArrayList<BronzePlane>(15);
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

        for (var vuelo: arrayBronze){
            if(vuelo!=null){
                System.out.println(vuelo.toPrint());
            }

        }

        for (var vuelo: arraySilver){
            if(vuelo!=null){
                System.out.println(vuelo.toPrint());
            }

        }

        for (var vuelo: arrayGold){
            if(vuelo!=null){
                System.out.println(vuelo.toPrint());
            }

        }


        LocalDateTime dias;




    }
}


