package PlanePackage;

public class BronzePlane extends Planes{

    private static double PRICE = 3000;

    public BronzePlane(double capacitanceFuel, double costPerKm, int maxCapacity, double maxSpeed, String typeOfPropulsion) {
        super(capacitanceFuel, costPerKm, maxCapacity, maxSpeed, typeOfPropulsion,PRICE);
    }

    public BronzePlane() {
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

    public String toPrint(){

        String toPrint;
        toPrint= "Avion Bronce: " + "Capacidad maxima: " + maxCapacity +
                ", Velocidad maxima: " + maxSpeed + ", Tipo de propulsion: " +
                typeOfPropulsion;

        return toPrint;
    }


}
