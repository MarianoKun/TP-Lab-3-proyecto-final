package PlanePackage;

import java.sql.Struct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TimerTask;

public abstract class Planes {

    protected double capacitanceFuel;
    protected double costPerKm;
    protected int maxCapacity;
    protected int occupation;    //a borrar
    protected double maxSpeed;
    protected String typeOfPropulsion;
    protected boolean booked;             // a reveer
    protected double priceOfRent;
    List<LocalDateTime> dias=new ArrayList<LocalDateTime>();

    public Planes(double capacitanceFuel, double costPerKm, int maxCapacity, double maxSpeed, String typeOfPropulsion, double priceOfRent) {
        this.capacitanceFuel = capacitanceFuel;
        this.costPerKm = costPerKm;
        this.maxCapacity = maxCapacity;
        this.occupation = 0;
        this.maxSpeed = maxSpeed;
        this.typeOfPropulsion = typeOfPropulsion;
        this.priceOfRent=priceOfRent;
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

    public double getPriceOfRent() {
        return priceOfRent;
    }

    public void setPriceOfRent(double priceOfRent) {
        this.priceOfRent = priceOfRent;
    }

    public List<LocalDateTime> getDias() {
        return dias;
    }

    public void setDias(List<LocalDateTime> dias) {
        this.dias = dias;
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
        MOTOR_A_REACCION("Motor a reaccion"),
        MOTOR_A_HELICE("Motor a helice"),
        MOTOR_A_PISTONES("Motor a pistones");

        final String typeOfPropulsion;

        TypeOfPropulsion(String s) {
            this.typeOfPropulsion=s;
        }
    }
}
