package PlanePackage;

public class BronzePlane extends Planes{

    public BronzePlane(double capacitanceFuel, double costPerKm, int maxCapacity, int occupation, double maxSpeed, String typeOfPropulsion) {
        super(capacitanceFuel, costPerKm, maxCapacity, occupation, maxSpeed, typeOfPropulsion);
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
}
