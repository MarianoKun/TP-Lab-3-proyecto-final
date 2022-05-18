package PlanePackage;

import java.util.Objects;

public abstract class Planes {
    protected double capacitanceFuel;
    protected double costPerKm;
    protected int maxCapacity;
    protected int occupation;
    protected double maxSpeed;
    protected String typeOfPropulsion;
    protected boolean booked;

    public Planes(double capacitanceFuel, double costPerKm, int maxCapacity, int occupation, double maxSpeed, String typeOfPropulsion) {
        this.capacitanceFuel = capacitanceFuel;
        this.costPerKm = costPerKm;
        this.maxCapacity = maxCapacity;
        this.occupation = occupation;
        this.maxSpeed = maxSpeed;
        this.typeOfPropulsion = typeOfPropulsion;
        this.booked = false;
    }

    public Planes() {
        this.booked=false;
    }

    public double getCapacitanceFuel() {
        return capacitanceFuel;
    }

    public void setCapacitanceFuel(double capacitanceFuel) {
        this.capacitanceFuel = capacitanceFuel;
    }

    public double getCostPerKm() {
        return costPerKm;
    }

    public void setCostPerKm(double costPerKm) {
        this.costPerKm = costPerKm;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getOccupation() {
        return occupation;
    }

    public void setOccupation(int occupation) {
        this.occupation = occupation;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public String getTypeOfPropulsion() {
        return typeOfPropulsion;
    }

    public void setTypeOfPropulsion(String typeOfPropulsion) {
        this.typeOfPropulsion = typeOfPropulsion;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planes planes = (Planes) o;
        return Double.compare(planes.capacitanceFuel, capacitanceFuel) == 0 && Double.compare(planes.costPerKm, costPerKm) == 0 && maxCapacity == planes.maxCapacity && occupation == planes.occupation && Double.compare(planes.maxSpeed, maxSpeed) == 0 && booked == planes.booked && Objects.equals(typeOfPropulsion, planes.typeOfPropulsion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacitanceFuel, costPerKm, maxCapacity, occupation, maxSpeed, typeOfPropulsion, booked);
    }

    @Override
    public String toString() {
        return "Planes{" +
                "capacitanceFuel=" + capacitanceFuel +
                ", costPerKm=" + costPerKm +
                ", maxCapacity=" + maxCapacity +
                ", occupation=" + occupation +
                ", maxSpeed=" + maxSpeed +
                ", typeOfPropulsion='" + typeOfPropulsion + '\'' +
                ", booked=" + booked +
                '}';
    }



    public enum TypeOfPropulsion {
        MOTOR_A_REACCION,
        MOTOR_A_HELICE,
        MOTOR_A_PISTONES

    }
}
