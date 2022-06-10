package PlanePackage;

import com.google.gson.*;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.sql.Struct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Planes implements Serializable , toPrint {  // no puede ser abstracta para trabajar con GSON
    protected UUID id;
    protected double capacitanceFuel;
    protected double costPerKm;
    protected int maxCapacity;
    protected double maxSpeed;
    protected String typeOfPropulsion;
    protected double priceOfRent;
    protected List<LocalDate> dias=new ArrayList<LocalDate>();

    public Planes() {
    }

    public Planes(double capacitanceFuel, double costPerKm, int maxCapacity, double maxSpeed, String typeOfPropulsion, double priceOfRent) {
        this.id = UUID.randomUUID();
        this.capacitanceFuel = capacitanceFuel;
        this.costPerKm = costPerKm;
        this.maxCapacity = maxCapacity;
        this.maxSpeed = maxSpeed;
        this.typeOfPropulsion = typeOfPropulsion;
        this.priceOfRent=priceOfRent;

    }

    public Planes(double capacitanceFuel, double costPerKm, int maxCapacity, double maxSpeed, String typeOfPropulsion, double priceOfRent, List<LocalDate> dias) {
        this.capacitanceFuel = capacitanceFuel;
        this.costPerKm = costPerKm;
        this.maxCapacity = maxCapacity;
        this.maxSpeed = maxSpeed;
        this.typeOfPropulsion = typeOfPropulsion;
        this.priceOfRent = priceOfRent;
        this.dias = dias;
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


    public double getPriceOfRent() {
        return priceOfRent;
    }

    public void setPriceOfRent(double priceOfRent) {
        this.priceOfRent = priceOfRent;
    }

    public List<LocalDate> getDias() {
        return dias;
    }

    public void setDias(List<LocalDate> dias) {
        this.dias = dias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planes planes = (Planes) o;
        return Double.compare(planes.capacitanceFuel, capacitanceFuel) == 0 && Double.compare(planes.costPerKm, costPerKm) == 0 && maxCapacity == planes.maxCapacity && Double.compare(planes.maxSpeed, maxSpeed) == 0 && Double.compare(planes.priceOfRent, priceOfRent) == 0 && Objects.equals(id, planes.id) && Objects.equals(typeOfPropulsion, planes.typeOfPropulsion) && Objects.equals(dias, planes.dias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, capacitanceFuel, costPerKm, maxCapacity, maxSpeed, typeOfPropulsion, priceOfRent, dias);
    }

    @Override
    public String toPrint() {
        return toString();
    }

    @Override
    public String toString() {
        return "Planes{" +
                "capacitanceFuel=" + capacitanceFuel +
                ", costPerKm=" + costPerKm +
                ", maxCapacity=" + maxCapacity +
                ", maxSpeed=" + maxSpeed +
                ", typeOfPropulsion='" + typeOfPropulsion +

                '}';
    }



    public enum TypeOfPropulsion {
        MOTOR_A_REACCION("Motor a reaccion"),
        MOTOR_A_HELICE("Motor a helice"),
        MOTOR_A_PISTONES("Motor a pistones");

        public final String typeOfPropulsion;

        TypeOfPropulsion(String s) {
            this.typeOfPropulsion=s;
        }
    }
}

