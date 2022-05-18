package PlanePackage;

import java.util.Objects;

public class GoldPlane extends Planes {
    private boolean cateringService;
    private boolean wifi;


    public GoldPlane(double capacitanceFuel, double costPerKm, int maxCapacity, int occupation, double maxSpeed, String typeOfPropulsion, boolean cateringService, boolean wifi) {
        super(capacitanceFuel, costPerKm, maxCapacity, occupation, maxSpeed, typeOfPropulsion);
        this.cateringService = cateringService;
        this.wifi = wifi;
    }

    public GoldPlane(boolean cateringService, boolean wifi) {
        this.cateringService = cateringService;
        this.wifi = wifi;
    }

    public GoldPlane() {
    }

    public boolean isCateringService() {
        return cateringService;
    }

    public void setCateringService(boolean cateringService) {
        this.cateringService = cateringService;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GoldPlane goldPlane = (GoldPlane) o;
        return cateringService == goldPlane.cateringService && wifi == goldPlane.wifi;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cateringService, wifi);
    }

    @Override
    public String toString() {
        return "GoldPlane{" +
                "cateringService=" + cateringService +
                ", wifi=" + wifi +
                ", capacitanceFuel=" + capacitanceFuel +
                ", costPerKm=" + costPerKm +
                ", maxCapacity=" + maxCapacity +
                ", occupation=" + occupation +
                ", maxSpeed=" + maxSpeed +
                ", typeOfPropulsion='" + typeOfPropulsion + '\'' +
                ", booked=" + booked +
                '}';
    }
}
