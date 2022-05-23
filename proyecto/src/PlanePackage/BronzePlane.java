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
                ", occupation=" + occupation +
                ", maxSpeed=" + maxSpeed +
                ", typeOfPropulsion='" + typeOfPropulsion + '\'' +
                ", booked=" + booked +
                '}';
    }

    public String toPrint(){

        String toPrint;
        toPrint= "Avion Bronce: " + "Capacidad maxima: " + maxCapacity +
                ", Velocidad maxima: " + maxSpeed + ", Tipo de propulsion: " +
                typeOfPropulsion;

        if(booked==false) toPrint = toPrint.concat(", reservado: No ");
        else {toPrint= toPrint.concat(", reservado: Si ");}


        return toPrint;
    }


}
