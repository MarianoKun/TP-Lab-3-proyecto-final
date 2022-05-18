package PlanePackage;

import java.util.Objects;

public class SilverPlane extends Planes{
   private boolean cateringService;


    public SilverPlane(double capacitanceFuel, double costPerKm, int maxCapacity, int occupation, double maxSpeed, String typeOfPropulsion, boolean cateringService) {
        super(capacitanceFuel, costPerKm, maxCapacity, occupation, maxSpeed, typeOfPropulsion);
        this.cateringService = cateringService;
    }

    public SilverPlane(boolean cateringService) {
        this.cateringService = cateringService;
    }

    public SilverPlane() {
    }

    public boolean isCateringService() {
        return cateringService;
    }

    public void setCateringService(boolean cateringService) {
        this.cateringService = cateringService;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SilverPlane that = (SilverPlane) o;
        return cateringService == that.cateringService;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cateringService);
    }

    @Override
    public String toString() {
        return "SilverPlane{" +
                "capacitanceFuel=" + capacitanceFuel +
                ", costPerKm=" + costPerKm +
                ", maxCapacity=" + maxCapacity +
                ", occupation=" + occupation +
                ", maxSpeed=" + maxSpeed +
                ", typeOfPropulsion='" + typeOfPropulsion + '\'' +
                ", booked=" + booked +
                ", cateringService=" + cateringService +
                '}';
    }
}
