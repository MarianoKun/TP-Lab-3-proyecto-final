package PlanePackage;
import UserPackage.User;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Flight implements Serializable {
    private User user;
    private Planes planeType;  // FIJARSE SI ES MEJOR STRING O TIPO ENUM
    private LocalDateTime date;
    private Connections connection;
    private int paxNumber;
    private double totalFare;

    public Flight() {
    }

    public Flight(User user, Planes planeType, LocalDateTime date, Connections connection, int paxNumber) {
        this.user = user;
        this.planeType = planeType;
        this.date = date;
        this.connection = connection;
        this.paxNumber = paxNumber;
        this.totalFare = calcularaValor(this.planeType,this.connection);
    }

    // region GETTERS Y SETTERS

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Planes getPlaneType() {
        return planeType;
    }

    public void setPlaneType(Planes planeType) {
        this.planeType = planeType;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Connections getConecction() {
        return connection;
    }

    public void setConecction(Connections conecction) {
        this.connection = conecction;
    }

    public int getPaxNumber() {
        return paxNumber;
    }

    public void setPaxNumber(int paxNumber) {
        this.paxNumber = paxNumber;
    }

    public Connections getConnection() {
        return connection;
    }

    public void setConnection(Connections connection) {
        this.connection = connection;
    }

    public double getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(double totalFare) {
        this.totalFare = totalFare;
    }
    // endregion

    // TODO: 1/6/2022
    public <T extends Planes> double  calcularaValor (T plane, Connections connection){
        //(Cantidad de kms * Costo del km) + (cantidad de pasajeros * 3500) + (Tarifa del tipo de avión)

        double endPrice;

        endPrice= connection.getDistance() * plane.getCostPerKm() + paxNumber*3500 + plane.getPriceOfRent();

        return endPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return paxNumber == flight.paxNumber && Objects.equals(user, flight.user) && Objects.equals(planeType, flight.planeType) && Objects.equals(date, flight.date) && connection == flight.connection;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, planeType, date, connection, paxNumber);
    }

    @Override
    public String toString() {
        return "Vuelo: " +
                "\nuser: \t" + user +
                "\nplaneType: \t" + planeType +
                "\ndate: \t" + date +
                "\nConnecion: \t" + connection +
                "\nPaxNumber: \t" + paxNumber;
    }


}
