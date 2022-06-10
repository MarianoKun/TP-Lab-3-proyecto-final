package PlanePackage;

import java.time.LocalDate;
import java.util.List;

public class BronzePlane extends Planes{
    private  PlaneType name = PlaneType.BRONZE;
    private  double PRICE = 3000;

    public BronzePlane(double capacitanceFuel, double costPerKm, int maxCapacity, double maxSpeed, String typeOfPropulsion,List<LocalDate> dias) {
        super(capacitanceFuel, costPerKm, maxCapacity, maxSpeed, typeOfPropulsion,3000, dias);
    }

    public BronzePlane() {
    }

    public  PlaneType getName() {
        return name;
    }


    @Override
    public String toString() {
        return "BronzePlane{" +
                "capacitanceFuel=" + capacitanceFuel +
                ", costPerKm=" + costPerKm +
                ", maxCapacity=" + maxCapacity +
                ", maxSpeed=" + maxSpeed +
                ", typeOfPropulsion='" + typeOfPropulsion + '\'' +
                '}';
    }
    @Override
    public String toPrint(){

        String toPrint;
        toPrint= "Avion Bronce: " + "Capacidad maxima: " + maxCapacity +
                ", Velocidad maxima: " + maxSpeed + ", Tipo de propulsion: " +
                typeOfPropulsion;

        return toPrint;
    }


}
