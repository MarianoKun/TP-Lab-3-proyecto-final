package BranchJuanma;

import java.time.LocalDateTime;
import java.util.Objects;

public class Flight {
    private User user;
    private Plane planeType;  // FIJARSE SI ES MEJOR STRING O TIPO ENUM
    private LocalDateTime date;
    private Connection conecction;
    private int paxNumber;

    public Flight() {
    }

    public Flight(User user, Plane planeType, LocalDateTime date, City origin, City destination, int paxNumber) {
        this.user = user;
        this.planeType = planeType;
        this.date = date;
        this.Connection = origin;
        this.paxNumber = paxNumber;
    }

    // region GETTERS Y SETTERS
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PlaneType getPlaneType() {
        return planeType;
    }

    public void setPlaneType(PlaneType planeType) {
        this.planeType = planeType;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public City getOrigin() {
        return origin;
    }

    public void setOrigin(City origin) {
        this.origin = origin;
    }

    public City getDestination() {
        return destination;
    }

    public void setDestination(City destination) {
        this.destination = destination;
    }

    public int getPaxNumber() {
        return paxNumber;
    }

    public void setPaxNumber(int paxNumber) {
        this.paxNumber = paxNumber;
    }
    // endregion


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight ) o;
        return paxNumber == flight.paxNumber && Objects.equals(user, flight.user) && planeType == flight.planeType && Objects.equals(date, flight.date) && origin == flight.origin && destination == flight.destination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, planeType, date, origin, destination, paxNumber);
    }

    @Override
    public String toString() {
        return "Vuelo: " +
                "\nuser: \t" + user +
                "\nplaneType: \t" + planeType +
                "\ndate: \t" + date +
                "\norigin: \t" + origin +
                "\ndestination: \t" + destination +
                "\nPaxNumber: \t" + paxNumber;
    }


}
