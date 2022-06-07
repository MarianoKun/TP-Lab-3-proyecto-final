package PlanePackage;

public class BronzePlane extends Planes{
    private static PlaneType name = PlaneType.BRONZE;
    private static double PRICE = 3000;

    public BronzePlane(double capacitanceFuel, double costPerKm, int maxCapacity, double maxSpeed, String typeOfPropulsion) {
        super(capacitanceFuel, costPerKm, maxCapacity, maxSpeed, typeOfPropulsion,PRICE);
    }

    public BronzePlane() {
    }

    public static PlaneType getName() {
        return name;
    }

    public static void setName(PlaneType name) {
        BronzePlane.name = name;
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
