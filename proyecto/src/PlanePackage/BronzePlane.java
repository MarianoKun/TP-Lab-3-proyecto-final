package PlanePackage;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class BronzePlane extends Planes{
    private  PlaneType name = PlaneType.BRONZE;
    private  double PRICE = 3000;

    public BronzePlane(double capacitanceFuel, double costPerKm, int maxCapacity, double maxSpeed, String typeOfPropulsion,List<LocalDate> dias) {
        super(capacitanceFuel, costPerKm, maxCapacity, maxSpeed, typeOfPropulsion,3000, dias);
    }

    public BronzePlane() {}

    public  PlaneType getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BronzePlane that = (BronzePlane) o;
        return Double.compare(that.PRICE, PRICE) == 0 && name == that.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, PRICE);
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
                ", Velocidad maxima: " + maxSpeed + " km/h, Tipo de propulsion: " +
                typeOfPropulsion;

        return toPrint;
    }


}
